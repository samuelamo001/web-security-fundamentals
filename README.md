# AuthController

The `AuthController` is a Spring Boot REST controller designed for handling user authentication and registration. It exposes endpoints for user registration and login functionalities.

## Endpoints

### Register

- **URL:** `/api/v1/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }
