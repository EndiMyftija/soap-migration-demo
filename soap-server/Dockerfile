# Stage 1 - build the .jar
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /workspace

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: Extract layers using Spring Boot's jarmode
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /builder

# Note: Maven's output directory is 'target', not 'build/libs'
ARG JAR_FILE=/workspace/target/*.jar
COPY --from=build ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract --destination extracted
# Stage 3: Final Runtime Image
FROM eclipse-temurin:25-jre-alpine
WORKDIR /application

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy layers from the builder stage
# Standard Spring Boot layer order: dependencies -> loader -> snapshots -> application
COPY --chown=spring:spring --from=builder /builder/extracted/dependencies/ ./
COPY --chown=spring:spring --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --chown=spring:spring --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --chown=spring:spring --from=builder /builder/extracted/application/ ./

USER spring

# Since we extracted the layers, we run the Launcher rather than a specific jar file
# This is the most efficient way to run layered Spring Boot apps
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
