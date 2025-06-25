SUMMARY = "Update rootfs for customizing root filesystem"
DESCRIPTION = "Update rootfs for customizing root filesystem"
SECTION = "base"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}${sysconfdir}
	echo "Telechips ${TOPST_LINUX_SDK_VERSION} (${TOPST_LINUX_SDK_RELEASE_DATE})" > ${D}${sysconfdir}/topst-release
}

FILES:${PN} += "${sysconfdir}"
