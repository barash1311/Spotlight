# Spotlight — Portfolio Backend API

The powerful Spring Boot backend for your "Spotlight" portfolio project. This API handles project management, contact messages, user profiling, and authentication.

## 🚀 Tech Stack
- **Spring Boot 3.4.x** (Java 17)
- **Spring Data JPA** (PostgreSQL)
- **Spring Security** (JWT Authentication)
- **Hibernate** (Automatic schema management)
- **Swagger / OpenAPI** (API documentation)
- **Lombok** (Boilerplate reduction)

## ⚙️ Environment Configuration
Create a `.env` file in the root directory or set these variables in your deployment environment:

| Variable | Description | Default |
| :--- | :--- | :--- |
| `DB_URL` | JDBC Connection String | `jdbc:postgresql://localhost:5432/portfolio_db` |
| `DB_USERNAME` | Database User | `postgres` |
| `DB_PASSWORD` | Database Password | `postgres` |
| `JWT_SECRET` | Super secure key for signing tokens | (Generated Secret) |
| `SEED_DEFAULT_USER_ENABLED` | Create admin user on startup | `true` |
| `SEED_DEFAULT_USER_USERNAME`| Default admin username | `barash1311` |

## 🛠️ Local Setup

1. **Clone & Install Dependencies:**
   ```bash
   ./mvnw clean install
   ```

2. **Configure Database:**
   Update your `.env` with your local PostgreSQL or Neon.tech credentials.

3. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The API will be live at `http://localhost:8080`.

## 📖 API Documentation
Once the app is running, visit the interactive Swagger UI to explore and test endpoints:
*   **Swagger Interface:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
*   **JSON OpenAPI Docs:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 🔒 Authentication
All `/admin/**` endpoints require a Bearer JWT Token.
1.  **Login**: POST to `/api/auth/login` to receive a token.
2.  **Authorize**: Include the token in your headers: `Authorization: Bearer <TOKEN>`.

## ☁️ Deployment
This project is deployment-ready for **Render**, **Koyeb**, or **Oracle Cloud**. It includes a `Dockerfile` setup for easy containerization.
