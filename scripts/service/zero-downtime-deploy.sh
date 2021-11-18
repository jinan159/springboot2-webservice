#!/bin/bash

# authorize execute permission to task scripts
# this script will be authorized execute permission by jenkins

echo ">>>> START ZERO DOWNTIME DELPOY SCRIPT <<<<"

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
TASK_DIR=${ABSDIR}/task

echo "> Authorize execute permission to scripts"
chmod +x $TASK_DIR/health.sh
chmod +x $TASK_DIR/profile.sh
chmod +x $TASK_DIR/start.sh
chmod +x $TASK_DIR/stop.sh
chmod +x $TASK_DIR/switch.sh

# stop unused service
echo "> timeout 60s $TASK_DIR/stop.sh"
timeout 60s $TASK_DIR/stop.sh

# start new version of server
echo "> timeout 60s $TASK_DIR/start.sh"
timeout 60s $TASK_DIR/start.sh

# health check new version of service
echo "> timeout 60s $TASK_DIR/health.sh"
timeout 60s $TASK_DIR/health.sh

echo ">>>> END ZERO DOWNTIME DELPOY SCRIPT <<<<"