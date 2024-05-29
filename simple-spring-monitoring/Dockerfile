FROM openjdk:17-oracle

COPY target/simple-spring-monitoring-0.0.1-SNAPSHOT.jar simple-spring-monitoring-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/spring-cloud-open-telemetry.jar"]