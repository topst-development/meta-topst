FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	${@bb.utils.contains('TOPST_FEATURES', 'support-4k-video', '', 'file://disable-4k.cfg', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-tty-console', 'file://tty-console.cfg', '', d)} \
	${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'file://ov5647.cfg', '', d)} \
	${@bb.utils.contains('TOPST_CAM_MODULE', 'imx219', 'file://imx219.cfg', '', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-bt-usb', 'file://bt-usb.cfg', '', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-pcie-usb', 'file://pcie-usb.cfg', '', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-nf-docker', 'file://nf-docker.cfg', '', d)} \
	${@bb.utils.contains('TOPST_FEATURES', 'support-swap', 'file://swap.cfg', '', d)} \
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

do_compile:prepend:tcc805x() {
	dtsi="${S}/arch/arm64/boot/dts/telechips/tcc805x/override/tcc805x-videoinput-camera-module.dtsi"

	if [ ! -f "${dtsi}" ]; then
		echo "NOTE: ${dtsi} not found, skip camera sensor toggling"
		exit 0
	fi

	if ${@bb.utils.contains('TOPST_CAM_MODULE','ov5647','true','false',d)}; then
		sed -i -E 's|^([[:space:]]*)//[[:space:]]*#include[[:space:]]+"tcc805x-videoinput-mipi0-ov5647\.dtsi"|\1#include "tcc805x-videoinput-mipi0-ov5647.dtsi"|' "${dtsi}"
	fi

	if ${@bb.utils.contains('TOPST_CAM_MODULE','imx219','true','false',d)}; then
		sed -i -E 's|^([[:space:]]*)//[[:space:]]*#include[[:space:]]+"tcc805x-videoinput-mipi0-imx219\.dtsi"|\1#include "tcc805x-videoinput-mipi0-imx219.dtsi"|' "${dtsi}"
	fi
}

do_compile:prepend:tcc750x() {
	dtsi="${S}/arch/arm64/boot/dts/telechips/tcc750x/override/tcc750x-videoinput-camera-module.dtsi"

	if [ ! -f "${dtsi}" ]; then
		echo "NOTE: ${dtsi} not found, skip camera sensor toggling"
		exit 0
	fi

	if ${@bb.utils.contains('TOPST_CAM_MODULE','ov5647','true','false',d)}; then
		sed -i -E 's|^([[:space:]]*)//[[:space:]]*#include[[:space:]]+"tcc750x-videoinput-ov5647\.dtsi"|\1#include "tcc750x-videoinput-ov5647.dtsi"|' "${dtsi}"
	fi

	if ${@bb.utils.contains('TOPST_CAM_MODULE','imx219','true','false',d)}; then
		sed -i -E 's|^([[:space:]]*)//[[:space:]]*#include[[:space:]]+"tcc750x-videoinput-imx219\.dtsi"|\1#include "tcc750x-videoinput-imx219.dtsi"|' "${dtsi}"
	fi
}
