FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy the Maven/Gradle project files to leverage Docker cache for dependencies
COPY ../pom.xml .
COPY ../src ./src

# Build the application JAR file
RUN mvn clean package -DskipTests

# === Run Stage ===
# Use a JRE (Java Runtime Environment) image for the final, lightweight runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR file from the 'builder' stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]