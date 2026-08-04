# Spring Boot Backend

This project was generated using Spring Boot and is built with Maven.

## Prerequisites

Before running the application, make sure you have:

- Java 17+ (or the version required by your project)
- Maven 3.8+
- A configured database (if applicable)

Check your installations:

```bash
java -version
mvn -version
```

## Development server

To start the Spring Boot application, run:

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080/
```

The server automatically restarts when using Spring Boot DevTools (if included in the project).

## Building

To build the project, run:

```bash
mvn clean package
```

The generated JAR file will be available in the `target/` directory.

## Running the packaged application

After building the project, run:

```bash
java -jar target/<your-application-name>.jar
```

Example:

```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## Clean project

To remove previously compiled files:

```bash
mvn clean
```

## Running tests

To execute all unit and integration tests:

```bash
mvn test
```

## Package without tests

To build the project while skipping tests:

```bash
mvn clean package -DskipTests
```

## Install dependencies

To download dependencies and install the project into your local Maven repository:

```bash
mvn clean install
```

## Dependency tree

To view the project's dependency tree:

```bash
mvn dependency:tree
```

## Verify project

To run validation, compilation, tests, and package verification:

```bash
mvn verify
```

## API Documentation

If SpringDoc OpenAPI (Swagger) is enabled, access:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```
http://localhost:8080/v3/api-docs
```

## Running with a specific profile

Run the application using a Spring profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

or

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Running with custom properties

Example:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8000"
```

## Maven Wrapper (recommended)

If your project includes the Maven Wrapper, use:

Run application:

```bash
./mvnw spring-boot:run
```

Build:

```bash
./mvnw clean package
```

Test:

```bash
./mvnw test
```

Windows:

```cmd
mvnw.cmd spring-boot:run
```

## Additional Resources

For more information, visit:

- Spring Boot Documentation: https://docs.spring.io/spring-boot/docs/current/reference/html/
- Spring Initializr: https://start.spring.io/
- Spring Guides: https://spring.io/guides
- Maven Documentation: https://maven.apache.org/guides/