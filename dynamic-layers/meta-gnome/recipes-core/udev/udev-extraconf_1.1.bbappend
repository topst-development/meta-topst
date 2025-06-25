FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://61-mutter-primary-gpu.rules"

do_install:append() {
	install -m 0644 ${WORKDIR}/61-mutter-primary-gpu.rules	${D}${sysconfdir}/udev/rules.d
}
