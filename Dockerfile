# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy
LABEL authors="Pogos"

COPY target/BBtoES-0.0.1-SNAPSHOT.jar BBtoES-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/BBtoES-0.0.1-SNAPSHOT.jar"]