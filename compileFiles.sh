#!/bin/bash

# Compile all .java files
for file in *.java
do
	javac "$file"
done
