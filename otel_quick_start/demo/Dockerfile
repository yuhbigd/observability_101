# Use an official OpenJDK runtime as a parent image
FROM openjdk:17.0-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar app.jar

# Declare ARG instructions for environment variables
ARG OTLP_ENDPOINT
ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD
ARG OTEL_METRIC_EXPORT_INTERVAL

# Set environment variables using the ARG values
ENV OTLP_ENDPOINT=${OTLP_ENDPOINT}
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV OTEL_METRIC_EXPORT_INTERVAL=${OTEL_METRIC_EXPORT_INTERVAL}

EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]