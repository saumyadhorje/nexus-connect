# Nexus – Social Media Backend (Spring Boot + JWT)

A production-inspired backend application that provides secure user authentication, post management, search, pagination, sorting, and JWT-based authorization using Spring Boot and MySQL.

---
## Features

### Authentication & Security
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Protected Endpoints using Spring Security
- Authorization (Users can update/delete only their own posts)

### User Management
- Register new users
- Login and receive JWT token
- Search users by name

### Post Management
- Create posts
- View all posts
- View a post by ID
- Update own posts
- Delete own posts

### Search
- Search posts by keyword
- Search users by name

### Pagination & Sorting
- Paginated post retrieval
- Dynamic sorting (Ascending/Descending)

### API Documentation
- Swagger UI Integration
- JWT Authorization support in Swagger

### Exception Handling
- Global Exception Handler
- Custom Exceptions
- Bean Validation

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL
- Maven
- Swagger / OpenAPI
- Hibernate

---

## Project Structure

```
src
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
├── serviceImpl
└── resources
```

---

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/users/register` | Register a new user |
| POST | `/api/v1/users/login` | Login and receive JWT |

---

### Users

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/v1/users/search?keyword=` | Search users |

---

### Posts

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/posts` | Create post |
| GET | `/api/v1/posts` | Get all posts |
| GET | `/api/v1/posts/{id}` | Get post by ID |
| PUT | `/api/v1/posts/{id}` | Update own post |
| DELETE | `/api/v1/posts/{id}` | Delete own post |
| GET | `/api/v1/posts/search?keyword=` | Search posts |

---

## Pagination

Retrieve posts with pagination.

Example:

```
GET /api/v1/posts?page=0&size=5
```

---

## Sorting

Retrieve posts sorted by creation date.

Newest first:

```
GET /api/v1/posts?page=0&size=5&sort=desc
```

Oldest first:

```
GET /api/v1/posts?page=0&size=5&sort=asc
```

---

## Authentication

After logging in, include the JWT token in every protected request.

```
Authorization: Bearer <your_jwt_token>
```

Swagger also supports JWT authentication through the **Authorize** button.

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/<your-username>/nexus.git
```

### Navigate

```bash
cd nexus
```

### Configure Database

Create a PostgreSQL database.

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nexus
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run

```bash
mvn spring-boot:run
```

or directly from your IDE.

---

## Swagger Documentation

Once the application is running:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Future Improvements

- Docker & Docker Compose
- Microservices Architecture
- API Gateway
- Eureka Service Discovery
- Refresh Token Authentication
- Likes & Comments
- Follow System
- Cloud Deployment (AWS / Render)

---

## Author

**Saumya Dhorje**
