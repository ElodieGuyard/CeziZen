# Stage 1 : Construction de l'application

`FROM maven:3.9.6-eclipse-temurin-21 AS builder`
= "Commence à partir d'une image Docker qui contient déjà Maven et Java 21."

L'image contient déjà :
```
Linux
   │
   ├── Java JDK 21
   ├── Maven 3.9.6
   └── tout ce qu'il faut pour compiler
```
Le mot `AS builder` donne simplement un nom à ce stage.  
Plus tard on pourra écrire : `COPY --from=builder` pour récupérer les fichiers construits.  

```
WORKDIR
WORKDIR /workspace
```

Docker ouvre un terminal et se direge vers le répoertoire `/workspace`.  
Toutes les commandes suivantes seront exécutées ici.

Equivalent Linux :
```
cd /workspace
COPY
COPY pom.xml mvnw.cmd ./
```
Docker copie depuis le projet :
```
pom.xml
mvnw.cmd
```
vers `/workspace`

Donc on obtient :
```
/workspace
│
├── pom.xml
└── mvnw.cmd
```

> Remarque : mvnw.cmd est le wrapper Maven pour Windows. Dans ce Dockerfile, comme on utilise directement l'image maven, il n'est généralement pas nécessaire.

Docker copie le code source
`COPY src ./src`

Docker copie tout le dossier : `src/` vers `/workspace/src`

On obtient :
```
workspace
│
├── pom.xml
└── src
    ├── main
    └── test
```
Compilation
`RUN mvn -B package`

Le mot-clé RUN signifie : "Exécute cette commande pendant la construction de l'image."

C'est exactement comme lancer : `mvn package`

L'option : `-B` signifie : `Batch mode`, c'est recommandé pour Docker car Maven n'affiche pas d'interface interactive. Cette commande :  

- télécharge les dépendances
- compile le code
- lance les tests (par défaut)
- crée le fichier
```
target/
    monProjet.jar
```  
À la fin du stage Builder :
```
workspace
│
├── pom.xml
├── src
└── target
      └── CeziZen.jar
```
# Stage 2 : Image finale

`FROM eclipse-temurin:21-jre-noble`

Ici Docker oublie complètement le premier stage. On repart d'une nouvelle image.  
Cette fois elle contient uniquement :
```
Linux
   │
   └── Java Runtime (JRE)
```

Il n'y a plus :

- Maven
- le code source
- les dépendances de compilation

C'est ce qui rend l'image finale beaucoup plus légère.

## Les labels
`LABEL org.opencontainers.image.title="CeziZen"`

Les labels sont des métadonnées. On peut voir ces informations avec :  

`docker inspect`

Par exemple :

- Titre
- Description
- Version
- Auteur
- Repository Git

Ils servent surtout à documenter l'image, aux outils comme Watchtower ou WUD, et aux registres Docker.  

## Création d'un utilisateur

`RUN groupadd -r appgroup && useradd -r -g appgroup -m appuser`

Cette ligne crée :
```
groupe
    appgroup

utilisateur
    appuser
```
### Pourquoi ?

Par défaut un conteneur tourne en utilisteur `root` ce qui n'est pas conseillé. À la place on crée : `appuser` qui possède beaucoup moins de droits. C'est une bonne pratique de sécurité.

## Nouveau dossier de travail

`WORKDIR /appDocker`

Équivalent :
```
cd /appDocker
Copier le jar
COPY --from=builder /workspace/target/*.jar CesiZen-0.0.1-SNAPSHOT.jar
```
Elle signifie : "Va dans le stage builder, récupère le JAR généré et copie-le dans cette nouvelle image."

Schéma :
```
Builder

workspace
   │
   └── target
          └── CeziZen.jar
                │
                ▼
Runtime

/appDocker
      │
      └── CesiZen-0.0.1-SNAPSHOT.jar
```

Le code source n'est pas copié. Seul le JAR l'est.

## Changer le propriétaire

`RUN chown appuser:appgroup /appDocker/CesiZen-0.0.1-SNAPSHOT.jar`

Le propriétaire devient : `appuser` au lieu de : 'root`.  

Ainsi l'utilisateur non privilégié pourra lire et exécuter le fichier.

## Utilisateur actif

`USER appuser`

À partir d'ici toutes les commandes seront exécutées en tant que : `appuser` et non plus : `root`

## Port exposé

`EXPOSE 8080`

Cela indique que l'application écoute sur le port 8080.

Important : `EXPOSE` n'ouvre pas le port. Il sert principalement de documentation et peut être utilisé par certains outils Docker.

Pour rendre le service accessible depuis ta machine, il faut publier le port lors du lancement :

`docker run -p 8080:8080 mon-image`

## Point d'entrée

`ENTRYPOINT ["java","-jar","/appDocker/CesiZen-0.0.1-SNAPSHOT.jar"]`

C'est la commande lancée au démarrage du conteneur. Docker exécute :

`java -jar /appDocker/CesiZen-0.0.1-SNAPSHOT.jar`

L'application Spring Boot démarre alors.

# Résumé du cycle complet  
```
Le projet
│
├── src/
├── pom.xml
│
▼
Stage Builder
│
├── Maven
├── JDK
├── Compilation
└── target/app.jar
│
▼
Stage Runtime
│
├── JRE
├── Copie uniquement app.jar
├── Création d'un utilisateur non-root
└── java -jar app.jar
│
▼
Conteneur lancé
│
└── Application Spring Boot disponible sur le port 8080
```
