# derived from meta/recipes-core/packagegroups/packagegroup-core-boot.bb

SUMMARY = "Minimal boot requirements"
DESCRIPTION = "The minimal set of packages required to boot the Telechips IVI System"
PR = "r11"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

#
# Set by the machine configuration with packages essential for device bootup
#
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

SYSVINIT_SCRIPTS = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'rtc', 'busybox-hwclock', '', d)} \
	modutils-initscripts \
	${VIRTUAL-RUNTIME_initscripts} \
    ${@bb.utils.contains('TCC_BSP_FEATURES', 'network', 'resolvconf init-ifupdown', '', d)} \
"

RDEPENDS:${PN} = " \
	acl \
	attr \
	bash \
	ldd \
	procps \
	bc \
	coreutils \
	cpio \
	e2fsprogs \
	ed \
	findutils \
	gawk \
	grep \
	kmod \
	logrotate \
	mingetty \
	ncurses \
	psmisc \
	sed \
	sudo \
	tar \
	time \
	util-linux \
    base-files \
    base-passwd \
    busybox \
	util-linux \
	util-linux-mount \
	util-linux-umount \
	util-linux-blkid \
	util-linux-fsck \
	dosfstools \
	vim \
	which \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINIT_SCRIPTS}', '', d)} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
"

RRECOMMENDS:${PN} = " \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
"
