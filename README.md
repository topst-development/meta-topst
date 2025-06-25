meta-topst, the Yocto layer for Telechips TOPST Linux SDK
===================================================================

This layer's purpose is to add Telechips TOPST Linux SDK support when
used with Poky.  The goal is to make the Telechips TOPST Linux SDK

Supported Machines
------------------

We do smoke test the builds of the machine that we currently support:

* D3 TOPST Board        - emulated machine: d3-topst-main
* D5 TOPST Board 		- emulated machine: d5-topst-main

How to enable usb power(vbus)
----------------------------------------------
We turned off the usb power initially then turn on usb power when excute enable-removable-disk.sh

 > The usb power will turn on when active enbale-removable-disk service(meta-topst-bsp/recipes-core/tc-enable-removable-disk).


Build a Telechips TOPST Linux SDK image
----------------------------------------------

You can build a Telechips TOPST SDK image using the following steps:
If you want more information for build, you can refer to '' document

1. Set-up build envrionment and Choose MACHINE
   > $ source meta-topst/topst-build.sh
   Choose MACHINE
     1. d3-topst-main
     2. d5-topst-main
   select number(1-2) => 1
   machine(d3-topst-main) graphic backend(wayland) selected.
   ...
   ...

2. Modify local.conf(default value)
   > $ vi conf/local.conf
   a. set numbers of thread : BB_NUMBER_THREADS(8)
   b. set parallel make : PARALLEL_MAKE(16)
   c. additional install packages : CORE_IMAGE_EXTRA_INSTALL
      > you can install extra packages to automotive-linux-platform-image using CORE_IMAGE_EXTRA_INSTALL
   d. combinations : TOPST_FEATURES
      > with-subcore : support subcore
      > gpu-vz : support gpu virtualization

3. Select Graphics System and Qt Platform Abstraction using conf/local.conf
   a. Qt5/Wayland(default)
   b. Qt5/EGLFS
      > DISTRO_FEATURES:remove = "wayland"

4. Build telechips-topst-image including Telechips TOPST Linux SDK components
   > $ bitbake telechips-topst-image

5. Deploy images: build/topst/tmp/deploy/images/machine
    - d3-topst
       > boot loader : ca72_bl3.rom
       > kernel : tc-boot-machine.img
       > dtb : tcc8050-topst-d3.dtb
       > rootfs : telechips-topst-image-machine.ext4(read/write or read-only, default is read-only)

    - d5-topst
     > boot-firmware
       > boot loader : ap0_bl3.rom
       > kernel : tc-boot-machine.img
       > dtb : tcc8070-topst-d5.dtb
       > rootfs : telechips-topst-image-machine.ext4(read/write or read-only, default is read-only)

6. To login use these credentials:
   > User - root
   > Password - root
