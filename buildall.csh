#!/bin/csh
#source j5
cd stun
ant clean
cd ../common
ant clean
cd ../voip
ant clean
cd ../softphone
ant clean
java -version
cd ../voicelib
ant clean
cd ../stun
ant jar
cd ../common
ant jar
cd ../voip
ant jar
cd ../softphone
ant jar
cd ../voicelib
ant jar
#cd ../bridgemonitor
#ant jar
cd ..
ant dist
