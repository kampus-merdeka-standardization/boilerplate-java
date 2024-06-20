## Overview

This repository shows how to integrate configurations from Cloud Consul on Spring Application by using Spring Cloud Config.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse
- Cloud Consul

### Running Application
1. Set ***PROFILE_MODE***
    ```sh
    export PROFILE_MODE=local
   ```
2. Run on local mode
    ```sh
    export APP_NAME=simple-spring-config
    export APP_PORT=3220
    export DB_HOST=localhost:3306
    export DB_USERNAME=root
    export DB_PASSWORD=admin
    mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE_MODE
    ```
3. Or run on production
    ```sh
    mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE_MODE
    ```
### Running Unit Tests
```sh
mvn clean test
```