# Car Parts REST API

A simple Java REST API for browsing car parts, managing a shopping cart, and performing basic user operations — suitable as a learning example, demo backend, or small project starter.

> Lightweight, Maven-built Java service with clear controller → service → repository layers.

## Table of contents
- [What this project does](#what-this-project-does)
- [Why it’s useful](#why-its-useful)
- [Tech stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Get started (quickstart)](#get-started-quickstart)
- [Running](#running)
- [Testing](#testing)
- [API endpoints & example requests](#api-endpoints--example-requests)
- [Project layout](#project-layout)
- [How to get help](#how-to-get-help)
- [Maintainers & contributing](#maintainers--contributing)

## What this project does
Car Parts REST API exposes HTTP endpoints to:

- List and query car parts
- Manage a user shopping cart (add/remove/update items)
- Perform basic user-related actions (create/lookup)

The application entry point is `src/main/java/com/mycompany/carpartsapp/CarpartsappApplication.java` and HTTP endpoints live under `src/main/java/com/mycompany/carpartsapp/controller`.

## Why it’s useful
- Small, focused codebase ideal for learning REST controllers, service layering, and simple persistence patterns in Java.
- Ready-to-run Maven project (`mvnw` included) that can be extended into a production prototype or used for demos.
- Clear separation of concerns (controllers, services, repositories, models) makes it easy to add features or swap implementations.

## Tech stack
- Language: Java
- Build: Maven (wrapper included: `mvnw` / `mvnw.cmd`)
- Typical framework: Spring Boot-style project layout (main class + controllers/services)

Notable files:
- `pom.xml` — project build and dependencies
- `src/main/java/com/mycompany/carpartsapp/` — application code

## Prerequisites
- JDK 17+ (or the version configured in `pom.xml`)
- Git
- Internet access for Maven dependency resolution

## Get started (quickstart)
Clone and run with the included Maven wrapper:

```bash
git clone https://github.com/Exclusioning/car-parts-rest-api.git
cd car-parts-rest-api
# Unix / macOS
./mvnw spring-boot:run
# Windows (PowerShell / CMD)
# .\mvnw.cmd spring-boot:run
```

Or build a runnable jar and start it:

```bash
./mvnw clean package
java -jar target/*.jar
```

By default the service listens on port 8080 (changeable via Spring properties).

## Running
Common commands:

- Run tests

```bash
./mvnw test
```

- Build

```bash
./mvnw clean package
```

- Run with a profile or environment variables

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
# or
SPRING_DATASOURCE_URL=jdbc:... server.port=9090 ./mvnw spring-boot:run
```

Configuration files are under `src/main/resources` — supply Spring properties or environment variables to configure ports, datasources, and profiles.

## Testing
Unit tests live under `src/test`. Run them with:

```bash
./mvnw test
```

Add tests under `src/test/java` to cover controllers and services before opening PRs.

## API endpoints & example requests
Base path note: this project exposes endpoints under the `/CarPartApp` context path in the provided examples. If you run the app with a different context or base path, adjust the URLs accordingly. The examples below include precise curl commands you provided.

Using /api/parts/*

- Delete a part from the database

```bash
curl -X DELETE http://localhost:8080/CarPartApp/api/parts/101
```

- See all parts

```bash
curl -X GET http://localhost:8080/CarPartApp/api/parts
```

- See a specific part (replace `(part-number-goes-here)`)

```bash
curl -X GET http://localhost:8080/CarPartApp/api/parts/(part-number-goes-here)
```

- Update part quantity (replace placeholders)

```bash
curl -X PUT http://localhost:8080/CarPartApp/api/parts/(part-number-goes-here)/(part-quantity-goes-here)
```

- Add a part to the database (note: when the name contains spaces, use `%20` or quote the URL)

```bash
curl -X POST "http://localhost:8080/CarPartApp/api/parts?name=(Name%20Here)&price=(price-here)&quantity=(quantity-here)"
```

Using /api/cart/*

- View a user's cart (uses cookies saved in cookies.txt)

```bash
curl -b cookies.txt -X GET http://localhost:8080/CarPartApp/api/cart
```

- Add an item to the cart (saves cookies to cookies.txt)

```bash
curl -c cookies.txt -X POST http://localhost:8080/CarPartApp/api/cart/(part-number-goes-here)
```

- Delete items from the user's cart

```bash
curl -b cookies.txt -X DELETE http://localhost:8080/CarPartApp/api/cart/(part-number-goes-here)/(part-quantity-goes-here)
```

- Update a part's quantity in the cart

```bash
curl -b cookies.txt -X PUT http://localhost:8080/CarPartApp/api/cart/(part-number-goes-here)/(quantity-to-update-here)
```

Notes:
- Replace the `(placeholders)` with actual IDs, quantities, and prices.
- The cart endpoints in examples use cookie-based session handling (`-c` to write cookies, `-b` to send cookies). If your app uses a different session mechanism, adapt accordingly.

Refer to the model classes in `src/main/java/com/mycompany/carpartsapp/model` for the exact request/response fields.

## Project layout
```
.
├─ mvnw, mvnw.cmd            # Maven wrapper scripts
├─ pom.xml                   # Maven build file
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/mycompany/carpartsapp/
│  │  │     ├─ CarpartsappApplication.java    # app entry point
│  │  │     ├─ controller/     # HTTP controllers (PartController, CartController, UserController)
│  │  │     ├─ service/        # business logic
│  │  │     ├─ repository/     # persistence layer
│  │  │     └─ model/          # DTOs / domain models
│  │  └─ resources/            # application properties / static resources
│  └─ test/                    # test sources
└─ README.md
```

How it fits together: controllers handle HTTP requests and delegate to services; services contain business logic and use repositories to load/save domain models defined in `model/`.

