#!/bin/bash

PROJECT_NAME=springboot2-webservice
JENKINS_HOME=/var/jenkins_home

WORKSPACE_ROOT=$JENKINS_HOME/workspace/$PROJECT_NAME
BEFORE_DEPLOY=$WORKSPACE_ROOT/before-deploy

echo ">>>> START MAKE DEPLOY PACKAGE <<<<"

if [ ! -d "$BEFORE_DEPLOY/jar" ]; then
  echo ">> Make $BEFORE_DEPLOY/jar directory"
  mkdir -p $BEFORE_DEPLOY/jar
fi

if [ ! -d "$BEFORE_DEPLOY/scripts/service" ]; then
  echo ">> Make $BEFORE_DEPLOY/scripts/service directory"
  mkdir -p $BEFORE_DEPLOY/scripts/service
fi

echo ">> Copy jar file."
cp $WORKSPACE_ROOT/build/libs/*.jar $BEFORE_DEPLOY/jar

echo ">> Copy service deploy scripts."
cp $WORKSPACE_ROOT/scripts/service/*.sh $BEFORE_DEPLOY/scripts/service

echo ">> Move to before-deploy directory."
cd $BEFORE_DEPLOY
echo $(pwd)

echo ">> Zip scripts and jar to $PROJECT_NAME.tar.gz"
tar -zcvf $PROJECT_NAME.tar.gz ./scripts ./jar

echo ">>>> END MAKE DEPLOY PACKAGE <<<<"