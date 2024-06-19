# Use the official Gradle image to build the application
FROM gradle:jdk17 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and the application source code to the container
COPY --chown=gradle:gradle . .

# Build the application
RUN gradle build --no-daemon

# Use a minimal base image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built application from the previous stage
COPY --from=builder /app/build/libs/payment-matcher-0.1.jar .

# Expose the port the application runs on
EXPOSE 8088

# Run the application
CMD ["java", "-jar", "payment-matcher-0.1.jar"]