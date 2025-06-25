SUMMARY = "Telechips Network packages for Linux/GNU runtime images"
DESCRIPTION = "The minimal set of packages required to network system"
PR = "r17"

inherit packagegroup

RDEPENDS:${PN} = "\
    packagegroup-core-ssh-openssh \
    ${@bb.utils.contains("DISTRO_FEATURES", "sysvinit", "resolvconf ", "", d)} \
    ethtool \
    net-tools \
    iproute2 \
	rsync \
    ${@bb.utils.contains('USE_IP_NETFILTER', '1', 'iptables', '', d)} \
"
