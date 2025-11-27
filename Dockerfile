FROM maven:3.9.10-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

COPY --from=build /app/target/v2-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]