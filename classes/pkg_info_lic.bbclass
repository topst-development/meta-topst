inherit buildhistory license_image


IMAGE_INSTALL_PACKAGE_FILE = "${BUILDHISTORY_DIR_IMAGE}/installed-package-parent-names.txt"
PACKAGE_INFO_RESULT_FILE = "${TOPDIR}/PACKAGE_INFO_${TELECHIPS_LINUX_SDK_VERSION}_${MACHINE}.csv"


# Create a file to clean up the packages that are installed in the actual SDK
buildhistory_get_image_installed:append() {
    if [ ! -e ${BUILDHISTORY_DIR_IMAGE} ]; then
        return
    fi

    cat ${BUILDHISTORY_DIR_IMAGE}/installed-package-info.txt | awk '{print $3}' | sort | uniq | \
        sed '/packagegroup\|-apps/d' > ${IMAGE_INSTALL_PACKAGE_FILE}
}


# Parsing copyright
def get_copyright(fn):
    import re

    pattern = r"copyright.*?(?:\(c\))?(\d{4})?[A-Za-z]+(?:\.|\\n)"
    url_or_email_pattern = r"(http|@)"

    result = ''

    if os.path.isfile(fn):
        with open(fn, 'r', encoding='utf-8') as f:
            content = f.read()
            match = re.search(pattern, content, re.DOTALL | re.IGNORECASE)
            if match:
                data = content[match.start():match.end()]
                next = match.end()
                if re.findall(url_or_email_pattern, data):
                    next = content.find((')', '>')[content.find(')', match.end()) == -1], match.end())
                result = content[match.start():next + 1]

    lines = result.splitlines()
    if len(lines) > 1:
        pattern = r'(c)|\d{4}'
        for i, line in enumerate(lines):
            result += line + '\n'
    return result.strip()


# Convert the recipeinfo and pkgurl files of the packages installed 
# in the SDK into the data used in the final output.
def convert_package_info_data_format(d):
    import re

    listvar = list()
    fields = ["Package", "Version", "License", "URL", "Homepage", "Copyright"]

    with open(d.getVar('IMAGE_INSTALL_PACKAGE_FILE'), "r") as f:
        listvar = [line.strip() for line in f.readlines()]
    if len(listvar) == 0:
        bb.fatal("Can't get install package data")

    pkglist = list()
    for var in listvar:
        license = ''
        version = ''
        url = ''
        homepage = ''

        if not '-native' in var:
            pkgdir = os.path.join(d.getVar('LICENSE_DIRECTORY'), var)
            recipeinfo = os.path.join(pkgdir, 'recipeinfo')
            urlinfo = os.path.join(pkgdir, 'urlinfo')

            if os.path.isfile(recipeinfo):
                with open(recipeinfo, "r") as f:
                    for data in re.finditer(re.compile("LICENSE: (.*)\nPR: (.*)\nPV: (.*)\n"), f.read()):
                        license = data.group(1)
                        version = data.group(3)

            if os.path.isfile(urlinfo):
                with open(urlinfo, "r") as f:
                    for data in re.finditer(re.compile("URL: (.*)\nHOMEPAGE: (.*)\n"), f.read()):
                        url = data.group(1)
                        homepage = data.group(2)

            if not 'Telechips' in license:
                copyright = ''
                pkglics = re.split(r'\s*[&|()]\s*', license)
                pkglics = [item.strip() for item in pkglics if item.strip()]
                ignoreliclist = ["PD", "BSD", "MIT", "GPL-3", "Apache", "OFL"]
                for lic in pkglics:
                    matched = next((validstr for validstr in ignoreliclist if lic.startswith(validstr)), None)
                    if not matched:
                        copyrightfn = os.path.join(pkgdir, 'generic_' + lic)
                        copyright += '[' + lic + ']\n'
                        copyright += get_copyright(copyrightfn) + '\n'

                item = {fields[0]: var,
                        fields[1]: version if version else '-',
                        fields[2]: license if license else '-',
                        fields[3]: url if url else '-',
                        fields[4]: homepage if homepage else '-',
                        fields[5]: copyright if copyright else '-'}
                pkglist.append(item)

    pkglist = sorted(pkglist, key=lambda item: item['Package'])

    return pkglist	


python do_pkg_info_lic() {
    import os
    import csv

    pkgvars = convert_package_info_data_format(d)
    if len(pkgvars) == 0:
        bb.fatal("Can't parsing %s" % d.getVar('IMAGE_INSTALL_PACKAGE_FILE'))

    with open(d.getVar('PACKAGE_INFO_RESULT_FILE'), 'w', newline='') as csvfile:
        fields = ["Package", "Version", "License", "URL", "Homepage", "Copyright"]
        writer = csv.DictWriter(csvfile, fieldnames=fields, delimiter=';')
        writer.writeheader()
        for item in pkgvars:
            writer.writerow(item)
}

do_pkg_info_lic[prefuncs] = "buildhistory_list_installed_image buildhistory_get_image_installed"
do_pkg_info_lic[prefuncs] += "${IMAGE_BASENAME}:do_populate_lic_deploy"
addtask do_pkg_info_lic
