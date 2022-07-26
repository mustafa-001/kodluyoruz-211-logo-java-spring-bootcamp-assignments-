FROM eclipse-temurin:18.0.1_10-jre-alpine
# RUN movies-payment-service/mvnw clean package
COPY ./target/movies-payment-service-0.0.1.jar .
EXPOSE 8081
CMD ["java", "-jar", "movies-payment-service-0.0.1.jar"]