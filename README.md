# Backend Documentation

## SafeSpace API Backend

This repository contains the backend API for the SafeSpace application, built with Spring Boot and PostgreSQL.

## Requirements

- Java JDK 21 or newer
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

**[http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

The Swagger UI provides a complete list of all available endpoints with request/response schemas and the ability to test API calls directly.

## Available Endpoints

### Tags API

| Method | Endpoint              | Description                  |
|--------|-----------------------|------------------------------|
| GET    | /api/tags             | Get all tags                 |
| GET    | /api/tags/{id}        | Get a specific tag by ID     |
| POST   | /api/tags             | Create a new tag             |
| PUT    | /api/tags/{id}        | Update an existing tag       |
| DELETE | /api/tags/{id}        | Delete a tag                 |

### Locations API

| Method | Endpoint                 | Description                     |
|--------|--------------------------|---------------------------------|
| GET    | /api/locations           | Get all locations               |
| GET    | /api/locations/{id}      | Get a specific location by ID   |
| POST   | /api/locations           | Create a new location           |
| PUT    | /api/locations/{id}      | Update an existing location     |
| DELETE | /api/locations/{id}      | Delete a location               |

### LocationTags API

| Method | Endpoint                                     | Description                                  |
|--------|----------------------------------------------|----------------------------------------------|
| GET    | /api/location-tags                           | Get all location-tag mappings                |
| POST   | /api/location-tags                           | Create a new location-tag mapping            |
| DELETE | /api/location-tags/{id}                      | Delete a location-tag mapping                |
| GET    | /api/location-tags/tags-by-location/{id}     | Get all tags associated with a location      |
| GET    | /api/location-tags/locations-by-tag/{id}     | Get all locations associated with a tag      |

### Reviews API

| Method | Endpoint                                     | Description                                     |
|--------|----------------------------------------------|-------------------------------------------------|
| POST   | /api/reviews                                 | Create a new review                             |
| GET    | /api/reviews/by-user/{userId}                | Get all reviews by a specific user              |
| GET    | /api/reviews/by-location/{locationId}        | Get all reviews for a specific location         |
| GET    | /api/reviews/average-rating/{locationId}     | Get the average rating for a specific location  |

