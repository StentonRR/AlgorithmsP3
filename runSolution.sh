#!/bin/bash

solutionFile=`find . -name "*linux_intel"`
inputFiles="./input/*"
outputDir="./output"


for file in $inputFiles
do
	fileOut=${file:8}
	"$solutionFile" < "$file" > "$outputDir"/"$fileOut".solution
done	
