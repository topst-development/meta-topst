SUMMARY = "TOPST Welcome screen launcher"
DESCRIPTION = "COG Webapp launcher service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbb8bfe083d42f2137035f097961779b"

PR = "r0"
PV = "1.0.0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    file://topst-welcome.service \
    file://LICENSE \
"

SRC_URI[sha256sum] = "a9efac19f06deb326e2b942bcd3c6effc603f614a763bb0b2cccc1f552c084a6"

inherit systemd

S = "${WORKDIR}"

DEPENDS = "cog topst-nano-server"
RDEPENDS:${PN} = "cog wpewebkit wpebackend-fdo topst-nano-server"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/topst-welcome.service	${D}${systemd_system_unitdir}/
}

SYSTEMD_SERVICE:${PN} = "topst-welcome.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
