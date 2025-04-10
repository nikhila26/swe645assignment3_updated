# Stage 1: Build the application
FROM maven:3.9.2 AS build
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the application (skipping tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-slim
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (default for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
