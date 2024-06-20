## Overview
This repository provides examples of setting and interacting with OpenTelemetry then export the data to desired traces, logs, and metrics tools. 

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse
- Open Telemetry
- Docker
- Jaeger
- Zipkin
- Tempo
- Loki
- Prometheus
- Grafana

### Running Application
1. Set and export ***PROFILE_MODE***
    ```sh
    export PROFILE_MODE=prod
   ```
2. Then run application
   * Run within a docker container
    ```sh
   # create a new container and run on background   
   docker compose -f docker-compose.yml -f docker-compose-prod.yml up -d
   # delete container
   docker compose -f docker-compose.yml -f docker-compose-prod.yml down
   ```
   * Run directly on terminal
    ```sh
    mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE_MODE
    ```
3. Or run on local
   * To run the application locally, you can use Docker Compose. The necessary configuration files (***docker-compose.yml*** and ***docker-compose-local.yml***) are already provided in this repository.
   ```sh
   # create a new container and run on background
   docker compose -f docker-compose.yml -f docker-compose-local.yml up -d
   # delete container
   docker compose -f docker-compose.yml -f docker-compose-local.yml down
    ```
   * To change the configurations of Tempo, Prometheus, Grafana, or the OpenTelemetry Collector, navigate to the following directories:
     - ***./localopentelemetry/grafana***
     - ***./localopentelemetry/tempo***
     - ***./localopentelemetry/prometheus***
     - ***./localopentelemetry/collector***