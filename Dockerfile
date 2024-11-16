# Start from a base Ubuntu image
FROM ubuntu:20.04 AS build

# Set non-interactive mode to avoid prompting for input during apt-get installs
ARG DEBIAN_FRONTEND=noninteractive

# Update package lists and install prerequisites
RUN apt-get update && \
    apt-get install -y wget software-properties-common

# Install OpenJDK 17 and Maven
RUN apt-get install -y openjdk-17-jdk maven

# Copy the application files
COPY . .

# Build the Spring Boot application with Maven
RUN mvn clean install
RUN mvn package

# Copy the dependencies
RUN mvn dependency:copy-dependencies -DoutputDirectory=/usr/share/java/smatech-pos

# Start a new image with a smaller openjdk image for the final deployment
FROM openjdk:17-jdk-slim

# Copy the built JAR and dependencies to the final image
COPY --from=build /usr/share/java/smatech-pos /usr/share/java/tech.ishe.smatech_pos
COPY --from=build /target/smatech-pos-0.0.1-SNAPSHOT.jar /usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar

# Expose the port the app runs on
EXPOSE 9078

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar"]
