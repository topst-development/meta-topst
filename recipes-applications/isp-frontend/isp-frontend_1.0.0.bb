SUMMARY = "ISP Frontend Web UI"
LICENSE = "Telechips"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/Telechips;md5=bf748a8e7a397a71f48f21715741f8a1"

SRC_URI = "file://dist_d3.tar.gz \
	file://dist_ai.tar.gz \
"

RDEPENDS:${PN} += "nginx"

do_install:tcc805x() {
	install -d ${D}/var/www/localhost/isp-tool
	install -d ${D}/etc/nginx/sites-available

	install -m 0644 ${THISDIR}/isp-frontend/default_server ${D}/etc/nginx/sites-available/

	tar -xzf ${THISDIR}/isp-frontend/dist_d3.tar.gz -C ${WORKDIR}
	cp -r ${WORKDIR}/dist/* ${D}/var/www/localhost/isp-tool/
}

do_install:tcc750x() {
	install -d ${D}/var/www/localhost/isp-tool
	install -d ${D}/etc/nginx/sites-available

	install -m 0644 ${THISDIR}/isp-frontend/default_server ${D}/etc/nginx/sites-available/

	tar -xzf ${THISDIR}/isp-frontend/dist_ai.tar.gz -C ${WORKDIR}
	cp -r ${WORKDIR}/dist/* ${D}/var/www/localhost/isp-tool/
}

FILES:${PN} += "/var/www/localhost/isp-tool /etc/nginx/sites-available"

