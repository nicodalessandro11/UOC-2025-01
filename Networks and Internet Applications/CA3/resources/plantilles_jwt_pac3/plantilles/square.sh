#!/bin/bash
TOKEN=$1

curl -X GET http://labxarxes.techlab.uoc.edu:8095/dslab-api/xai/square/2 \
  -H "Authorization: Bearer $TOKEN"




