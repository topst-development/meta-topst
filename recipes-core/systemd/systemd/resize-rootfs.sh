#!/bin/sh

DEVICE="/dev/disk/by-partlabel/system"
REAL_DEV=$(readlink -f "$DEVICE")

PART_SIZE=$(blockdev --getsize64 "$REAL_DEV")
FS_SIZE=$(dumpe2fs -h "$REAL_DEV" 2>/dev/null | awk '/Block count/ {bc=$3} /Block size/ {bs=$3} END {print bc * bs}')

if [ "$PART_SIZE" -gt "$FS_SIZE" ]; then
	echo "Resizing filesystem on $REAL_DEV..."
	resize2fs "$REAL_DEV"
else
	echo "Filesystem already uses full partition."
fi
