#!/bin/bash


APP_HOME=/home/webservice/app

PROJECT_NAME=springboot2-webservice
JAR_DIR=$APP_HOME/jar
CONFIG_DIR=$APP_HOME/config

echo ">>>> START DEPLOY SERVER <<<<"

echo ">> Check [$PROJECT_NAME] running state.";
CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep jar | awk '{print $1}')

if [ -z "$CURRENT_PID" ]; then
  echo ">> [$PROJECT_NAME] is not running."
else
  echo ">> Kill application for deploy(PID:$CURRENT_PID)"
  echo ">> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo ">> Start deploy [$PROJECT_NAME]"

echo ">> Copy jar file to app main"
JAR_NAME=$(ls -tf $JAR_DIR/*.jar | tail -n 1)
cp $JAR_NAME $APP_HOME

JAR_NAME=$(ls -tf $APP_HOME/*.jar | tail -n 1)

echo ">> Deploy target : [$JAR_NAME]"

echo ">> Add execution permission to [$JAR_NAME]"
chmod +x $JAR_NAME

echo ">> Run [$JAR_NAME]"
nohup java -jar \
-Dspring.config.location=classpath:/application-real.properties,$CONFIG_DIR/application-real-db.properties,$CONFIG_DIR/application-oauth.properties  \
-Dspring.profiles.active=real \
$JAR_NAME > nohup.out &

echo ">>>> END DEPLOY SERVER <<<<"

