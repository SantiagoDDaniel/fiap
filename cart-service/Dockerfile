# Cart Service Dockerfile
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /workspace

COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
VOLUME /tmp
COPY --from=build /workspace/target/cart-*.jar cart.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/cart.jar"]
