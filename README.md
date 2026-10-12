# TP1-DAR2026 - Application client-serveur

## Objectif

Ce projet met en œuvre une communication TCP simple entre deux applications Java : un serveur et un client.

## Serveur

Le serveur ouvre un socket d'écoute sur le port `1234` et attend la connexion d'un client. La méthode `accept()` bloque tant qu'aucun client ne s'est connecté. Après la connexion, le serveur affiche un message, puis ferme le socket client et le socket serveur.

Lancer `ServerPackage.Server` en premier.

Sortie avant la connexion :

```text
Je suis un serveur en attente la connexion d'un client
```

Sortie après la connexion :

```text
un client est connecté
```

<!-- Ajouter ici la capture d'écran de la console du serveur. -->

## Client

Le client affiche un message avant de se connecter, puis ouvre une connexion vers l'adresse `127.0.0.1` sur le port `1234`. Une fois connecté, il affiche un message de confirmation et ferme son socket.

Après le démarrage du serveur, lancer `ClientPackage.Client`.

Sortie attendue dans la console du client :

```text
Je suis un client pas encore connecté…
je suis un client connecté
```

<!-- Ajouter ici la capture d'écran de la console du client. -->

## Interprétation des résultats

Le serveur doit être lancé avant le client. Il reste en attente tant qu'aucune connexion n'arrive. Quand le client se connecte, le serveur et le client affichent chacun leur message de confirmation. Les deux applications ferment ensuite leurs sockets et terminent leur exécution.

## Connexion avec une adresse IP

L'adresse `127.0.0.1` correspond à la machine locale. Pour connecter un client situé sur une autre machine, remplacer cette adresse dans `ClientPackage.Client` par l'adresse IP de la machine qui exécute le serveur. Le serveur et le client doivent utiliser le même port (`1234`), et le pare-feu doit autoriser la connexion.

## Dépôt Git

Lien du repository : https://github.com/nourhbaieb88/TP1-DAR2026.git
