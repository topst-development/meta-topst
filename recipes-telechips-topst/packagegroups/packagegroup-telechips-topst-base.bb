SUMMARY = "TOPST Core packages for Linux/GNU runtime images"
DESCRIPTION = "The minimal set of packages required to boot the TOPST"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

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
	e2fsprogs-dumpe2fs \
	e2fsprogs-resize2fs \
	e2fsprogs-e2fsck \
	e2fsprogs-e2scrub \
	e2fsprogs-mke2fs \
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
	util-linux-mount \
	util-linux-umount \
	util-linux-blkid \
	util-linux-fstrim \
	util-linux-hwclock \
	util-linux-fsck \
	dosfstools \
	vim \
	which \
"

RDEPENDS:${PN} += "${@bb.utils.contains('TCC_BSP_FEATURES', 'network', 'packagegroup-telechips-topst-base-net', '', d)}"
