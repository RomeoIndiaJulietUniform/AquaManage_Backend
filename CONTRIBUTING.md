# Contributing to AquaManage_Backend

Thank you for considering contributing to our project!

## How to Contribute

1. **Fork the repository**: Click the "Fork" button in the top right corner of the repository page.
2. **Clone your fork**:
   ```bash
   git clone https://github.com/your-username/your-repo.git
    ```
3. **Create a new branch**:
   ```bash
   git checkout -b feature/your-feature-name
    ```
4. **Make your changes and commit them**:
   ```bash
   git checkout -b feature/your-feature-name
    ```
5. **Push your changes**:
   ```bash
   git push origin feature/your-feature-name
    ```
6. **Open a pull request**: Go to the original repository and click on "Pull Requests," then "New Pull Request."   

## Prerequisites

- **Docker**
- **Docker Compose**
- **Maven** (version 3.6.0 or higher)
- **Java** (version 21)
- **PostgreSQL** (version 15 or higher)

### Running the Application

1. Clone the repository:

   ```bash
   git clone <your-repository-url>
   cd AquaManage
   ```
 
2. Build the Packages of Each service (Use Java 21 Strictly , we use Oracle official version you may use OpenJDK)   

   ```bash
   cd to_each_service(i.e. UserService,SensorService,ActuatorService,TankService)
   mvn clean package 
   ```  

3. Build and run the services using Docker Compose:

   ```bash
    docker-compose up --build
   ```
4. Access the Services

- **User Service:** `http://localhost:8081`
- **Tank Service:** `http://localhost:8082`
- **Actuator Service:** `http://localhost:8083`
- **Sensor Service:** `http://localhost:8084`

4. To stop the services, run:

    ```bash
    docker-compose down
    ```