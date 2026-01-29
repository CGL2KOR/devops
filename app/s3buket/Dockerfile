################################
# Stage 1: Build
################################
FROM maven:3.9.9-eclipse-temurin-11 AS builder

WORKDIR /build

# Copy pom first for dependency caching
COPY pom.xml .
RUN mvn -B -e -C -DskipTests dependency:go-offline

# Copy source code
COPY src ./src

# Build application
RUN mvn -B clean package -DskipTests


################################
# Stage 2: Runtime
################################
FROM eclipse-temurin:11-jre-jammy

# Create non-root user
RUN useradd -ms /bin/bash spring

WORKDIR /app

# Copy only the jar from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Change ownership
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring

# Expose Spring Boot port
EXPOSE 8080

# JVM tuning for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
