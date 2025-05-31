FROM openjdk:21-jdk-slim
WORKDIR /app
COPY ./target/Demo-Project-for-Jenkins-with-Springboot-0.0.1-SNAPSHOT.jar /app
EXPOSE 8082
CMD ["java", "-jar", "Demo-Project-for-Jenkins-with-Springboot-0.0.1-SNAPSHOT.jar"]