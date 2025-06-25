SUMMARY = "Meta package for building a installable toolchain for TOPST Linux SDK"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
		    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
inherit populate_sdk

FEATURES = "-topst"
FEATURES:append = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '-opengl', '-no-opengl', d)}"
FEATURES:append = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-wayland', '-fbdev', d)}"

TOOLCHAIN_OUTPUTNAME = "${TELECHIPS_LINUX_SDK_VERSION}-${TCC_ARCH_FAMILY}-toolchain-${PACKAGE_ARCH}${FEATURES}-${SDK_ARCH}-gcc-${SDKGCCVERSION}"
TOOLCHAIN_TARGET_TASK:append = " packagegroup-telechips-topst-toolchain-target"
SDKIMAGE_LINGUAS = ""

create_sdk_files:append() {
    mkdir -p ${SDK_OUTPUT}/${SDKPATHNATIVE}/environment-setup.d/
    script=${SDK_OUTPUT}/${SDKPATHNATIVE}/environment-setup.d/telechips.sh

    touch $script
    echo 'export SIZE="${TARGET_PREFIX}size"' >> $script
    echo 'export KERNEL_DIR="${SDKTARGETSYSROOT}/usr/src/kernel"' >> $script
    echo 'export MACHINE="${MACHINE}"' >> $script
}
