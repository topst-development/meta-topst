DESCRIPTION = "Telechips DVRS T-Codec"
SECTION = "T-Codec"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"


inherit cmake pkgconfig

DEPENDS = "virtual/kernel glib-2.0 t-util"
RDEPENDS:${PN} += "t-util"

SRC_URI = "${TELECHIPS_TOPST_GIT}/t-codec.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"

SRC_URI:append = " \
	file://0002-FEA-enable-vpu-v3-for-topst-ai.patch \
	"
SRCREV = "c3b7dd67803f0cfab997e614bedce19a779e95bf"

S = "${WORKDIR}/git"
B = "${S}"

PATCHTOOL = "git"
#PACKAGE_ARCH = "${MACHINE_ARCH}"


EXTRA_OECMAKE += "-DLINUX_KERNEL_DIR=${STAGING_KERNEL_DIR}"
EXTRA_OECMAKE += "-DCHIPSET=${TCC_ARCH_FAMILY}"
EXTRA_OECMAKE += "-DCHIPSET_CORE=${TCC_MACHINE_FAMILY}"
EXTRA_OECMAKE += "-DLINUX_KERNEL_VERSION=${LINUX_VERSION}"
EXTRA_OECMAKE += " ${@bb.utils.contains('INVITE_PLATFORM', 'support-4k-video', '-DSUPPORT_4K_VIDEO=enable', '-DSUPPORT_4K_VIDEO=disable', d)}"
EXTRA_OECMAKE += " ${@bb.utils.contains('TCC_BSP_FEATURES', 'multimedia_vpu_v3', '-DUSE_VPU_LEGACY=disable', '-DUSE_VPU_LEGACY=enable', d)}"
EXTRA_OECMAKE += "-DMACHINE=${MACHINE}"

FILES:${PN} += " \
	${libdir}/*.so \
"

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
