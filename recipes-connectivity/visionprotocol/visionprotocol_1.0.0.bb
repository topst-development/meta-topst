DESCRIPTION = "Telechips Vision Protocol Library"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI =  "${TELECHIPS_TOPST_GIT}/visionprotocol;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"
SRCREV = "08193eb565e0ef1cef715979913fb6f66b34c298"

inherit pkgconfig cmake

DEPENDS += ""
RDEPENDS:${PN} = ""

S = "${WORKDIR}/git"

do_install:append() {
}

FILES:${PN} += " \
	${datadir} \
	${includedir} \
	${libdir} \
	"
