# Stage 1: builder avec Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /workspace
COPY pom.xml mvnw.cmd ./
# Copier le code source
COPY src ./src
# Build le jar
RUN mvn -B package

# Stage 2: runtime
FROM eclipse-temurin:21-jre-noble

# Ajouter les labels pour WUD et les métadonnées
LABEL org.opencontainers.image.title="CeziZen"
LABEL org.opencontainers.image.description="CeziZen application"
LABEL org.opencontainers.image.version="${VERSION:-1.0.0}"
LABEL org.opencontainers.image.source="https://github.com/ElodieGuyard/CeziZen"

# Créer un groupe et un utilisateur non-root
RUN groupadd -r appgroup && useradd -r -g appgroup -m appuser

WORKDIR /appDocker
# Récupère l'artefact buildé depuis le stage builder
COPY --from=builder /workspace/target/*.jar CesiZen-0.0.1-SNAPSHOT.jar

# Donner la propriété du jar à l'utilisateur non-root
RUN chown appuser:appgroup /appDocker/CesiZen-0.0.1-SNAPSHOT.jar

USER appuser
EXPOSE 8080
# Ajuste la commande si tu utilises Layered jars / properties
ENTRYPOINT ["java","-jar","/appDocker/CesiZen-0.0.1-SNAPSHOT.jar"]