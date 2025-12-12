
# Etapa 1: Compilar con Maven
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Copiar archivos de Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copiar código fuente
COPY src src

# Dar permisos y compilar
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecutar la aplicación
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR compilado
COPY --from=builder /app/target/crudsupermercado-0.0.1.jar app.jar

# Exponer puerto
EXPOSE 8080

# Comando para iniciar
ENTRYPOINT ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]