# Order Service Dockerfile
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

COPY . .

# Build with Maven and download dependencies first for better caching
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

RUN apk add --no-cache curl

WORKDIR /app

COPY --from=builder /app/target/order-service-*.jar app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring

EXPOSE 8082

HEALTHCHECK --interval=30s --timeout=3s --start-period=120s --retries=3 \
    CMD curl -f http://localhost:8082/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]