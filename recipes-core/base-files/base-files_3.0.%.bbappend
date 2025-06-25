FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://fstab \
	file://profile_local.sh \
"

do_install:append () {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/profile_local.sh ${D}${sysconfdir}/profile.d/
}
