# Step 1: Base image with Java 17
FROM eclipse-temurin:17-jdk

# Step 2: Add jar to container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Step 3: Expose port
EXPOSE 8080

# Step 4: Run the jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
