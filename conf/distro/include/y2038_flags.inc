# This is enable __USE_TIME_BITS64 define for resolve Y2038 Problem.
# Glibc 2.35 use 64bit time size instead of 32bit when enable __USE_TIME_BITS64 define

Y2038_CFLAGS = "-D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE -D_TIME_BITS=64 -D_FILE_OFFSET_BITS=64"
TARGET_CC_ARCH:append:class-target:arm = " ${Y2038_CFLAGS}"
TARGET_CC_ARCH:append:class-cross-canadian:arm = " ${Y2038_CFLAGS}"

Y2038_CFLAGS:pn-glibc = ""
Y2038_CFLAGS:pn-zlib = ""
Y2038_CFLAGS:pn-tar = ""
Y2038_CFLAGS:pn-cpio = ""
Y2038_CFLAGS:pn-findutils = ""
Y2038_CFLAGS:pn-kbd = ""
Y2038_CFLAGS:pn-qtbase = ""
