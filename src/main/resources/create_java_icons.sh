#!/bin/bash

export PROJECT="ROM Metadata Reader"
export ICONDIR="./img/app-icons"
export ORIGICON="./$PROJECT.png"

rm -rf "$ICONDIR"
mkdir "$ICONDIR"

cp "./$PROJECT 16x16.png" "$ICONDIR/icon_16x16.png"
cp "./$PROJECT 32x32.png" "$ICONDIR/icon_32x32.png"
sips -z 20 20 "./$PROJECT 32x32.png" --out "$ICONDIR/icon_20x20.png";

# normal screen icons
for SIZE in 40 44 64 128 256 512; do
sips -z $SIZE $SIZE "$ORIGICON" --out "$ICONDIR/icon_${SIZE}x${SIZE}.png" ;
done

echo "Done"