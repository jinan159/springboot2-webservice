#!/bin/bash

echo ">>>> START SERVER STOP SCRIPT <<<<"

IDLE_PORT=$1

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