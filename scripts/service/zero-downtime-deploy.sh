# authorize execute permission to task scripts
# this script will be authorized execute permission by jenkins

echo ">>>> START ZERO DOWNTIME DELPOY SCRIPT <<<<"

# stop unused service
timeout 60s ./task/stop.sh

# start new version of server
timeout 60s ./task/start.sh

# health check new version of service
timeout 60s ./task/health.sh

echo ">>>> END ZERO DOWNTIME DELPOY SCRIPT <<<<"