# Employee Management System API

This is a simple Employee Management System API using Java with Spring Boot. It utilizes a relational database to store employee details, department information, and project assignments. The system exposes a RESTful API for performing CRUD operations on employee data.

## Completed Tasks

✅ Project Setup  
✅ Database Design  
✅ Core Functionality  
✅ API Documentation using Swagger  
✅ Security Implementation  
✅ Unit Testing on the repository using JUnit  
✅ Exception Handling on the repository  

## Setup Instructions 🔧

### Download the Source Code

1. Clone the repository: `git clone https://github.com/Ethanlyt/ems-repo.git`

### Setup Database

1. Create a schema for your database
2. Open `ems-repo/src/main/resources/application.yml`
3. Update the connection URL to your database and modify the authentication details

### Run Application

Run the application on your IDE

## Project Details

### Architecture

- The project uses JPA (Java Persistence API) for object-relational mapping

### Entity Relationships

- There are 4 main entities: Employee, Department, Project, and User
- An Employee can belong to one department or none
- An Employee can be assigned to multiple projects or none
- The relationships are bidirectional
- The User entity is used for authentication

### Initial Data

When the application runs, it automatically inserts mock data into the database for ease of presentation.

### Authentication

Users can login with the following credentials to obtain an access token:
- Username: `admin`
- Password: `admin`

### API Documentation

Please visit `http://localhost:8080/swagger-ui/index.html` for the complete API documentation.
