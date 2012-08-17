#!/bin/sh
H2=/Users/seungbeomi/.m2/repository/com/h2database/h2/1.3.164/h2-1.3.164.jar
SCRIPT=script.sql
URL=jdbc:h2:~/test

java -cp $H2 org.h2.tools.RunScript -url $URL -user sa -script $SCRIPT -showResults
