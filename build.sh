#!/bin/bash

rm -rf *.class
javac -cp "./:sqlite-jdbc-3.43.2.2.jar:./sqlite-jdbc-3.7.2.jar" $1