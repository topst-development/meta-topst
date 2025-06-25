python do_populate_lic:append() {
    if '-native' in d.getVar('PN'):
        return

    info = get_package_url(d)
    if not type(info) is dict:
        return

    destdir = os.path.join(d.getVar('LICSSTATEDIR'), d.getVar('PN'))
    with open(os.path.join(destdir, "urlinfo"), "a") as f:
        for key in info.keys():
            f.write("%s: %s\n" % (key, info[key]))
    oe.qa.exit_if_errors(d)
}

def get_package_url(d):
    src_uri = (d.getVar('SRC_URI') or "").split()
    if not src_uri:
        return ''
 
    try:
        urls = list()
        # fetch2 to get only the download URL from the SRC_URI variable value.
        fetcher = bb.fetch2.Fetch(src_uri, d)
        for ud in fetcher.expanded_urldata():
            url = bb.fetch2.URI(ud.url)
            if url and url.scheme != 'file':
                urls.append(url)
    except bb.fetch2.BBFetchException as e:
        bb.fatal("Get packagae url data Error: " + repr(e))
    else:
        info = dict()
        info["URL"] = urls[0].scheme + '://' + urls[0].hostname + urls[0].path if len(urls) > 0 else '-'
        info["HOMEPAGE"] = d.getVar('HOMEPAGE') if d.getVar('HOMEPAGE') else \
                           d.getVar('UPSTREAM_CHECK_URI') if d.getVar('UPSTREAM_CHECK_URI') else '-'
        return info
