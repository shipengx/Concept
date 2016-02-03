#!/bin/bash
#
#

mvn clean package

java -cp ./target/TwitterPublicStream-1.0-SNAPSHOT-jar-with-dependencies.jar com.shipeng.TwitterStreamServer


