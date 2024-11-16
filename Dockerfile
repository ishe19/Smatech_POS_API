FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-19-jdk -y
COPY . .
RUN mvn clean install
RUN mvn package
RUN mvn dependency:copy-dependencies -DoutputDirectory=/usr/share/java/smatech-pos
FROM openjdk:19-jdk-slim
COPY --from=build /usr/share/java/smatech-pos /usr/share/java/tech.ishe.smatech_pos
COPY --from=build /target/smatech-pos-0.0.1-SNAPSHOT.jar /usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar
EXPOSE 9078
ENTRYPOINT ["java", "-jar", "/usr/share/java/tech.ishe.smatech_pos/smatech-pos.jar"]