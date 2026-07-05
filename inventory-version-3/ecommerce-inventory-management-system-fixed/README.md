# E-Commerce Inventory Management System

A production-style backend REST API for an online store inventory system, built with Spring Boot 3, Spring Data JPA, Spring Security, and JWT authentication. Admins manage products and categories; regular users can browse them.

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Maven
- Spring Data JPA (Hibernate)
- Spring Security 6 + JWT (jjwt 0.12.5)
- MySQL 8
- Lombok
- Jakarta Bean Validation

## Project Structure

```
src/main/java/com/example/inventory
├── controller       # REST controllers (AuthController, CategoryController, ProductController)
├── service          # Service interfaces + implementations
├── repository       # Spring Data JPA repositories
├── entity           # JPA entities (User, Role, Category, Product)
├── dto              # Request/response DTOs
├── exception        # GlobalExceptionHandler, custom exceptions
└── security         # JWT + Spring Security configuration
```

## 1. Prerequisites

- JDK 17+
- Maven 3.8+ (or use the IntelliJ-bundled Maven)
- MySQL 8 running locally, accessible via MySQL Workbench or CLI
- IntelliJ IDEA (Community or Ultimate)

## 2. Database Setup

You do **not** need to manually create tables — `spring.jpa.hibernate.ddl-auto=update` will create/update the schema automatically. You only need the database itself to exist, and the connection string already includes `createDatabaseIfNotExist=true`, so simply having MySQL running is enough.

If you prefer to create it manually in MySQL Workbench:

```sql
CREATE DATABASE ecommerce_inventory;
```

## 3. Configure Credentials

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password_here
```

Also consider changing `jwt.secret` before any real deployment.

## 4. Import into IntelliJ IDEA

1. Unzip the project.
2. In IntelliJ: **File → Open** → select the unzipped project folder (the one containing `pom.xml`).
3. IntelliJ will detect it as a Maven project and download dependencies automatically.
4. Once indexing/dependency download finishes, locate `InventoryApplication.java` and click the green **Run** arrow.
5. The app starts on **http://localhost:8080**.

## 5. Postman Sample Requests

### Register ADMIN
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "admin1",
  "email": "admin1@example.com",
  "password": "Admin@123",
  "role": "ADMIN"
}
```

### Register USER
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "user1",
  "email": "user1@example.com",
  "password": "User@123",
  "role": "USER"
}
```

### Login
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin1",
  "password": "Admin@123"
}
```
Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin1",
  "role": "ADMIN"
}
```
Use this token as a Bearer token (`Authorization: Bearer <token>`) on all subsequent requests.

### Create Category (ADMIN only)
```
POST http://localhost:8080/api/categories
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "name": "Electronics",
  "description": "Electronic gadgets and devices"
}
```

### Create Product (ADMIN only)
```
POST http://localhost:8080/api/products
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "name": "Wireless Mouse",
  "description": "Ergonomic 2.4GHz wireless mouse",
  "price": 799.00,
  "quantity": 150,
  "categoryId": 1
}
```

### Pagination Example
```
GET http://localhost:8080/api/products?page=0&size=10
Authorization: Bearer <token>
```
Response shape:
```json
{
  "products": [ ... ],
  "currentPage": 0,
  "totalItems": 25,
  "totalPages": 3
}
```

### Sorting Example
```
GET http://localhost:8080/api/products?page=0&size=10&sortBy=price&direction=asc
Authorization: Bearer <token>

GET http://localhost:8080/api/products?page=0&size=10&sortBy=name&direction=desc
Authorization: Bearer <token>
```

## 6. Role-Based Access Summary

| Endpoint                          | ADMIN | USER |
|------------------------------------|:-----:|:----:|
| POST /api/categories               | ✅    | ❌ (403) |
| PUT /api/categories/{id}           | ✅    | ❌ (403) |
| DELETE /api/categories/{id}        | ✅    | ❌ (403) |
| GET /api/categories                | ✅    | ✅ |
| GET /api/categories/{id}           | ✅    | ✅ |
| POST /api/products                 | ✅    | ❌ (403) |
| PUT /api/products/{id}             | ✅    | ❌ (403) |
| DELETE /api/products/{id}          | ✅    | ❌ (403) |
| GET /api/products                  | ✅    | ✅ |
| GET /api/products/{id}             | ✅    | ✅ |

## 7. Notes

- Passwords are hashed with `BCryptPasswordEncoder` before being stored — never stored in plain text.
- All entities are hidden from the API surface; only DTOs (`*Request` / `*Response`) are exposed.
- All unhandled/known exceptions are converted into structured JSON error responses by `GlobalExceptionHandler`.
- JWT tokens expire after 24 hours by default (`jwt.expiration-ms=86400000`); adjust as needed.
