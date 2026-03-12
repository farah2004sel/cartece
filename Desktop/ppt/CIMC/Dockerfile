FROM maven:3.8.5-openjdk-17 AS build
COPY src /tmp/src/
COPY pom.xml /tmp/
COPY sonar-project.properties /tmp/
WORKDIR /tmp/
RUN mvn clean package #-DskipTests

FROM openjdk:17
EXPOSE 8089
COPY --from=build /tmp/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test","/app.jar"]