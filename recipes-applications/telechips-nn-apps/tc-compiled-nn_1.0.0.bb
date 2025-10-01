DESCRIPTION = "Telechips Compiled NN Package"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "${TELECHIPS_TOPST_GIT}/tc-compiled-nn.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"
SRCREV = "${AUTOREV}"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}${datadir}

    for quantized_folder in ${S}/*_quantized; do
	install -d ${D}${datadir}/${quantized_folder##*/}/
	cp ${quantized_folder}/npu_cmd.bin ${D}${datadir}/${quantized_folder##*/}/npu_cmd.bin
	cp ${quantized_folder}/quantized_network.bin ${D}${datadir}/${quantized_folder##*/}/quantized_network.bin
	if [ -d "${quantized_folder}/sample/" ]; then
	    cp -r ${quantized_folder}/sample/ ${D}${datadir}/${quantized_folder##*/}/sample/
	fi
    done
    for quantized_folder in ${B}/*_quantized; do
	install -d ${D}${datadir}/${quantized_folder##*/}/
	cp ${quantized_folder}/net.so ${D}${datadir}/${quantized_folder##*/}/net.so
    done
}

FILES:${PN} += " \
    ${datadir}/* \
    "
