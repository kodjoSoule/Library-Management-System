# Use a base image with Java 17
FROM openjdk:17

# Copy the JAR package into the image
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
COPY ./target/Library-Management-System-0.0.1-SNAPSHOT.jar Library-Management-System-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 80

# Run the App
ENTRYPOINT ["java", "-jar", "/Library-Management-System-0.0.1-SNAPSHOT.jar"]

#mvn clean package -Pprod -DskipTests
#docker build -t lms_app:latest .
#docker run -p 8080:8080 lms_app:latest


