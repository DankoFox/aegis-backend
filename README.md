# Backend Documentation

## SafeSpace API Backend

This repository contains the backend API for the SafeSpace application, built with Spring Boot and PostgreSQL.

## Requirements

- Java JDK 17 or newer
- PostgreSQL database server
- Maven (optional if using the Maven wrapper)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/DankoFox/aegis-backend.git
cd safespace-backend
```

### 2. Configure Database

Add `.env` file and setup the database connection properties:

```properties
DB_URL=jdbc:postgresql://ep-yellow-union-a1bf4cav-pooler.ap-southeast-1.aws.neon.tech:5432/neondb
DB_USERNAME=neondb_owner
DB_PASSWORD=.................
```

### 3. Build and Run the Application

#### Using Maven Wrapper (recommended)

**On Linux/Mac:**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

**On Windows:**
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

#### Using Maven (if installed globally)
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Documentation

Once the application is running, you can access the interactive API documentation at:

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

The Swagger UI provides a complete list of all available endpoints with request/response schemas and the ability to test API calls directly.

## Available Endpoints

### Tags API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/tags | Get all tags |
| GET    | /api/tags/{id} | Get a specific tag by ID |
| POST   | /api/tags | Create a new tag |
| PUT    | /api/tags/{id} | Update an existing tag |
| DELETE | /api/tags/{id} | Delete a tag |
