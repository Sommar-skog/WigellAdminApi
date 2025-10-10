# WigellAdminAPI

WigellAdminAPI is a **Spring Boot Enterprise application** developed by **Amanda Aronsson** as part of the **Enterprise Java course**.  
It demonstrates a layered **MVC architecture** with database integration, DTOs, and server-side rendering using **Thymeleaf**.  
The project follows professional Spring conventions and fulfills all course requirements.  
Graded **VG (Highest grade)**.

---

## Overview

- **Language:** Java 17
- **Framework:** Spring Boot 3.4.3
- **Database:** H2 (in-memory)
- **View Layer:** Thymeleaf
- **Architecture:** MVC (Controllers, Services, Repositories, Entities)
- **Port:** `8080`

---

## Features

- Full **CRUD functionality** for administrative entities
- **Custom exceptions** for input validation and business rule enforcement
- **Service layer abstraction** for business logic
- **JPA repository integration** with automatic schema generation
- **Thymeleaf** templates for server-side rendered views
- **H2 Console** enabled for easy testing
- **Validation annotations** ensure clean and consistent data
- Follows **Enterprise course architecture and naming standards**

---

## Project Structure


| Package        | Purpose                                           |
|----------------|---------------------------------------------------|
| `controllers`  | Handles HTTP requests and web routing             |
| `services`     | Contains business logic and validation            |
| `repositories` | Manages persistence using Spring Data JPA         |
| `entities`     | JPA entities mapped to database tables            |
| `exceptions`   | Custom exception classes and error handling logic |

---

## Database Configuration

The application uses an **H2 in-memory database** for testing and development.

| Property                     | Value                  |
|------------------------------|------------------------|
| `spring.datasource.url`      | `jdbc:h2:mem:memberdb` |
| `spring.datasource.username` | `sa`                   |
| `spring.h2.console.enabled`  | `true`                 |
| `spring.jpa.show-sql`        | `true`                 |

The H2 console is accessible at:  
👉 **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**

---

## Result

Graded VG (Highest grade) in the *Enterprise Java* course.