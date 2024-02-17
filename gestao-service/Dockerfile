# Item Service Dockerfile
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /workspace

COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
VOLUME /tmp
COPY --from=build /workspace/target/gestao-*.jar gestao.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/gestao.jar"]