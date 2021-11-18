#!/usr/bin/env bash

echo ">>>> START SERVER STOP SCRIPT <<<<"

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> Get PID of the program using $IDLE_PORT port."
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> No programs are running."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi

echo ">>>> END SERVER STOP SCRIPT <<<<"