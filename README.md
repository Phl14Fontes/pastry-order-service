# Pastry Shop Order Management Microservice

###### The **pastry-order-service** is designed to handle the internal operations of a pastry shop.

It offers RESTful APIs for managing orders, provides interactive API documentation through **Swagger** (accessible at `/docs`),
and integrates seamlessly with a relational database and Kafka for asynchronous communication.

---

## Features

- **Order Lifecycle Management**: APIs for creating, retrieving, updating, and deleting orders.
- **Swagger Documentation**: Interactive API documentation available at `/docs`.
- **Database Integration**: Uses PostgreSQL as the relational database.
- **Event-Driven Communication**: Utilizes Kafka for message-based communication.
- **Dockerized Environment**: All required services are orchestrated via Docker Compose.
- **Gradle Build System**: Simplified dependency management and build process.

---

## Project Setup

### Prerequisites

1. **Install Docker and Docker Compose**  
   Follow the official instructions to install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/).

2. **Install JDK 21**  
   Download and install JDK 21 from [Adoptium](https://adoptium.net/) or your preferred JDK provider. Make sure `JAVA_HOME` is set correctly.

3. **Install Gradle**  
   Download and install Gradle from [gradle.org](https://gradle.org/), or use the Gradle wrapper (`./gradlew`) included in the project.

---

## Running the Environment

### 1. Start Docker Compose
To start the required database and Kafka services, execute the following command in the project root directory (where the `docker-compose.yml` is located):

```bash
docker-compose up -d
