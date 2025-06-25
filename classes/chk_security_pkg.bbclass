CHECK_SEC_REPORT_FILE_NAME = "${PN}_security_flags_report.csv"

do_chk_security_pkg() {
	rm -f ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	echo "RELRO,STACK CANARY,NX,PIE,RPATH,RUNPATH,Symbols,FORTIFY,Fortified,Fortifiable,Filename" > ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	${STAGING_BINDIR_NATIVE}/checksec \
		--output=csv \
		--dir=${WORKDIR}/image \
		>> ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
	sed -i 's%${WORKDIR}/image%%g'  ${TOPDIR}/${CHECK_SEC_REPORT_FILE_NAME}
}

do_chk_security_pkg[depends] += "checksec-native:do_populate_sysroot"
addtask chk_security_pkg after do_build
