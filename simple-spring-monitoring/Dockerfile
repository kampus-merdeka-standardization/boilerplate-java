FROM openjdk:17-jdk-oracle
ARG APP_DIR=app
WORKDIR /$APP_DIR
COPY target/simple-spring-monitoring-0.0.1-SNAPSHOT.jar simple-spring-monitoring.jar
ENV PROFILE_MODE=prod
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=$PROFILE_MODE -jar simple-spring-monitoring.jar"]