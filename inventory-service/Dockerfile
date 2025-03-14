# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/inventory-service.jar app.jar

# Expose the port your service runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
