DESCRIPTION = "Telechips Compiled NN Package"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

# Release tag: 1.3.1-r01
SRC_URI = "${TELECHIPS_TOPST_GIT}/tc-compiled-nn.git;protocol=${TOPST_GIT_PROTOCOL};nobranch=1"
SRCREV = "cceb2adca3d6f324d815c09a54da533703149ee2"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

# Preserve the compiler-supplied YOLOv8 model binary.
INHIBIT_PACKAGE_STRIP_FILES += "${PKGD}${datadir}/yolov8s_quantized/net.so"

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
