DESCRIPTION = "This image provides topst image"

require telechips-topst-image-minimal.bb

IMAGE_INSTALL += " \
	${@bb.utils.contains('TOPST_FEATURES', 'multimedia', 'packagegroup-telechips-topst-multimedia', '', d)} \
	packagegroup-telechips-topst-graphics \
	${@bb.utils.contains("DISTRO_FEATURES", 'x11', '', 'packagegroup-topst-welcome', d)} \
	android-tools \
	sqlite3 \
	tzdata \
	tzdata-posix \
	python3 \
	python3-pip \
"

# set systemd default taget when using systemd
SYSTEMD_DEFAULT_TARGET = "graphical.target"
