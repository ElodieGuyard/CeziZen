# README

Start the application with:
```docker compose up```

### Access to the VM via ssh :

`ssh ubuntu@localhost -p 2222`

according to the forwarded port in virtualbox ubuntu server


`sudo docker run -d -p 8080:8080 ghcr.io/elodieguyard/cezizen:release
1c38b991dfc858cd17c2073762275d131550ec4e42947417e3203bc7ed32276b`


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
