# 🛡️ ÆGis Backend

This repository contains the backend API for the Aegis application, built with Spring Boot and PostgreSQL.

## ✨ Features

- **Spring Boot Framework**: Modern, production-ready backend
- **PostgreSQL Database**: Reliable and scalable data storage
- **AI-Powered Analysis**: Automatically tags locations based on user reviews
- **RESTful API**: Complete set of endpoints for all functionality
- **Swagger Documentation**: Interactive API documentation

## 🛠️ Requirements

- Java JDK 21 or newer
- PostgreSQL database server
- Maven (optional if using the Maven wrapper)

> ⚠️ **Important Note**
>
> To enable the **AI Analysis** feature, make sure the [AI Pipeline](https://github.com/nmquan1/aegis-pipeline) is also running locally **within the same local network**.
>
> This backend is part of an MVP system and is optimized for local development. For the best experience, it is recommended to run:
>
> - 🧠 [AI Pipeline](https://github.com/nmquan1/aegis-pipeline) – Required for AI Analysis
> - 🖥️ [Frontend](https://github.com/Hankaji/GDGDoc-Aegis-Frontend) – Optional, but recommended for full functionality and testing UI interactions
>
> Ensure that the backend, frontend, and AI pipeline can communicate over the same local network.


## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/DankoFox/aegis-backend.git
cd aegis-backend
```

### 2. Configure Database & AI Service

Add a `.env` file in the project root and setup the database and AI connection properties:

```properties
# Database configuration
DB_URL=jdbc:postgresql://localhost:5432/aegis
DB_USERNAME=postgres
DB_PASSWORD=your_password

# AI service endpoint
AI_HOST_URL=http://localhost:5000/analyze-review
```

> ⚠️ **Important Note**
> 
> - ✅ **DB_URL**: The JDBC URL for your PostgreSQL instance. Replace `localhost` and port `5432` if your DB is hosted elsewhere.
> - ✅ **DB_USERNAME** and **DB_PASSWORD**: Credentials to access your PostgreSQL database.
>   
> - 🤖 **AI_HOST_URL**: The base URL for the AI pipeline’s review analysis endpoint.
>   
>  Make sure the [AI Pipeline](https://github.com/nmquan1/aegis-pipeline) is running locally or accessible on your network. This is required for AI-powered features like review analysis.

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

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation at:

**[Swagger API documentation](http://localhost:8080/api/swagger-ui/index.html)**

The Swagger UI provides a complete list of all available endpoints with request/response schemas and the ability to test API calls directly.

## 🔌 Available Endpoints

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

### AI Analysis API
| Method | Endpoint                                     | Description                                          |
|--------|----------------------------------------------|------------------------------------------------------|
| POST   | /api/ai/analyze/{locationId}                 | Manually trigger AI analysis for a specific location |

## 🔄 AI Integration

The backend integrates with a separate AI service to analyze reviews and automatically tag locations based on their friendliness to marginalized communities. When reviews are created:

1. A `ReviewCreatedEvent` is dispatched
2. The AI analysis service processes the reviews asynchronously
3. Location tags are automatically updated based on the analysis results

> **Warning**: The AI service must be running separately. Check the [AI repository](https://github.com/nmquan1/aegis-pipeline) for setup instructions.

## 🧰 Project Structure

```
/src
  /main
    /java/com/aegis/safespace
      /ai                  # AI integration services
      /config              # Application configuration 
      /exception           # Exception handlers
      /location            # Location-related components
      /locationtag         # Location-tag mapping components
      /locationinsight     # Friendliness score and summary of a location
      /review              # Review-related components
      /tag                 # Tag-related components
      /user                # User-related components
      SafespaceApplication.java  # Main application class
    /resources
      application.yml      # Application properties
```
