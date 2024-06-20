## Overview

This repository provides a comprehensive set of code examples to make simple RESTful, GraphQL and gRPC API.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse

### Running Examples

Each module is a standalone Spring Boot application. To run an example, navigate to the module directory and use Maven to start the application:
1. Run spring application
   ```sh
   mvn spring-boot:run
   ```
2. Make a client request
   * RESTful API\
     for RESTful API to make a client request, we could use curl or tools like Postman.
      ```sh
     curl --location 'localhost:8080/ping'
     ```
   * GraphQL API\
     for GraphQL API to make a client request, we could use curl or tools like Postman.
     ```sh
     curl -X POST \
     -H "Content-Type: application/json" \
     -d '{"query": "{ message }"}' \
     http://localhost:8080/graphql
      ```
   * gRPC API
     for GraphQL API to make a client request, we could use grpcurl or tools like Postman.
     ```sh
     grpcurl -plaintext -d '{}' \
     localhost:9090 \
     t.it.simplespringapp.services.PingService/Ping
      ```
### Running Unit Tests

Each module is a standalone Spring Boot application. To run an example, navigate to the module directory and use Maven to start the application:

```sh
mvn test
```