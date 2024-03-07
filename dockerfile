# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY application/target/fii-practic-final-project-1.0.0-SNAPSHOT.jar /app

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "fii-practic-final-project-1.0.0-SNAPSHOT.jar"]