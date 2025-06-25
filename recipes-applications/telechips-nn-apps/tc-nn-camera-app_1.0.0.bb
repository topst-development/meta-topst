DESCRIPTION = "Telechips N-Dolphin Camera Sample Application"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "${TELECHIPS_TOPST_GIT}/tc-nn-camera-app.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"
SRCREV = "fa3001859a07f9789cf14db1fd0ceeaa76988d1f"

inherit pkgconfig cmake

DEPENDS += "virtual/kernel"
RDEPENDS:${PN} = ""

EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/git"
