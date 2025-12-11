FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/crudsupermercado-0.0.1.jar
COPY ${JAR_FILE} app.crudsupermercado.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.crudsupermercado.jar"]