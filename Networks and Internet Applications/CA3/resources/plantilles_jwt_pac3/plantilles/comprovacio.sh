#!/bin/bash
DIR_BASE="resultats_"
mkdir -p $DIR_BASE

TOKEN=`./token.sh 2>$DIR_BASE/token_stderr.txt`

echo $TOKEN >$DIR_BASE/token.txt

./square.sh $TOKEN 2>$DIR_BASE/square_stderr.txt

echo ""



