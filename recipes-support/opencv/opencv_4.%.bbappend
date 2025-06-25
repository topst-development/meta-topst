FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG:append:tcc805x = " opencl"
PACKAGECONFIG:remove = "eigen"
RRECOMMENDS:${PN}:tcc805x += "libopencl-telechips"
ALLOW_EMPTY:${PN}-src = "1"
