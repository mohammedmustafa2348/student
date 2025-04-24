# Student Service Application

A simple REST API service for managing student information. This service allows you to create, read, update, and delete student records, as well as search for students by grade or school.

## Overview

The Student Service provides the following functionalities:
- Manage student records (CRUD operations)
- Search students by grade
- Search students by school name
- RESTful API with JSON responses

## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory database)
- Swagger/OpenAPI for documentation

## Getting Started

### Prerequisites

1. Install Java 17
   ```bash
   # Check Java version
   java --version
   ```

### Setup and Installation

1. Clone the repository
   ```bash
   git clone <repository-url>
   cd student-service
   ```

2. Database Configuration

   The application uses H2 in-memory database by default. Add these properties in `src/main/resources/application.properties`:
   ```properties
   # H2 Database Configuration
   spring.datasource.url=jdbc:h2:mem:studentdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   
   # H2 Console Configuration (for development)
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   
   # JPA Configuration
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Build the application
   ```bash
   # Using Maven
   ./mvnw clean install
   ```

4. Run the application
   ```bash
   # Using Maven
   ./mvnw spring-boot:run
   ```

The application will start at `http://localhost:8081`

## Database Access

You can access the H2 console at: [http://localhost:8081/h2-console](http://localhost:8081/h2-console)

Connection details:
- JDBC URL: `jdbc:h2:mem:studentdb`
- User Name: `sa`
- Password: [leave empty]

## API Endpoints

### Base URL: `/api/students`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create a new student |
| GET | `/` | Get all students |
| GET | `/{studentId}` | Get student by ID |
| PUT | `/{studentId}` | Update student |
| DELETE | `/{studentId}` | Delete student |
| GET | `/grade/{grade}` | Get students by grade |
| GET | `/school/{schoolName}` | Get students by school |

### Example Requests

1. Create a new student
   ```bash
   curl -X POST http://localhost:8081/api/students \
   -H "Content-Type: application/json" \
   -d '{
     "firstName": "John",
     "lastName": "Doe",
     "grade": "10",
     "schoolName": "High School"
   }'
   ```

2. Get all students
   ```bash
   curl http://localhost:8081/api/students
   ```

3. Get students by grade
   ```bash
   curl http://localhost:8081/api/students/grade/10
   ```

## API Documentation

Access the Swagger UI documentation at: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

## Testing

Run the tests using:
```bash
./mvnw test
```
## Postman Collection

A Postman collection is provided for testing the API endpoints. You can find it in the `postman-collections.json` file.

### Importing the Collection

1. Open Postman
2. Click on "Import" button
3. Upload the `postman-collections.json` file or paste its contents

### Collection Structure

The collection includes two main folders:

#### 1. Student Service
- Create Student (POST)
   - Endpoint: `http://localhost:8081/api/students`
   - Example request body included for student creation
- Get All Students (GET)
   - Endpoint: `http://localhost:8081/api/students`
- Get Student by ID (GET)
   - Endpoint: `http://localhost:8081/api/students/{studentId}`


### Environment Variables

Consider setting up environment variables in Postman for:
- `baseUrl` for student service: `http://localhost:8081`

This will make it easier to switch between different environments (local, development, staging, etc.).
## Important Notes
- The H2 database is in-memory, which means data will be lost when the application is restarted
- The H2 console is enabled only for development purposes and should be disabled in production
- The database is automatically created when the application starts
- Database schema is automatically generated based on your entity classes

## Common Issues and Solutions
1. **Cannot access H2 Console**
    - Verify the application is running
    - Check if you're using the correct JDBC URL
    - Ensure you're using the correct credentials

2. **Application won't start**
    - Ensure port 8081 is available
    - Check application logs for errors

## Need Help?
If you encounter any issues:
1. Check the application logs
2. Verify H2 console access
3. Contact the development team
