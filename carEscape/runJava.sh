#!/bin/bash

# Script to compile and run

mkdir classes
javac -cp ./classes -d ./classes ./src/*.java 	#compiles all .java files

echo "Source code compiled."

cd classes
java carEscape.GUIMain
