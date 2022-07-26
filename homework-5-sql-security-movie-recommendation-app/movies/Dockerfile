FROM eclipse-temurin:18.0.1_10-jre-alpine
# WORKDIR /target
COPY target/movies-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "movies-0.0.1-SNAPSHOT.jar"]