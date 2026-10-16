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
- [Example API requests](#example-api-requests)
- [Project layout](#project-layout)
- [How to get help](#how-to-get-help)
- [Maintainers & contributing](#maintainers--contributing)

## What this project does
Car Parts REST API exposes HTTP endpoints to:

- List and query car parts
- Manage a user shopping cart (add/remove items)
- Perform basic user-related actions (create/lookup)

The application entry point is src/main/java/com/mycompany/carpartsapp/CarpartsappApplication.java and HTTP endpoints live under src/main/java/com/mycompany/carpartsapp/controller.

## Why it’s useful
- Small, focused codebase ideal for learning REST controllers, service layering, and simple persistence patterns in Java.
- Ready-to-run Maven project (mvnw included) that can be extended into a production prototype or used for demos.
- Clear separation of concerns (controllers, services, repositories, models) makes it easy to add features or swap implementations.

## Tech stack
- Language: Java
- Build: Maven (wrapper included: mvnw / mvnw.cmd)
- Typical framework: Spring Boot-style project layout (main class + controllers/services)

Notable files:
- pom.xml — project build and dependencies
- src/main/java/com/mycompany/carpartsapp/ — application code

## Prerequisites
- JDK 17+ (or the version configured in pom.xml)
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

Configuration files are under src/main/resources — supply Spring properties or environment variables to configure ports, datasources, and profiles.

## Testing
Unit tests live under src/test. Run them with:

```bash
./mvnw test
```

Add tests under src/test/java to cover controllers and services before opening PRs.

## Example API requests
Use the following examples as a starting point. Adjust the paths/JSON to match the models in src/main/java/com/mycompany/carpartsapp/model.

- List parts (GET)

```bash
curl -sS http://localhost:8080/api/parts
```

- Get a single part (GET)

```bash
curl -sS http://localhost:8080/api/parts/{partId}
```

- Add an item to cart (POST)

```bash
curl -X POST http://localhost:8080/api/cart \
  -H "Content-Type: application/json" \
  -d '{"partId":"123","quantity":2,"userId":"user-1"}'
```

- Create a user (POST)

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","email":"alice@example.com"}'
```

Refer to the model classes in src/main/java/com/mycompany/carpartsapp/model for the exact request/response fields.

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

How it fits together: controllers handle HTTP requests and delegate to services; services contain business logic and use repositories to load/save domain models defined in model/.

## How to get help
- Open an issue in this repository with a clear title and reproduction steps.
- Inspect the source under src/main/java for examples of request/response shapes.

When filing an issue include: endpoint path, request payload, response (or server logs), and steps to reproduce.

## Maintainers & contributing
Maintainer: Exclusioning (GitHub repository owner)

Contributing (brief):
1. Fork the repository and create a branch: git checkout -b feat/short-description
2. Implement changes and add tests
3. Run tests: ./mvnw test
4. Push and open a pull request against the main branch

If you plan large or breaking changes, please open an issue first to discuss design and API changes.

---

If you want, I can also add a CONTRIBUTING.md and fill in more precise run/config examples after you confirm the target Java version and any expected external services (database, cache).