FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://apt.tar.gz"

do_install:append() {
	cp -af ${WORKDIR}/apt/* ${D}${sysconfdir}/apt/
}
