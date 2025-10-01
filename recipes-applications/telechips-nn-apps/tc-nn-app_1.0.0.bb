DESCRIPTION = "Telechips NPU Sample Application"
SECTION = "applications"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "${TELECHIPS_TOPST_GIT}/tc-nn-app.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH} \
	file://tc-nn-app.service \
"

SRCREV = "5cb59c264b88251b7d75a396df47daa7fa374f51"

PATCHTOOL = "git"
inherit pkgconfig cmake systemd

DEPENDS += "libtcndnpu opencv virtual/kernel json-c visionprotocol vehicle-camera-framework"
RDEPENDS:${PN} = "libtcndnpu libopencv-core libopencv-imgproc libopencv-videoio libopencv-imgcodecs mlx-kernel json-c visionprotocol tc-compiled-nn"

EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/git"

# for systemd
# SYSTEMD_PACKAGES = "${PN}"
# SYSTEMD_SERVICE:${PN} = "tc-nn-app.service"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/tc-nn-app.service	${D}${systemd_unitdir}/system
}

FILES:${PN} += " \
    ${systemd_unitdir} \
    "
