# authorize execute permission to task scripts
# this script will be authorized execute permission by jenkins

echo ">>>> START ZERO DOWNTIME DELPOY SCRIPT <<<<"

echo "> Authorize execute permission to scripts"
chmod +x ./task/health.sh
chmod +x ./task/profile.sh
chmod +x ./task/start.sh
chmod +x ./task/stop.sh
chmod +x ./task/switch.sh

# stop unused service
echo "> timeout 60s ./task/stop.sh"
timeout 60s ./task/stop.sh

# start new version of server
echo "> timeout 60s ./task/start.sh"
timeout 60s ./task/start.sh

# health check new version of service
echo "> timeout 60s ./task/health.sh"
timeout 60s ./task/health.sh

echo ">>>> END ZERO DOWNTIME DELPOY SCRIPT <<<<"