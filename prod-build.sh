#!/bin/bash

rm -rf static
gulp build
rm -rf solution/src/main/resources/static
cp -r static solution/src/main/resources
cd solution
./gradlew build