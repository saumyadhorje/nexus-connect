# nexus-connect


A full-stack professional social networking platform inspired by LinkedIn, built using **Spring Boot**, **React**, **MySQL**, **Spring Security**, and **JWT Authentication**.

Nexus enables users to build professional profiles, create and share posts, connect with other professionals, and interact through likes, comments, and notifications. The backend follows a scalable layered architecture with secure JWT-based authentication, while the frontend provides a modern and responsive user experience.

---

# ✨ Features

## 👤 User Management

* User Registration
* Secure Login & Logout
* JWT Authentication
* BCrypt Password Encryption
* Profile Management
* Change Password
* Upload Profile Picture
* Delete Account

---

## 📝 Posts

* Create Posts
* Edit Posts
* Delete Posts
* View Feed
* Like & Unlike Posts
* Comment on Posts
* Reply to Comments

---

## 🤝 Networking

* Follow / Unfollow Users
* View Followers & Following
* Search Users
* Discover Professionals

---

## 🔔 Notifications

* Like Notifications
* Comment Notifications
* Follow Notifications

---

## 🔒 Security

* Spring Security
* JWT Authentication
* Stateless Sessions
* Password Hashing with BCrypt
* Global Exception Handling
* Request Validation

---

# 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT (JJWT)
* MySQL
* Maven
* Lombok

### Frontend

* React.js
* HTML5
* CSS3
* JavaScript (ES6+)
* Axios

### Tools

* Git
* GitHub
* Postman / Thunder Client
* IntelliJ IDEA
* VS Code
* MySQL Workbench

---

# 📂 Project Structure

```
Nexus
│
├── backend
│   ├── config
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   ├── security
│   ├── service
│   └── serviceImpl
│
├── frontend
│   ├── components
│   ├── pages
│   ├── services
│   ├── hooks
│   ├── assets
│   └── utils
│
└── README.md
```

---

#  Architecture

```
                React Frontend
                      │
                      ▼
               REST API Requests
                      │
                      ▼
             Spring Boot Backend
                      │
      ┌───────────────┼────────────────┐
      │               │                │
      ▼               ▼                ▼
 Controllers      Services       Security
      │               │                │
      ▼               ▼                ▼
Repositories ───────► MySQL ◄────── JWT
```

---

# 🔐 Authentication Flow

```
Register User
      │
      ▼
Validate Request
      │
      ▼
Hash Password (BCrypt)
      │
      ▼
Store User in Database

────────────────────────────────────

Login
      │
      ▼
Verify Credentials
      │
      ▼
Generate JWT
      │
      ▼
Return Token

────────────────────────────────────

Frontend stores JWT

────────────────────────────────────

Every Protected Request

Authorization: Bearer <JWT>

────────────────────────────────────

JWT Validation

────────────────────────────────────

Access Granted
```

---

# 📡 REST API Endpoints

## Authentication

| Method | Endpoint               | Description         |
| ------ | ---------------------- | ------------------- |
| POST   | /api/v1/users/register | Register a new user |
| POST   | /api/v1/users/login    | Login user          |
| GET    | /api/v1/users/me       | Get logged-in user  |

---

## Profile

| Method | Endpoint        |
| ------ | --------------- |
| GET    | /api/v1/profile |
| PUT    | /api/v1/profile |
| DELETE | /api/v1/profile |

---

## Posts

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/v1/posts      |
| GET    | /api/v1/posts      |
| GET    | /api/v1/posts/{id} |
| PUT    | /api/v1/posts/{id} |
| DELETE | /api/v1/posts/{id} |

---

## Comments

| Method | Endpoint              |
| ------ | --------------------- |
| POST   | /api/v1/comments      |
| PUT    | /api/v1/comments/{id} |
| DELETE | /api/v1/comments/{id} |

---

## Likes

| Method | Endpoint                |
| ------ | ----------------------- |
| POST   | /api/v1/posts/{id}/like |
| DELETE | /api/v1/posts/{id}/like |

---

## Follow

| Method | Endpoint                     |
| ------ | ---------------------------- |
| POST   | /api/v1/users/{id}/follow    |
| DELETE | /api/v1/users/{id}/follow    |
| GET    | /api/v1/users/{id}/followers |
| GET    | /api/v1/users/{id}/following |

---

# 🗄 Database Design

### User

* id
* name
* email
* password
* bio
* profilePicture
* createdAt

### Post

* id
* content
* imageUrl
* createdAt
* updatedAt
* userId

### Comment

* id
* content
* createdAt
* postId
* userId

### Like

* id
* postId
* userId

### Follow

* followerId
* followingId

### Notification

* id
* message
* type
* createdAt
* isRead

---

# 🚀 Getting Started

## Clone the Repository

```bash
git clone https://github.com/your-username/nexus.git
```

## Navigate to the Project

```bash
cd nexus
```

## Configure MySQL

Update the database configuration inside:

```
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nexusdb
spring.datasource.username=root
spring.datasource.password=your_password
```

## Run Backend

```bash
mvn spring-boot:run
```

## Run Frontend

```bash
npm install
npm start
```

---

# 🧪 Testing

The REST APIs can be tested using:

* Thunder Client
* Postman

---

# 🔮 Future Enhancements

* Real-time Chat
* AI-powered Feed Recommendations
* Resume Upload
* Skill Endorsements
* Company Pages
* WebSocket Notifications
* Email Verification
* Password Reset via Email
* Two-Factor Authentication (2FA)
* Docker & Kubernetes Deployment
* CI/CD with GitHub Actions


---

# 👨‍💻 Author

**Saumya Dhorje**

Built to explore modern backend development using **Spring Boot**, **JWT Authentication**, **REST APIs**, **Spring Security**, and full-stack application development.

