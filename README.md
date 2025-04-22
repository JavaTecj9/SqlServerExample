## Overview

This project is a simple Java application built with Spring Boot that demonstrates how to connect to a local Microsoft SQL Server instance using JDBC. It showcases basic CRUD (Create, Read, Update, Delete) operations on a `TASK` table and utilizes HikariCP for efficient database connection pooling. The example is intended as a lightweight, beginner-friendly reference for working with JDBC in a Spring Boot context without requiring a full Spring Data JPA setup.

### Key Features

- Connects to a local SQL Server using a JDBC connection string  
- Initializes a `TASK` table if it doesn’t already exist  
- Inserts sample task records into the database  
- Retrieves and displays task records via console output  
- Demonstrates parameterized SQL queries using `PreparedStatement`  
- Deletes all records as part of a simple cleanup operation  
- Uses HikariCP as the connection pool manager for performance and reliability

### Requirements

- Java 17+  
- Microsoft SQL Server (running locally on default port 1433)  
- Spring Boot  
- HikariCP (included as a dependency with Spring Boot)

This is a great starting point for developers learning how to integrate Spring Boot with SQL Server using raw JDBC and connection pooling.
