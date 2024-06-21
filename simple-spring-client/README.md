## Overview

This repository provides a comprehensive set of code examples to make simple client requests from spring application to RESTful, GraphQL and gRPC Server. 

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6.0 or higher
- An IDE such as IntelliJ IDEA or Eclipse
- Postman

### Running Application
1. Set and export ***PROFILE_MODE***
    ```sh
    export PROFILE_MODE=local
    ```
2. Run on local
    ```sh
    mvn spring-boot:run
    ```
3. Or run on production
   * First, set env variables for ***RESTFUL_API_KEYSTORE*** and ***RESTFUL_API_KEYSTORE_PASSWORD*** 
    ```sh
   export RESTFUL_API_KEYSTORE=/etc/your_keystore.jks
   export RESTFUL_API_PASSWORD=yourkeystorepassword
   ```
   > if you don't have jks or not imported on jvm yet, you could find it the way to do that on [Working with Certificates and SSL](https://docs.oracle.com/cd/E19830-01/819-4712/ablqw/index.html).  
   * Then run spring application
   ```sh
    mvn spring-boot:run
    ```
   
### Running Unit Tests
```sh
mvn clean test
```