do_install:append() {
	echo "GSK_RENDERER=cairo" >> ${D}${sysconfdir}/environment
	echo "CLUTTER_PAINT=disable-clipped-redraws" >> ${D}${sysconfdir}/environment
}
