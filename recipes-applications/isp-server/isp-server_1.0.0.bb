SUMMARY = "TOPST ISP control HTTP server"
DESCRIPTION = "A lightweight C++ HTTP server for controlling ISP registers and dump operations"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "${TELECHIPS_TOPST_GIT}/isp-server.git;protocol=${TOPST_GIT_PROTOCOL};branch=${TOPST_BRANCH}"

TOPST_BRANCH:tcc805x = "${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'feature/d3g-ov5647', 'feature/d3g-imx219', d)}"
TOPST_BRANCH:tcc750x = "${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'feature/aig-ov5647', 'feature/aig-imx219', d)}"
SRCREV:tcc805x = "${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', '37c3e879099c2199086ccf7672313bcf87a7b646', '8fc93e90d30eb869a43a7e93a27f7b6eacaa574d', d)}"
SRCREV:tcc750x = "${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'fcc4d8b32209332582ef59e15c82cfef31099c85', 'c7fb111ff24f632726fba2e0e84c51ddaed9dcb1', d)}"

inherit cmake pkgconfig

RDEPENDS:${PN} += " nginx"
DEPENDS += " zlib virtual/kernel"

EXTRA_OECMAKE += "-DUSE_STATIC=OFF -DSIM=OFF"

S = "${WORKDIR}/git"

INSANE_SKIP:${PN} += "already-stripped"

do_install:tcc805x() {
	install -d ${D}${base_libdir}/firmware
	install -d ${D}${bindir}
	
	install -m 0755 ${B}/isp-server    ${D}${bindir}/

	if ${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'true', 'false', d)}; then
		install -m 755 ${THISDIR}/isp-server/isp_default_d3_ov5647.dump    ${D}${base_libdir}/firmware
	fi
	if ${@bb.utils.contains('TOPST_CAM_MODULE', 'imx219', 'true', 'false', d)}; then
		install -m 755 ${THISDIR}/isp-server/isp_default_d3_imx219.dump    ${D}${base_libdir}/firmware
	fi
}

do_install:tcc750x() {
	install -d ${D}${base_libdir}/firmware
	install -d ${D}${bindir}
	
	install -m 0755 ${B}/isp-server    ${D}${bindir}/

	if ${@bb.utils.contains('TOPST_CAM_MODULE', 'ov5647', 'true', 'false', d)}; then
		install -m 755 ${THISDIR}/isp-server/isp_default_ai_ov5647.dump    ${D}${base_libdir}/firmware
	fi
	if ${@bb.utils.contains('TOPST_CAM_MODULE', 'imx219', 'true', 'false', d)}; then
		install -m 755 ${THISDIR}/isp-server/isp_default_ai_imx219.dump    ${D}${base_libdir}/firmware
	fi
}

FILES:${PN} += "${base_libdir} ${bindir}"
