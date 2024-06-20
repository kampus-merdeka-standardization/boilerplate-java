## Overview

This repository offers a comprehensive collection of code examples for building a RESTful API using Spring WebFlux. It demonstrates the common HTTP methods employed in RESTful APIs, providing clear and practical guidance for developers.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse
- Postman
- cURL

### Running Application
1. Run spring application
   ```sh
   mvn spring-boot:run
   ```
2. Make a client request
   To make client requests, we could use curl or tools like Postman.
* **POST**
   ```sh
  curl --location 'localhost:8080/hello' \
       --header 'Content-Type: application/json' \
       --data '{
          "name": "cisnux",
          "age": 12
       }'
  ```
* **GET**
   ```sh
  curl --location 'localhost:8080/hello/cisnux'
  ```
* **PUT**
   ```sh
  curl --location --request PUT 'localhost:8080/hello' \
    --header 'Content-Type: application/json' \
    --data '{
      "current_name": "cisnux",
      "new_name": "fajra"
    }'
  ```
* **PATCH**
   ```sh
  curl --location --request PATCH 'localhost:8080/hello/e28952cc-c688-4d31-b031-f757f5f9f201' \
    --header 'Content-Type: application/json' \
    --data '{
        "new_name": "risqulla"
    }'
  ```
* **DELETE**
   ```sh
  curl --location --request DELETE 'localhost:8080/hello/e28952cc-c688-4d31-b031-f757f5f9f201'
  ```
### Running Unit Tests
```sh
mvn clean test
```