CHECK_SEC_REPORT_FILE_NAME = "${TELECHIPS_LINUX_SDK_VERSION}_${MACHINE}_${DATE}.csv"

do_chk_security() {
	rm -f ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	echo "RELRO,STACK CANARY,NX,PIE,RPATH,RUNPATH,Symbols,FORTIFY,Fortified,Fortifiable,Filename" > ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	${STAGING_BINDIR_NATIVE}/checksec \
		--output=csv \
		--dir=${IMAGE_ROOTFS} \
		>> ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	sed -i 's%${IMAGE_ROOTFS}%%g'  ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
}

do_chk_security[depends] += "checksec-native:do_populate_sysroot"
do_chk_security[depends] += "${IMAGE_BASENAME}:do_rootfs"
addtask chk_security
