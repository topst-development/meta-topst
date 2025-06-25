#
# Copyright (C) Telechips Inc.
#

inherit core-image extrausers make_fai

IMAGE_FSTYPES = "ext4"
IMAGE_ROOTFS_SIZE = "102400"
IMAGE_OVERHEAD_FACTOR ??= "1.2"
IMAGE_ROOTFS_ALIGNMENT = "1024"

LINGUAS_KO_KR = "ko-kr ko-kr.euc-kr"
LINGUAS_EN_GB = "en-gb en-gb.iso-8859-1"
LINGUAS_EN_US = "en-us en-us.iso-8859-1"
LINGUAS_ZH_CN = "zh-cn.gb18030 zh-cn.gb2312 zh-cn.gbk zh-hk zh-hk.big5-hkscs zh-sg zh-sg.gb2312 zh-sg.gbk zh-tw zh-tw.big5 zh-tw.euc-tw"
LINGUAS_JA_JP = "ja-jp ja-jp.euc-jp"

IMAGE_LINGUAS = "${LINGUAS_KO_KR} ${LINGUAS_EN_GB} ${LINGUAS_EN_US} ${LINGUAS_ZH_CN} ${LINGUAS_JA_JP}"

# topst base install packages
CORE_IMAGE_BASE_INSTALL = "\
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-core-boot \
"

IMAGE_INSTALL = "${CORE_IMAGE_BASE_INSTALL}"
IMAGE_INSTALL += "\
	packagegroup-telechips-topst-base \
	glibc-gconv-utf-16 \
	glibc-gconv-utf-32 \
	glibc-gconv-euc-kr \
	glibc-gconv-euc-cn \
	glibc-gconv-euc-jp \
	glibc-gconv-euc-tw \
	glibc-gconv-big5 \
	glibc-gconv-big5hkscs \
	glibc-gconv-gb18030 \
	glibc-gconv-gbbig5 \
	glibc-gconv-iso8859-1 \
	glibc-gconv-unicode \
	glibc-gconv-libksc \
	${@bb.utils.contains_any('TCC_ARCH_FAMILY', 'tcc805x', ' hsm', '', d)} \
"

ROOTFS_POSTPROCESS_COMMAND += "rootfs_update_timestamp ; "

set_user_group:prepend() {
	export ENCRYPTED_PASSWORD=$(openssl passwd ${DEFAULT_PASSWORD})
	export ENCRYPTED_ROOT_PASSWORD=$(openssl passwd ${DEFAULT_ROOT_PASSWORD})
}

EXTRA_USERS_PARAMS = "\
	groupadd ${DEFAULT_GROUP_NAME}; \
	useradd --gid ${DEFAULT_GROUP_NAME} --groups audio,video,tty,disk,kmem,sudo --password '${ENCRYPTED_PASSWORD}' ${DEFAULT_USER_NAME}; \
	usermod --password '${ENCRYPTED_ROOT_PASSWORD}' root; \
"
