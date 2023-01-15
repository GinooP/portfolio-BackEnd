FROM amazoncorretto:11-alpine-jdk
MAINTAINER GGP
COPY target/ggp-0.0.1-SNAPSHOT.jar ggp-app.jar
ENTRYPOINT ["java","-jar","/ggp-app.jar"]
