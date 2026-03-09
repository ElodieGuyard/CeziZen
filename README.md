# CESI ZEN

## Run the project :

```shell
docker-compose up
```

## For success connection with dbeaver (or your pref) in local
not up to date as command: `--default-authentication-plugin=mysql_native_password` is added to the mysql conf  
TODO: document the new process to connect with dbeaver (or another)
allowPublicKeyRetrieval = true
useSSL = false (if you run it in local)

or in the url for example : `jdbc:mysql://localhost:3306/cesizen?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC`

# Notes

controller → routes web
service → logique métier
repository → accès base de données
security → configuration sécurité

## fonctionnement global auth

Utilisateur
↓
GET /dashboard
↓
Spring Security
↓
redirect /login
↓
LoginController
↓
login.html (Thymeleaf)
↓
POST /login (formulaire)
↓
Spring Security
↓
Authentification
↓
OK → /dashboard
KO → /login?error