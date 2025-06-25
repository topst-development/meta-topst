FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:append = " file://nng.pc"

do_install:append() {
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${WORKDIR}/nng.pc ${D}${libdir}/pkgconfig/
}
