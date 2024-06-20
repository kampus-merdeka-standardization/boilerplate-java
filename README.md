# Boilerplate Java

## Overview

This repository provides a comprehensive set of boilerplate code examples for running the Spring Framework, related libraries, and other essential tools. It is designed to help developers quickly set up and understand various Spring functionalities and integrations.

## Repository Structure

The repository is organized into multiple modules, each demonstrating different aspects of the Spring Framework and its ecosystem:

- **[simple-spring-app](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/main/simple-spring-app)**: Basic Spring application setup and demonstrates how to implement RESTful, GraphQL, and gRPC API on Spring application.
- **[simple-spring-client](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/main/simple-spring-client)**: Examples of make client requests for RESTful, GraphQL, and gRPC Server.
- **[simple-spring-config](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/main/simple-spring-config)**: Integration with Cloud Consul to manage configurations.
- **[simple-spring-database](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/11709fd15a1f9db114051ba4ec56f7d942909254/simple-spring-database)**: Reactive Database interactions and ORM setup.
- **[simple-spring-logging](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/main/simple-spring-logging)**: Logging configurations and examples.
- **[simple-spring-monitoring](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/11709fd15a1f9db114051ba4ec56f7d942909254/simple-spring-monitoring)**: Integration with Open Telemetry.
- **[simple-spring-rest-server](https://github.com/kampus-merdeka-standardization/boilerplate-java/tree/11709fd15a1f9db114051ba4ec56f7d942909254/simple-spring-rest-server)**: RESTful server setup and examples.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/kampus-merdeka-standardization/boilerplate-java.git
    cd boilerplate-java
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Import the project into your IDE.

### Running Examples

Each module is a standalone Spring Boot application. To run an example, navigate to the module directory and use Maven to start the application:

```sh
cd simple-spring-app
mvn spring-boot:run
```

Replace `simple-spring-app` with the desired module directory.

## Features

- **Transactional, Optimistic Locking and Pessimistic Locking**: Demonstrates how to handle transactions, optimistic and pessimistic locking in Spring applications.
- **Cloud Consul Integration**: Shows how to integrate configurations from Cloud Consul on Spring Application by using Spring Cloud Config.
- **Reactive Database Interactions**: Provides examples of setting up and interacting with databases using Spring Data R2DBC.
- **Logging**: Configures and demonstrates logging using Logback.
- **Monitoring**: Provides examples of setting and interacting with OpenTelemetry then export the data to desired traces, logs, and metrics tools.
- **RESTful Services**: Sets up and provides examples of RESTful web services using Spring Web Flux.
- **Unit Test**: Shows how to make unit tests on Spring Application.