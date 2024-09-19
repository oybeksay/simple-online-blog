# Simple Online Blog Platform

This project is a basic online blogging platform built using Spring Boot. It allows users to create, update, delete, and view blog posts, as well as comment on posts created by others. The platform includes user authentication and authorization, utilizing JWT for secure access control.

## Features
- **Post management:** Users can create, edit, delete, and view their blog posts.
- **User management:** Users can register, log in, and manage their accounts.
- **Comment system:** Users can leave comments on blog posts.
- **RESTful API:** The platform provides a RESTful API for interacting with the system.
- **JWT-based Authentication:** Secure authentication is implemented using JWT tokens.
- **Role-based Authorization:** Different access levels are provided based on user roles (e.g., Admin, User).

## Technologies Used
- **Spring Boot** - For building the backend services.
- **Spring Security** - To handle authentication and authorization.
- **Spring Data JPA** - For interaction with the database.
- **PostgreSQL** - The relational database used for storing data.
- **Lombok** - To reduce boilerplate code.
- **JWT (JSON Web Token)** - For secure user authentication.
- **Swagger** - For API documentation.

## Setup and Installation

### Prerequisites
- Java 17 or later
- Maven 3.6+
- PostgreSQL

### Installation Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/oybeksay/simple-online-blog-platform.git
