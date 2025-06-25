FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://resize-rootfs.service \
    file://resize-rootfs.sh \
"

do_install:append() {
    install -m 0644 ${WORKDIR}/resize-rootfs.service        ${D}/${systemd_system_unitdir}/
    install -m 0755 ${WORKDIR}/resize-rootfs.sh             ${D}/${bindir}/

    ln -sf ../resize-rootfs.service							${D}${systemd_system_unitdir}/sysinit.target.wants/resize-rootfs.service
	ln -sf ../systemd-timesyncd.service						${D}${systemd_system_unitdir}/sysinit.target.wants/systemd-timesyncd.service
}

FILES:${PN} += " \
	${bindir}/resize-rootfs.sh \
"
