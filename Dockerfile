FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/Online-Voting-System-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9061

ENTRYPOINT ["java","-jar","app.jar"]