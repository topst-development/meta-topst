DESCRIPTION = "Telechips N-Dolphin npu test Sample Application"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "${TELECHIPS_TOPST_GIT}/libtc-ndnpu.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"
SRCREV = "1e5b8ca8105db6bc90f99190245681dee006a1e7"

inherit pkgconfig cmake

DEPENDS += ""
RDEPENDS_${PN} = "mlx-kernel tc-compiled-nn"
S = "${WORKDIR}/git"
