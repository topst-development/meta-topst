do_install:append() {
	sed -i "s/^\(Exec.*chromium\) %U/\1 --user-data-dir=.cache\/chromium --no-sandbox %U/g" ${D}${datadir}/applications/chromium.desktop
}
