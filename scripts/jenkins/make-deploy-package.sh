#!/bin/bash

PROJECT_NAME=springboot2-webservice
JENKINS_HOME=/var/jenkins_home

BEFORE_DEPLOY=$JENKINS_HOME/before-deploy
WORKSPACE_ROOT=$JENKINS_HOME/workspace/$PROJECT_NAME

# make directory before-deploy/script/service
if [! -d "$BEFORE_DEPLOY/jar"]; then
  mkdir -p $BEFORE_DEPLOY/jar
fi

if [! -d "$BEFORE_DEPLOY/scripts/service"]; then
  mkdir -p $BEFORE_DEPLOY/scripts/service
fi

echo ">> Copy jar file."
cp $WORKSPACE_ROOT/build/libs/*.jar $BEFORE_DEPLOY/jar

echo ">> Copy service deploy scripts."
cp $WORKSPACE_ROOT/scripts/service/*.sh $BEFORE_DEPLOY/scripts/service

echo ">> Move to before-deploy directory."
cd $BEFORE_DEPLOY
echo $(pwd)

echo ">> Archive $PROJECT_NAME.tar.gz"
tar -zcvf $PROJECT_NAME.tar.gz $BEFORE_DEPLOY