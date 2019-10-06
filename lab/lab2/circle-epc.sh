#!/bin/bash

java -jar RemoteCar.jar < circle-epc > circle-output

rg < circle-output '[A-Z].*$' -o
