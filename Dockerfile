FROM maven:3.9.10-eclipse-temurin-17-alpine AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY .  . 

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080 

COPY --from=build /target/*.jar app.jar 

ENTRYPOINT ["java", "-jar", "app.jar"]