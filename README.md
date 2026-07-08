# README

Start the application with:
```docker compose up```

### Access to the VM via ssh :

`ssh ubuntu@localhost -p 2222`

according to the forwarded port in virtualbox ubuntu server

Copy the docker-compose.yml file to the VM
next to it, create a .env file with the following content :
```
DB_URL=
DB_USER=
DB_PASSWORD=
```
All credentials for mySQL are in clear in the docker file :')
Security bof
`docker compose --env-file .env up`


### fonctionnement de WUD :

WUD surveille les conteneurs Docker locaux et interroge régulièrement
le registry (GHCR) pour comparer les digests des images.
Lorsqu’un changement est détecté, il déclenche automatiquement
un pull et un redémarrage du conteneur concerné.

```
1. Docker (VM)
   ↓
   WUD voit les conteneurs + images

2. GHCR
   ↓
   WUD demande :
   "quelle est la dernière version ?"

3. Comparaison
   ↓
   digest local ≠ digest remote ?

4. Action
   ↓
   docker pull
   docker restart

```
Pour la partie 4. Action, on ajoute dans `docker-compose.yml` sous l'app, 
`wud.tag.include: "release"` et `wud.trigger: "restart"` qui signifie :
surveille ce container-là et faire un restart dès qu'une nouvelle image
avec le tag release existe.