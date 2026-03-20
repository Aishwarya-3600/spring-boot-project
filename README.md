# Spring boot project Workspace

## Common Setup

1. **Java Version:** Ensure Java 17+ is installed.
2. **Build Tool:** Maven Wrapper (`mvnw`) is provided for each project.
3. **IDE Support:** Compatible with IntelliJ IDEA, Eclipse, NetBeans, VS Code, etc.
4. **Environment Variables:** Configure in `application.properties` as needed.

## Workspace Structure

```
combined-folder/
├── activmq01/
├── demo-redis/
├── practice01/
└── README.md
```

## Microservices Overview

- **CRUD Operations:** All microservices support Create, Read, Update, and Delete operations on their entities via REST endpoints.

### activmq01 & practice01
- **Interconnection:**
  - `activmq01` and `practice01` are interconnected microservices.
  - They communicate via messaging (ActiveMQ) and REST APIs.
  - Typical use case: `practice01` sends/receives messages to/from `activmq01` for asynchronous processing.
- **Elasticsearch Integration (practice01):**
  - `practice01` integrates with Elasticsearch for simple entity indexing.
  - When an entity is created, its info is stored in both the database and an Elasticsearch index.
  - This enables basic search and retrieval functionality alongside persistent storage.
  - Configuration and usage details are managed in `application.properties` and relevant service classes.

### demo-redis
- **Description:** Standalone Spring Boot microservice demonstrating Redis integration.
- **Architecture:**
  - Operates independently from other microservices.
  - Provides caching and data storage features using Redis.

## Running Microservices

Start each microservice in a separate terminal:

```bash
cd activmq01
./mvnw spring-boot:run
```

```bash
cd practice01
./mvnw spring-boot:run
```

```bash
cd demo-redis
./mvnw spring-boot:run
```

Ensure ActiveMQ broker and Redis server are running and accessible to the respective services.

> **Usage:** Run microservices using terminal commands as shown below, or use IDE-specific run configurations if preferred.

## Subfolder Details

### activmq01
- **Description:** Spring Boot microservice integrating ActiveMQ for messaging.
- **Structure:**
  - `src/main/java/com/example/activmq01/` — Main application code
  - `src/main/resources/` — Configuration and static files
  - `src/test/java/com/example/activmq01/` — Test code
- **Setup:**
  - Edit `application.properties` for configuration (ActiveMQ broker URL, etc.)
- **Interconnection:**
  - Exposes endpoints and message listeners for integration with `practice01`.

### practice01
- **Description:** Spring Boot microservice for business logic, interconnected with `activmq01`.
- **Structure:**
  - `src/main/java/com/example/practice01/` — Main application code
  - `src/main/resources/` — Configuration and static files
  - `src/test/java/com/example/practice01/` — Test code
- **Setup:**
  - Edit `application.properties` for configuration (ActiveMQ broker URL, etc.)
- **Interconnection:**
  - Sends/receives messages to/from `activmq01` using ActiveMQ.
  - May call REST endpoints exposed by `activmq01`.

### demo-redis
- **Description:** Standalone Spring Boot microservice demonstrating Redis integration.
- **Structure:**
  - `src/main/java/com/example/demo_redis/` — Main application code
  - `src/main/resources/` — Configuration and static files
  - `src/test/java/com/example/demo_redis/` — Test code
- **Setup:**
  - Edit `application.properties` for configuration (Redis host, etc.)

## Testing

Run tests using Maven:

```bash
./mvnw test
```

## Notes
- Each project is independent; dependencies are managed per project.
- `.gitignore` and `.gitattributes` files are set up for common IDEs and build artifacts.
- For messaging and caching demos, ensure ActiveMQ and Redis are running locally or update `application.properties` accordingly.
- Modify anything as required for your local environment or deployment.

---

Feel free to use these projects as templates for your own Spring Boot applications.
