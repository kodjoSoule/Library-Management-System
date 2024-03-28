FROM openjdk:17-jdk-alpine

MAINTAINER souleymanekodjo@gmail.com

# Ajouter le jar construit dans le conteneur
COPY ./target/Library-Management-System-0.0.1-SNAPSHOT.jar Library-Management-System-0.0.1-SNAPSHOT.jar
#Expose the port

EXPOSE 8080

# Commande d'entrée
ENTRYPOINT ["java", "-jar", "/Library-Management-System-0.0.1-SNAPSHOT.jar"]

#mvn clean package -Pprod -DskipTests
#docker build -t library-Management-System:latest .
#docker run -p 8080:8080 library-Management-System:latest
