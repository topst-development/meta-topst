SUMMARY = "These modules are OMX modules for linux"
DESCRIPTION = "OMX modules"
SECTION = "libs"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

DEPENDS = "glib-2.0 vehicle-camera-framework t-codec drm t-util"
RDEPENDS:${PN} += "vehicle-camera-framework"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
inherit cmake pkgconfig

SRC_URI = "${TELECHIPS_TOPST_GIT}/omx-components.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}; \
	file://0001-ref-disable-overlaymix.patch \ 
	file://0001-Add-buffer-reader-component-and-buffer_venc-app.patch \ 
         "
SRCREV = "e429af143197b14f7d393d10bcb182f83b0efe3e"
S = "${WORKDIR}/git"
B = "${S}"

PACKAGES = "${PN}"
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} = " \
    ${libdir}/* \
    ${bindir}/* \
    ${includedir}/* \
    "

EXTRA_OECMAKE += "-DCHIPSET=${TCC_ARCH_FAMILY} "
EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR} "
EXTRA_OECMAKE += "-DKERNEL_VERSION=${LINUX_VERSION}}"
EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=RELEASE "

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
