FROM maven:3.9.10-eclipse-temurin-17-alpine AS build 
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine 
EXPOSE 8080 
COPY --from=build /target/*.jar app.jar 
ENTRYPOINT ["java", "-jar", "app.jar"]