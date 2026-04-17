# Stage 1: Build
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/auth-service-*.jar app.jar
RUN rm -f /app/*plain.jar 2>/dev/null || true
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
