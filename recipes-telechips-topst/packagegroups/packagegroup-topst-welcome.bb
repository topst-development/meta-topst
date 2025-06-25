SUMMARY = "TOPST Welcome (intro) packages for Linux/GNU runtime images"
DESCRIPTION = "The set of packages for welcome webapp for TOPST"

inherit packagegroup

RDEPENDS:${PN} = " \
	topst-nano-server \
	topst-welcome \
"
