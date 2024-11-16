# Start from a base image
FROM ubuntu:20.04 AS build

# Set non-interactive mode to avoid prompting for input during apt-get installs
ARG DEBIAN_FRONTEND=noninteractive

# Update package lists and install prerequisites for adding repositories
RUN apt-get update && \
    apt-get install -y wget software-properties-common

# Add the PPA repository for OpenJDK 19
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update

# Install OpenJDK 19
RUN apt-get install -y openjdk-19-jdk

# Copy the application files and install dependencies
COPY . .

# Build the Spring Boot application
RUN mvn clean install
RUN mvn package

# Copy the dependencies
RUN mvn dependency:copy-dependencies -DoutputDirectory=/usr/share/java/smatech-pos

# Start a new image with a smaller openjdk image
FROM openjdk:19-jdk-slim

# Copy dependencies and the built jar to the final image
COPY --from=build /usr/share/java/smatech-pos /usr/share/java/tech.ishe.smatech_pos
COPY --from=build /target/smatech-pos-0.0.1-SNAPSHOT.jar /usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar

# Expose the port your app runs on
EXPOSE 9078

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar"]
