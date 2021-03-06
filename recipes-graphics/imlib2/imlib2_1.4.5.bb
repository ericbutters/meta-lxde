DESCRIPTION = "A graphic library for file loading, saving, rendering, and manipulation."
LICENSE = "MIT | BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35"
DEPENDS = "freetype libpng jpeg giflib tiff zlib bzip2 virtual/libx11 libxext libid3tag"
PROVIDES = "virtual/imlib2"

PV = "1.4.5"
PR = "r0"

SRC_URI="http://downloads.sourceforge.net/enlightenment/imlib2-${PV}.tar.bz2"
SRC_URI[md5sum] = "859e5fede51ec819f4314eee11da3ea5"

inherit autotools

EXTRA_OECONF = "\
	--with-x \
	--x-includes=${STAGING_INCDIR} \
	--x-libraries=${STAGING_LIBDIR} \
    --with-jpeg \
    --with-pmg \
    --with-tiff \
    --with-gif \
    --with-zlib \
    --with-bzip2 \
    --with-id3 \
    "

# TODO: Use more fine granular version
#OE_LT_RPATH_ALLOW=":${libdir}/imlib2/loaders:${libdir}/imlib2/filters:"
#OE_LT_RPATH_ALLOW = "any"
#OE_LT_RPATH_ALLOW[export]="1"

do_install_append() {
    install -m 0755 imlib2-config ${STAGING_BINDIR_CROSS}
}

PACKAGES =+ "\
	${PN}-loaders-dbg \
    ${PN}-filters-dbg \
    ${PN}-loaders-dev \
    ${PN}-filters-dev \
    ${PN}-tools-dbg \
    ${PN}-loaders \
    ${PN}-filters \
    ${PN}-tools"

FILES_${PN} = "${libdir}/lib*.so.* ${libdir}/imlib2/*/*.so"
FILES_${PN}-dbg = "${libdir}/.debug/ ${bindir}/.debug/ ${prefix}/src/debug/"
FILES_${PN}-dev += "${bindir}/imlib2-config ${libdir}/*.so ${includedir}"
FILES_${PN}-tools = "${bindir} ${datadir}/${PN}"
FILES_${PN}-loaders = "${libdir}/imlib2/loaders/*.so"
FILES_${PN}-filters = "${libdir}/imlib2/filters/*.so"
FILES_${PN}-tools-dbg += "${bindir}/.debug"
FILES_${PN}-loaders-dbg += "${libdir}/imlib2/loaders/.debug"
FILES_${PN}-filters-dbg += "${libdir}/imlib2/filters/.debug"
FILES_${PN}-loaders-dev += "${libdir}/imlib2/loaders/*.la"
FILES_${PN}-filters-dev += "${libdir}/imlib2/filters/*.la"
