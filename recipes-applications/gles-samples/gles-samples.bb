DESCRIPTION = "PowerVR OpenGL ES sample applications"
SECTION = "applications"
LICENSE = "Telecips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "file://gles-samples.tar.gz"

# check mandatory features
REQUIRED_DISTRO_FEATURES = "opengl"

# check conflict features when using arm
CONFLICT_DISTRO_FEATURES += "wayland"

inherit features_check

LINKER_HASH_STYLE = "sysv"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d 	${D}${bindir}
	install -d 	${D}/usr/local/share/pvr/shaders
	install -m 0755 ${WORKDIR}/gles-samples/bin/*		${D}${bindir}/
	install -m 0644 ${WORKDIR}/gles-samples/shaders/*	${D}/usr/local/share/pvr/shaders/
}

FILES:${PN} += " \
	${bindir} \
	/usr/local/share/pvr \
"
RDEPENDS:${PN} += "libgles2-telechips libegl-telechips"
INSANE_SKIP:${PN} += "already-stripped file-rdeps"
