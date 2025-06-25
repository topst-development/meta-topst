DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://0001-wl-Fix-wrong-wl_shm-for-cursor.patch"
