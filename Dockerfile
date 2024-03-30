# Use a base image with Java 17
FROM openjdk:17

# Copy the JAR package into the image
ARG JAR_FILE=target/Library-Management-System-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} Library-Management-System-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8091

# Run the App
ENTRYPOINT ["java", "-jar", "/Library-Management-System-0.0.1-SNAPSHOT.jar"]

#mvn clean package -Pprod -DskipTests
#docker build -t lms_app:latest .
#docker run -p 8080:8080 lms_app:latest


