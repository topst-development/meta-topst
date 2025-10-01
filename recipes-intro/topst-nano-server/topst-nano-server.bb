SUMMARY = "Topst Intro Webapp"
DESCRIPTION = "Topst Welcome page"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbb8bfe083d42f2137035f097961779b"

PV = "1.0.0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "\
    ${TELECHIPS_TOPST_GIT}/topst-nano-server.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH} \
    file://gui.tar.gz;unpack=false \
    https://github.com/topst-development/assets/raw/refs/heads/develop/background-enc.mp4;downloadfilename=background.mp4 \
"
SRC_URI[sha256sum] = "7f0ff6c4bc3c9ddaedbc5cc506b7f2bccd132bbc0e7343ebe7c702a021051238"

SRCREV = "8c44ee9f88ed7f731b912ce91e7b7952a6c59d91"

S = "${WORKDIR}/git"

#EXTRA_OECMAKE = "-DUSE_BACKWARD_COMPATIBILITY=ON"

DEPENDS = "pkgconfig pkgconfig-native gstreamer1.0 gstreamer1.0-plugins-base"

INSANE_SKIP:${PN} = "already-stripped"

inherit cmake systemd

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/services/topst-nano-server.service ${D}${systemd_system_unitdir}/

    install -d ${D}${datadir}/topst-nano-server/
    install -d ${D}${datadir}/topst-nano-server/html
    install -m 0644 ${WORKDIR}/background.mp4 ${D}${datadir}/topst-nano-server/html
    tar -xzf ${WORKDIR}/gui.tar.gz -C ${D}${datadir}/topst-nano-server/html
}

SYSTEMD_SERVICE:${PN} = "topst-nano-server.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
