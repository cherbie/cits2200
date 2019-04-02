#!/bin/bash
success="Script complete"
#----------------- Command Line Calls
clear
make ListBlock
if [ $? == 0 ]; then
	echo 1;
	java -cp CITS2200.jar:. ListBlockTest 10
	echo $(success)
else
	echo "not successful"
fi
