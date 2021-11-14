nohup java -jar \
-Dspring.config.location=classpath:/application-real.properties,classpath:/application-real-db.properties,classpath:/application-oauth.properties \
-Dspring.profiles.active=real \
./build/libs/springboot2-webservice-1.0-SNAPSHOT.jar > nohup.out