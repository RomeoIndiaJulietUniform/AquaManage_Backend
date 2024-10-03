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

## Prerequisites

- **Docker**
- **Docker Compose**
- **Maven** (version 3.6.0 or higher)
- **Java** (version 21)

### Running the Application

1. Clone the repository:

   ```bash
   git clone <your-repository-url>
   cd AquaManage
   ```
2. Build and run the services using Docker Compose:
  
   ```bash
    docker-compose up --build
   ```
3. Access the Services

- **User Service:** `http://localhost:8081`
- **Tank Service:** `http://localhost:8082`
- **Actuator Service:** `http://localhost:8083`
- **Sensor Service:** `http://localhost:8084`

4. To stop the services, run:

    ```bash
    docker-compose down
    ```

## 👨‍💻 Contributors
Riju Mondal – Project Maintainer  
Contributors – Special thanks to all contributors

## 📝 License
This project is licensed under the MIT License. See the LICENSE file for more details.