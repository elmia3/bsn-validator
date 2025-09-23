# -------- Build image --------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# leverage Docker layer cache for dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# now copy sources and build
COPY src ./src
RUN mvn -q -DskipTests package

# -------- Runtime image --------
FROM eclipse-temurin:17-jre
WORKDIR /opt/app
COPY --from=build /app/target/bsn-validator-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
