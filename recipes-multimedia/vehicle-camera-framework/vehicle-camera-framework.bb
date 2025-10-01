SUMMARY = "vehicle-camera-framework is camera source management system for vehicle"
DESCRIPTION = "vehicle-camera-framework"
SECTION = "vehicle camera framework"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

inherit cmake pkgconfig systemd

DEPENDS = "virtual/kernel nng"
RDEPENDS:${PN} += "nng"

SRC_URI = "${TELECHIPS_TOPST_GIT}/vehicle-camera-framework.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}; \
file://vehicle-camera-framework.service \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
B = "${S}"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "${PN}.service"

PATCHTOOL = "git"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECMAKE += "-DCHIPSET=${TCC_ARCH_FAMILY} "
EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR} "

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"

FILES:${PN} = " \
    ${systemd_unitdir} \
    ${libdir}/*.so* \
    ${bindir}/* \
"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_unitdir}/system
}
