FROM eclipse-temurin:18.0.1_10-jre-alpine
# WORKDIR /target
COPY target/movies-email-service-0.0.1.jar .
EXPOSE 8082
CMD ["java", "-jar", "movies-email-service-0.0.1.jar"]