#!/bin/sh
H2=h2-1.2.143.jar
SCRIPT=test.script
EMBEDDED_MODE=jdbc:h2:~/test
SERVER_MODE=jdbc:h2:tcp://localhost/~/test

java -cp $H2 org.h2.tools.RunScript -url $SERVER_MODE -user sa -script $SCRIPT -showResults
