#!/bin/bash

env mvn -DgroupId=es.caib.pinbalmonitor -DartifactId=* versions:set -DnewVersion=$@  

