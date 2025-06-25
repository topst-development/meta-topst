DISTRO_NAME = "Telechips Baseline (Poky/meta-telechips/meta-core)"
DISTRO_VERSION = "4.0.17"
MAINTAINER = "Telechips Automotive Platform Team <thshin@telechips.com>"

TCMODE ?= "default"
EXTERNAL_TOOLCHAIN ?= ""
TARGET_VENDOR = "-telechips"
TCLIBCAPPEND = ""
TOPST_LINUX_SDK_VERSION = "LINUX_YP4.0_TOPST_1.0.0"
TOPST_LINUX_SDK_RELEASE_DATE = "2025/05/30"

# Add default distro features
DISTRO_FEATURES_DEFAULT = "acl alsa argp debuginfod ext2 largefile opengl pam usbgadget usbhost wayland xattr zeroconf"
DISTRO_FEATURES_BACKFILL:remove = "pulseaudio"

SDK_NAME = "${DISTRO}-${TCLIBC}-${SDK_ARCH}-${IMAGE_BASENAME}-${TUNE_PKGARCH}"
SDKPATH = "/opt/${DISTRO}/${TELECHIPS_LINUX_SDK_VERSION}"

# Set image block size to 4096 because default block size of UFS is 4096.
EXTRA_IMAGECMD:append = " -b 4096"

IMAGE_LINGUAS ?= "en-us en-gb"
ENABLE_BINARY_LOCALE_GENERATION ?= "1"
IMAGE_FEATURES ?= ""
IMAGE_FEATURES:remove = "${@bb.utils.contains('EXTRA_IMAGE_FEATURES', 'ssh-server-openssh', 'read-only-rootfs', '', d)}"

# This is a list of packages that are used by the build system to build the distribution,
# they are not directly part of the distribution.
HOSTTOOLS_WHITELIST_GPL-3.0 ?= ""

# Set of restricted licenses for commercial
INCOMPATIBLE_LICENSE += "CC-BY-NC-1.0 CC-BY-NC-2.0 CC-BY-NC-2.5 CC-BY-NC-3.0 CC-BY-NC-3.0-DE CC-BY-NC-4.0 CC-BY-NC-ND-1.0 CC-BY-NC-ND-2.0"
INCOMPATIBLE_LICENSE += "CC-BY-NC-ND-2.5 CC-BY-NC-SA-3.0 CC-BY-NC-SA-3.0-DE CC-BY-NC-ND-3.0-IGO CC-BY-NC-ND-4.0 CC-BY-NC-SA-1.0"
INCOMPATIBLE_LICENSE += "CC-BY-NC-SA-2.0 CC-BY-NC-SA-2.0-DE CC-BY-NC-SA-2.0-FR CC-BY-NC-SA-2.0-UK CC-BY-NC-SA-2.5 CC-BY-NC-SA-3.0"
INCOMPATIBLE_LICENSE += "CC-BY-NC-SA-3.0-DE CC-BY-NC-SA-3.0-IGO CC-BY-NC-SA-4.0 FDK-AAC OSL-3.0"

# Set of commercial licenses whitelist
LICENSE_FLAGS_ACCEPTED += "commercial_openh264 commercial_libde265"

#TC_CXX_RUNTIME = "llvm"
#LIBCPLUSPLUS = "-stdlib=libc++"
#CLANGSDK = "1"
#TOOLCHAIN ?= "clang"
TOOLCHAIN:pn-bash = "gcc"
TOOLCHAIN:pn-grep = "gcc"
TOOLCHAIN:pn-findutils = "gcc"
TOOLCHAIN:pn-gawk = "gcc"
TOOLCHAIN:pn-gstreamer1.0-plugins-good = "gcc"
TOOLCHAIN:pn-gstreamer1.0-omx = "gcc"
TOOLCHAIN:pn-persistence-common-object = "gcc"
TOOLCHAIN:pn-persistence-client-library = "gcc"
TOOLCHAIN:pn-common-api-c++ = "gcc"
TOOLCHAIN:pn-gpsd = "gcc"

# Set preferred provides
PREFERRED_PROVIDER_llvm = "clang"
PREFERRED_PROVIDER_llvm-native = "clang-native"
PREFERRED_PROVIDER_nativesdk-llvm = "nativesdk-clang"

# Set provides
PROVIDES:pn-clang = "llvm"
PROVIDES:pn-clang-native = "llvm-native"
PROVIDES:pn-nativesdk-clang = "nativesdk-llvm"

LICENSE_CREATE_PACKAGE = "1"

BUILDCFG_VARS += "LINUX_VERSION KBUILD_DEFCONFIG TCC_BSP_FEATURES IMAGE_FEATURES SDKIMAGE_FEATURES"
BUILDCFG_VARS += "GCCVERSION"
BUILDCFG_VARS += "GLIBCVERSION"
BUILDCFG_VARS += "TOPST_FEATURES"

PREFERRED_PROVIDER_virtual/wpebackend ?= "wpebackend-fdo"

# Bump the connection timeout to 5 minutes to overcome slow proxy servers
FETCHCMD_wget = "/usr/bin/env wget -t 2 -T 300 -nv --passive-ftp --no-check-certificate"

PREMIRRORS ??= " \
bzr://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
cvs://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
git://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
gitsm://.*/.* http://downloads.yoctoproject.org/mirror/sources/ \n \
hg://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n \
osc://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
p4://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n \
svn://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
"

MIRRORS += " \
ftp://.*/.*      http://downloads.yoctoproject.org/mirror/sources/ \n \
http://.*/.*     http://downloads.yoctoproject.org/mirror/sources/ \n \
https://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n \
"

SOURCE_MIRROR_URL = "http://rnd-file.telechips.com/source-mirror/linux_yp4.0_topst_1.0.0"
SSTATE_MIRRORS ??= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${SSTATE_MIRRORS_X11}', \
				   bb.utils.contains('DISTRO_FEATURES', 'wayland', '${SSTATE_MIRRORS_WAYLAND}', \
						   '${SSTATE_MIRRORS_FBDEV}', d), d)}"

SSTATE_MIRRORS_X11 ??= "file://.* http://rnd-file.telechips.com/linux_yp4.0_topst_1.0.0/x11/PATH"
SSTATE_MIRRORS_WAYLAND ??= "file://.* http://rnd-file.telechips.com/linux_yp4.0_topst_1.0.0/wayland/PATH"
SSTATE_MIRRORS_FBDEV ??= "file://.* http://rnd-file.telechips.com/linux_yp4.0_topst_1.0.0/fbdev/PATH"

# Disable connectivity check for can build without network environment
CONNECTIVITY_CHECK_URIS = ""

INHERIT += "rm_work buildhistory own-mirrors"
BUILDHISTORY_COMMIT = "1"

# Refer to poky distro in meta-yocto layer
# QA check settings - a little stricter than the OE-Core defaults
WARN_TO_ERROR_QA = "already-stripped compile-host-path install-host-path \
		    ldflags pn-overrides rpaths staticdev \
		    useless-rpaths"
WARN_QA:remove = "${WARN_TO_ERROR_QA} host-user-contaminated unhandled-features-check"
ERROR_QA:append = " ${WARN_TO_ERROR_QA}"

ERROR_TO_WARN_QA = "pkgconfig version-going-backwards"
ERROR_QA:remove = "${ERROR_TO_WARN_QA}"
WARN_QA:append = " ${ERROR_TO_WARN_QA}"

# Do not error out on bbappends for missing recipes
BB_DANGLINGAPPENDS_WARNONLY = "true"
