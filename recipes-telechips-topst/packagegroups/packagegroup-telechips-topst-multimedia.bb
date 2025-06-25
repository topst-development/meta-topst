SUMMARY = "TOPST multimedia packages for Linux/GNU runtime images"
DESCRIPTION = "The set of packages for multimedia feature for TOPST"

inherit packagegroup

RDEPENDS:${PN} = " \
	gstreamer1.0-meta-audio \
	gstreamer1.0-meta-video \
	gstreamer1.0-meta-extra \
	${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', '${PULSEAUDIO_PKGS}', '', d)} \
"

PULSEAUDIO_PKGS= "\
	pulseaudio \
	pulseaudio-server \
	pulseaudio-misc \
	pulseaudio-module-remap-sink \
	pulseaudio-module-loopback \
	alsa-plugins-pulseaudio-conf \
"
