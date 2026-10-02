DESCRIPTION = "Telechips N-Dolphin Camera Sample Application"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

# Release tag: 1.3.1-r01
SRC_URI = "${TELECHIPS_TOPST_GIT}/tc-nn-camera-app.git;protocol=${TOPST_GIT_PROTOCOL};nobranch=1"
SRCREV = "ff941ee167bf553cc4771eca06a551939f8751e7"

inherit pkgconfig cmake

DEPENDS += "virtual/kernel"
RDEPENDS:${PN} = ""

EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/git"
