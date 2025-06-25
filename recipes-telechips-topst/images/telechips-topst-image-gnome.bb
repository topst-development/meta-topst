DESCRIPTION = "This image provides topst gnome image"

require telechips-topst-image-minimal.bb

IMAGE_INSTALL += " \
	android-tools \
	sqlite3 \
	tzdata \
	tzdata-posix \
	python3 \
	python3-pip \
	packagegroup-telechips-topst-multimedia \
	packagegroup-gnome-apps \
	packagegroup-gnome-desktop \
	packagegroup-meta-networking \
	network-manager-applet \
	usermode \
	gdm \
	${X11_PACKAGES} \
"

X11_PACKAGES = " \
	xwayland \
    packagegroup-core-x11-xserver \
    packagegroup-core-x11-utils \
    liberation-fonts \
	ttf-nanum-font \
	xeyes \
	xev \
	l3afpad \
	pcmanfm \
	puzzles \
"

# set systemd default taget when using systemd
SYSTEMD_DEFAULT_TARGET = "graphical.target"
