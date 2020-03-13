#!/bin/bash

#driverFile=`find . -name "Driver_prj*"`
driverFile="Driver_prj2"

inputFiles="./input/*"
outputDir="./output"


for file in $inputFiles
do
        fileOut=${file:8}
        java -enableassertions "$driverFile" < "$file" > "$outputDir"/"$fileOut".test
done

