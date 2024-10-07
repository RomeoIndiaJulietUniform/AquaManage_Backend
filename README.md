# AquaManage Backend

AquaManage is a backend application designed for managing aquariums and related services using a microservices architecture. The application is built using Spring Boot and Java, with PostgreSQL as the database, and is containerized using Docker.

## Features

- **User Management:** Handle user registration, authentication, and profile management.
- **Tank Management:** Manage different types of aquariums (basic, planted, marine) and their configurations.
- **Actuator Management:** Monitor and control various actuators associated with the tanks.
- **Sensor Management:** Gather and manage sensor data for environmental monitoring.

## Technologies Used

- **Spring Boot:** A powerful framework for building Java-based web applications.
- **PostgreSQL:** A relational database for storing user, tank, actuator, and sensor data.
- **Docker:** Containerization tool for easy deployment and management of microservices.
- **Docker Compose:** Simplifies running multi-container Docker applications.
- **JWT (JSON Web Token):** For secure authentication and authorization.

## Logging

- **Simple Logging Facade for Java (SLF4J)** serves as a facade for various logging frameworks (e.g., `java.util.logging`, Logback, Log4j). It provides a generic API, enabling logging to be independent of the actual implementation. This abstraction allows developers to switch logging frameworks easily without changing the logging code.
- **Apache Log4j** is a robust, industrial-grade logging framework for Java, comprising an API, its implementation, and various components that assist in deployment across different use cases. Log4j offers extensive configuration options and supports multiple output destinations, making it highly versatile.

### Dependencies
To use SLF4J and Log4j in your project, you need to include the following dependencies in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.23.1</version>
</dependency>

<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.23.1</version>
</dependency>

<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.23.1</version>
</dependency>
```
- The `@Slf4j` annotation from Lombok provides a convenient way to implement logging in your Java classes. By using this annotation, you can automatically generate a logger instance, which reduces boilerplate code and makes your classes cleaner and more readable.

1. **Add Lombok Dependency**: Ensure you have the Lombok dependency in your `pom.xml` file:

   ```xml
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.30</version>
       <scope>provided</scope>
   </dependency>
   ```


## 👨‍💻 Contributors
Riju Mondal – Project Maintainer  
Contributors – Special thanks to all contributors

## 📝 License
This project is licensed under the MIT License. See the LICENSE file for more details.