# springboot2-webservice

[테스트 링크](#안녕하세요)

스프링 부트 책의 기본 예제를 따라하며 기본 개념들을 익히며 만든 프로젝트 입니다.

- 프로젝트 후기 : [[Spring] 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 후기(+삽질)](https://jwkim96.tistory.com/190)
- 공부한 책 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 (저자 - 이동욱)

## 프로젝트 실행 방법

```
1. 프로젝트 클론
git clone https://github.com/jinan159/springboot2-webservice

2. 빌드(프로젝트 경로로 이동)
cd springboot2-webservice
./gradlew build

3. 실행
java -jar \                  
-Dspring.config.location=classpath:/application.properties,src/main/resources/application-oauth.properties, \
build/libs/springboot2-webservice-1.2-SNAPSHOT.jar
```

## 사용한 기술들
- openjdk 1.8.0_292
- SpringBoot 2.1.9.RELEASE
  - JPA
  - JUnit4
  - H2 Database
- Docker
  - MariaDB
  - Jenkins
  - Nginx
- Git
  - Github Webhook
- Slack (Jenkins build result notification)

## 아키텍처

### 프로젝트 아키텍처

![](https://images.velog.io/images/jinan159/post/101c5cd1-ac73-4b63-b3f0-2044a4faa866/image.png)

### 배포 프로세스(Nginx 프록시를 활용한 무중단 배포)

![](https://images.velog.io/images/jinan159/post/4df20c60-1f72-40e7-9fed-6f2cb1b80222/image.png)

1. Jenkins에서 Github webhook 수신 

![](https://images.velog.io/images/jinan159/post/4fe77f4f-ba65-4fd1-a890-5cdb3aaee002/image.png)

2. 프로젝트 빌드 및 패키징

패키징 스크립트 : [make-deploy-package.sh](https://github.com/jinan159/springboot2-webservice/blob/master/scripts/jenkins/make-deploy-package.sh)

![](https://images.velog.io/images/jinan159/post/3c8596fc-0940-4a55-98fe-d0ed42d66fec/image.png)

3. 서비스 컨테이너로 ssh 원격전송 및 배포 스크립트 실행

무중단 배포 실행 스크립트 : [zero-downtime-deploy.sh](https://github.com/jinan159/springboot2-webservice/blob/master/scripts/service/zero-downtime-deploy.sh)

![](https://images.velog.io/images/jinan159/post/7638e006-87b1-4cda-bf4e-66b69cb156cb/image.png)

#### 안녕하세요

안녕하세요
