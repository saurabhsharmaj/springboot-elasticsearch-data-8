FROM openjdk:17-alpine
MAINTAINER saurabhsharmaj
COPY target/ElasticwithSpringBoot3-0.0.1-SNAPSHOT.jar elastic-repo-1.0.0.jar
ENTRYPOINT ["java","-jar","/elastic-repo-1.0.0.jar"]