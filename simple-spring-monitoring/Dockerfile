FROM openjdk:17-jdk-oracle
ARG APP_DIR=app
WORKDIR /$APP_DIR
COPY target/simple-spring-monitoring-0.0.1-SNAPSHOT.jar simple-spring-monitoring.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","simple-spring-monitoring.jar"]