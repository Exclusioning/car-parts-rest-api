# Car Parts REST API

A Spring Boot REST API for managing a car parts inventory and a per-user shopping cart. The application exposes endpoints to add, list, update, and delete parts, and supports authenticated cart operations tied to the logged-in user.

## Overview

This project is a simple backend for a car-parts storefront or inventory system. It includes:

- Inventory management for car parts
- User-specific cart functionality
- Persistent storage via JPA and PostgreSQL
- Spring Security integration with OAuth2 login
- REST endpoints for parts and cart operations

## Tech Stack

- Java 17
- Spring Boot 3.2.2
- Maven (wrapper included)
- Spring Web
- Spring Data JPA
- PostgreSQL (runtime dependency)
- Spring Security + OAuth2 Client

## Project Structure

The application follows a standard Spring Boot layout (found under `src/main/java/com/mycompany/carpartsapp`):

- `controller` — REST controllers that expose the API
- `model` — JPA entities (CarPartInfo, CartItem)
- `repository` — Spring Data JPA repositories (PartRepository, CartRepository)
- `service` — business logic services (PartService, CartService)
- `config` — security and other application configuration

Key classes include:

- `CarpartsappApplication` (Spring Boot entrypoint)
- `PartController` — /api/parts endpoints
- `CartController` — /api/cart endpoints
- `CarPartInfo` — JPA entity for parts
- `CartItem` — JPA entity for cart items
- `PartRepository`, `CartRepository`

## Domain Model

### Car Part

The app stores car parts with these attributes:

- `partId` (generated id)
- `partName` (string)
- `partPrice` (string representation in code)
- `partQuantity` (int)

### Cart Item

The cart stores per-user items using:

- `cartId` (generated id)
- `partId` (reference to a part)
- `quantity` (int)
- `sessionId` (string derived from OAuth2 user login)

Cart operations use the authenticated OAuth2 user's login value to scope cart data to a user.

## API Endpoints

Base path: `/api`

### Parts

GET `/api/parts`
- Returns the full list of parts.

GET `/api/parts/{partId}`
- Returns the part matching the specified ID.

POST `/api/parts`
- Creates a new part.
- Request parameters (form/query): `name`, `price`, `quantity`.

Example:

```bash
curl -X POST "http://localhost:8080/api/parts?name=Brake%20Pad&price=29.99&quantity=50"
```

PUT `/api/parts/{partId}/{quantity}`
- Updates the quantity of an existing part.

Example:

```bash
curl -X PUT "http://localhost:8080/api/parts/1/25"
```

DELETE `/api/parts/{partId}`
- Deletes a part by ID.

Example:

```bash
curl -X DELETE "http://localhost:8080/api/parts/1"
```

### Cart

All cart endpoints require an authenticated user (OAuth2).

GET `/api/cart`
- Returns the authenticated user's cart as a list of items.

POST `/api/cart/{partId}/{quantity}`
- Adds a quantity of a part to the authenticated user's cart.

Example:

```bash
curl -X POST "http://localhost:8080/api/cart/1/2" -b <cookie-or-auth>
```

PUT `/api/cart/{partId}/{quantity}`
- Updates the quantity for an item in the cart.

DELETE `/api/cart/{partId}`
- Removes an item from the authenticated user's cart.

## Authentication

The application uses Spring Security and OAuth2 login. Controller methods expect an `@AuthenticationPrincipal OAuth2User` principal and use the principal's `login` attribute as the session/user identifier for cart operations.

Configure OAuth2 client properties in `application.properties` / `application.yml` for your provider (GitHub, Google, etc.).

## Configuration

Typical application properties to set (examples):

- `spring.datasource.url=jdbc:postgresql://localhost:5432/carparts`
- `spring.datasource.username=...`
- `spring.datasource.password=...`
- `spring.jpa.hibernate.ddl-auto=update` (or `validate`/`none` in production)
- OAuth2 client config: `spring.security.oauth2.client.registration.{provider}` settings

Check `src/main/resources` for property files and existing configuration.

## Running the Application

Requirements:

- Java 17+
- Maven (the repo includes the Maven wrapper)
- PostgreSQL (or change JPA config to an embedded DB for local testing)

Run with the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Open the API at:

http://localhost:8080/api

## Building

```bash
./mvnw clean package
```

## Testing

The project includes Spring Boot test dependencies and JaCoCo reporting configured in Maven. Run:

```bash
./mvnw test
```

## Notes & TODOs

- `partPrice` is stored as a String in the current model — consider switching to a numeric type (BigDecimal) for correctness.
- Improve validation for inputs (e.g., negative quantities, price format).
- Add DTOs for API requests/responses to decouple JPA entities from external contracts.
- Add OpenAPI / Swagger documentation for discoverability.

## License

No explicit license file is present in the repository. Add a LICENSE file (e.g., MIT) if you intend to open-source this project.
