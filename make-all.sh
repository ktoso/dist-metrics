#!/bin/sh

function building() {
  echo "====== Building: $1 ======"
}

building "Sensor [go]"

cd sensor
go clean
go build
cd ..


building "Monitor [scala]"
cd monitor
sbt proguard
cd ..

building "Client [python]"


echo "====== Done! ======"
