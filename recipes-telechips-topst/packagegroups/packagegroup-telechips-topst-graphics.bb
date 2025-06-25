SUMMARY = "TOPST graphics packages for Linux/GNU runtime images"
DESCRIPTION = "The set of packages for graphics feature for TOPST"

inherit packagegroup

RDEPENDS:${PN} = " \
	${@bb.utils.contains("DISTRO_FEATURES", "wayland", "${WAYLAND_PACKAGES}", "", d)} \
"

WAYLAND_PACKAGES = " \
	wayland \
	weston \
	weston-init \
	weston-examples \
	${@bb.utils.contains("DISTRO_FEATURES", 'x11', 'weston-xwayland ${X11_PACKAGES}', '', d)} \
"
X11_PACKAGES = " \
    packagegroup-core-x11-xserver \
    packagegroup-core-x11-utils \
    matchbox-wm \
    mini-x-session \
    liberation-fonts \
	xeyes \
	xev \
	l3afpad \
	pcmanfm \
	puzzles \
"
