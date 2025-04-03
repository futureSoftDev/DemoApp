#FROM openjdk:21
#LABEL maintainer="Shukhratjon Rayimjonov"
#WORKDIR /app
#EXPOSE 8085
#COPY target/employee-management-system-app.jar ./app.jar
#ENTRYPOINT ["java", "-jar","./app.jar"]

FROM maven AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21
LABEL maintainer="Shukhratjon Rayimjonov"
WORKDIR /app
EXPOSE 8081
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]


