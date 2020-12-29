FROM openjdk:8-jre-slim

ARG JAR_FILE=target/auth-jwt-service-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
