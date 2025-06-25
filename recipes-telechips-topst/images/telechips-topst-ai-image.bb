DESCRIPTION = "This image provides topst ai image minimal"

inherit topst-base-image

IMAGE_INSTALL += "tc-nn-app tc-nn-camera-app isp-firmware"
IMAGE_INSTALL += "i2c-tools v4l-utils"

# install pciutils
IMAGE_INSTALL:append = "${@bb.utils.contains_any('TOPST_FEATURES', 'pcie-host', ' pciutils', '', d)}"

# set systemd default taget when using systemd
SYSTEMD_DEFAULT_TARGET = "graphical.target"
