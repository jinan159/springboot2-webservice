#!/bin/bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/switch.sh

IDLE_PORT=${1}

echo ">>>> START HEALTH CHECK SCRIPT <<<<"

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile "
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  # check 'rea' is included
  if [ ${UP_COUNT} -ge 1 ]
  then
      echo "> Health check SUCCESS"
      switch_proxy
      break
  else
      echo "> Health check UNSTABLE"
      echo "> Health check: ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "> Health check FAILED"
    echo "> Deployment is finished with out nginx forwarding configuration."
    exit 1
  fi

  echo "> Health check failed. retry..."
  sleep 10
done

echo ">>>> END HEALTH CHECK SCRIPT <<<<"