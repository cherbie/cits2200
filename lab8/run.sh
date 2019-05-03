#!/bin/bash
###
clear
echo "Starting execution."
java -cp CITS2200.jar:. PathTest F "sample_graph.in" 0
echo "Now Mine"
java -cp CITS2200.jar:. PathImpTest.