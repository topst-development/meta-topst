FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	${@bb.utils.contains('TCC_BSP_FEATURES', 'network', 'file://eth.network', '', d)} \
	${@bb.utils.contains('USE_RNDIS_HOST', '1', 'file://rndis.network', '', d)} \
"

do_install:append() {
	if ${@bb.utils.contains('TCC_BSP_FEATURES', 'network', 'true', 'false', d)}; then
		install -m 0644 ${WORKDIR}/eth.network			${D}${systemd_unitdir}/network/80-wired.network

		if ${@bb.utils.contains('USE_RNDIS_HOST', '1', 'true', 'false', d)}; then
			install -m 0644 ${WORKDIR}/rndis.network	${D}${sysconfdir}/systemd/network/
		fi
	fi
}
