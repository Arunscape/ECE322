#!/bin/bash

java -jar Drone.jar < drone-epc > drone-output

rg < drone-output '(F|S|E).*$' -o
