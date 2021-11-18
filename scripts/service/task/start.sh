#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

APP_HOME=/home/webservice/app
PROJECT_NAME=springboot2-webservice

echo ">>>> START SERVER START SCRIPT <<<<"

echo "> Copy build files"
echo "> cp $APP_HOME/jar/*.jar $APP_HOME/"

cp $APP_HOME/jar/*.jar $APP_HOME/

echo "> Deploy [$PROJECT_NAME]"
JAR_NAME=$(ls -tr $APP_HOME/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> Authorize execute permission on $JAR_NAME"

chmod +x $JAR_NAME

echo "> execute $JAR_NAME "

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME is launched for profile=$IDLE_PROFILE"
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/webservice/app/config/application-oauth.properties,/home/webservice/app/config/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $APP_HOME/nohup.out 2>&1 &

echo ">>>> END SERVER START SCRIPT <<<<"