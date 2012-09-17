#!/bin/sh
H2=D:/DEV2-win64/workspace/development/java/spring-hibernate/lib/h2-1.3.164.jar
SCRIPT=script.sql
URL=jdbc:h2:~/test

java -cp $H2 org.h2.tools.RunScript -url $URL -user sa -script $SCRIPT -showResults
