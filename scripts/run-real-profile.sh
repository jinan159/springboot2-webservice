# dev repository script
nohup java -jar \
-Dspring.config.location=classpath:/application-real.properties,src/main/resources/application-real-db.properties,src/main/resources/application-oauth.properties, \
-Dspring.profiles.active=real \
build/libs/springboot2-webservice-1.0-SNAPSHOT.jar > nohup.out &


#nohup java -jar \
#-Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,config/application-real-db.properties,config/application-oauth.properties, \
#-Dspring.profiles.active=real \
#springboot2-webservice-1.1-SNAPSHOT.jar > nohup-real.out 2>&1 &