#!/bin/bash

inputDir="./input"
outputDir="./output"

if [ ! -d "$inputDir" ]
then
	`mkdir $inputDir`
fi

if [ ! -d "$outputDir" ]
then
	`mkdir $outputDir`
fi
