# User Service Dockerfile
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /workspace

COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
VOLUME /tmp
COPY --from=build /workspace/target/user-service*.jar user-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/user-service.jar"]