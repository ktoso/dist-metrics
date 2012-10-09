#!/bin/sh

cd common/src/main/proto

echo "Generate sources for: Java..."
protoc -I=. --java_out=../java *
echo "Done"

echo "Generate sources for: Go..."
protoc -I=. --go_out=../go *
echo "Done"

echo "Generate sources for: Python..."
protoc -I=. --python_out=../python *
echo "Done"

cd ../../..
