DESCRIPTION = "Target packages for basic of Telechips TOPST Linux SDK"

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"
PACKAGE_ARCH = "${TUNE_PKGARCH}"
inherit packagegroup

RDEPENDS:${PN} += "\
	packagegroup-core-standalone-sdk-target \
	libsqlite3-dev \
    expat-dev \
	base-files \
	glib-2.0-dev \
	dbus-dev \
	dbus-glib-dev \
	boost-dev \
	libusb1-dev \
	taglib-dev \
	python3 \
	kernel-devsrc \
	gawk \
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '${OPENGL_DEP_PACKAGES}', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland opengl', '${WAYLAND_DEP_PACKAGES}', '', d)} \
	gstreamer1.0-dev \
	gstreamer1.0-plugins-base-dev \
	gstreamer1.0-plugins-good-dev \
	gstreamer1.0-plugins-bad-dev \
"

OPENGL_DEP_PACKAGES = " \
	libegl-dev \
	libgles1-dev \
	libgles2-dev \
	libgles3-dev \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm-dev', '', d)} \
	libsdl2-dev \
"

WAYLAND_DEP_PACKAGES = " \
	libdrm-dev \
	wayland-dev \
	weston-dev \
"
