FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	${@bb.utils.contains('TOPST_FEATURES', 'support-4k-video', '', 'file://disable-4k.cfg', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-tty-console', 'file://tty-console.cfg', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'file://systemd.cfg', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'nfs', 'file://nfs.cfg', '', d)} \
	${@bb.utils.contains('TCC_BSP_FEATURES', 'with-subcore', 'file://touch-bridge.cfg', '', d)} \
"

SRC_URI += "file://topst-base.cfg"

python __anonymous() {
    topst_features = d.getVar('TOPST_FEATURES', True)
    use_utoe = d.getVar('USE_USB_TO_ETHERNET', True)
    use_rndis_host = d.getVar('USE_RNDIS_HOST', True)
    use_ip_netfilter = d.getVar('USE_IP_NETFILTER', True)
    src_uri = d.getVar('SRC_URI').split()

    if 'network' in topst_features:
        if use_utoe == '1':
            src_uri.append('file://usbnet.cfg')
        if use_rndis_host == '1':
            src_uri.append('file://rndis.cfg')
        if use_ip_netfilter == '1':
            src_uri.append('file://netfilter.cfg')

    d.setVar('SRC_URI', ' '.join(src_uri))
}
