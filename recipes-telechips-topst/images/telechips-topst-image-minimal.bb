DESCRIPTION = "This image provides topst image minimal"

inherit topst-base-image

IMAGE_INSTALL += " \
	kernel-modules \
	customize-rootfs \
	rtl815x \
	rtl88x2bu \
	isp-firmware \
	${@bb.utils.contains_any('TCC_ARCH_FAMILY', 'tcc805x', ' hsm', '', d)} \
	gnupg \
	${@bb.utils.contains("DISTRO_FEATURES", 'x11', 'tk', '', d)} \
	tcl \
	alsa-lib \
	alsa-utils \
	alsa-state \
	udev-extraconf \
	curl \
	libdrm-tests \
"
