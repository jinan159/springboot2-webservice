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

# Include profile.sh
source $TASK_DIR/profile.sh

# Get idle PORT
IDLE_PORT=$(find_idle_port)
echo "> Get idle port $IDLE_PORT"

# Get idle PROFILE
IDLE_PROFILE=$(find_idle_profile)
echo "> Get idle profile $IDLE_PORT"

# stop unused service
echo "> timeout 60s $TASK_DIR/stop.sh"
timeout 60s $TASK_DIR/stop.sh $IDLE_PORT

# start new version of server
echo "> timeout 60s $TASK_DIR/start.sh"
timeout 60s $TASK_DIR/start.sh $IDLE_PROFILE

# health check new version of service
echo "> timeout 60s $TASK_DIR/health.sh"
timeout 60s $TASK_DIR/health.sh $IDLE_PORT

echo ">>>> END ZERO DOWNTIME DELPOY SCRIPT <<<<"