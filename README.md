# Bataille Navale - Multijoueur

## ℹ Introduction et Prérequis

Notre projet est conçu pour fonctionner sur deux machines distinctes, donc sur deux terminals différents ( un par joueur et un pour le serveur ). 

### Partie Serveur
- Avoir une installation Java, git et Dockersur sur sa machine.
- Avoir le port 9000 ouvert pour les connexions entrantes.

### Partie Client 
- Avoir une installation Java et git sur sa machine.
- Posseder le fichier iut.algo disponible ici [le fichier](https://diw.iut.univ-lehavre.fr/pedago/info1/R1_01_Init_Dev/ressources/fichiersource/java_installation/iut.jar)


## ⚙️ Instalation du Serveur 

1. Clonner le contenue de GitHub sur sa machine via : 
```shell
git clone https://github.com/Amaxi76/docker-sae203.git
```

2. Se déplacer dans le dossier src :
```shell
cd docker-sae203/src
```

3. Construire un conteneur :
```shell
docker build -t bataillenavale .
```

4. Une fois que tout à fini d'être instatllé, lancer le docker :
```shell
docker run --rm -p 9000:9000 bataillenavale
```

Le serveur est prêt pour accueillir les deux joueurs.

## ⚙️ Instalation du Client

1. Clonner le contenue de GitHub sur sa machine via : 
```shell
git clone https://github.com/Amaxi76/docker-sae203.git
```

2. Se déplacer dans le dossier src :
```shell
cd docker-sae203/src
```

3. Javac le fichier Client.java  :
```shell
javac Client.java -encoding utf8
```

Vous êtes maintenant prêt à jouer ! 

## 💻 Mise en service

Pour se connecter au serveur, ils suffient au deux joueurs de se connecter avec le client via :
- Javac le fichier Client.java  :
```shell
javac Client.java -encoding utf8
```

## Comment avons nous procédé

Nos fichiers **.java** sont donc en relation avec notre serveur, qui permet aux deux joueurs d'échanger, et de jouer chacun leur tour. En effet, nous avons utilisé nos connaissances en réseaux afin de pouvoir créer un serveur et clients, communiquant entre eux. Les joueurs peuvent donc se connecter indépendamment, et une fois que le serveur les a acceptés, la partie peut commencer en se laissant guider par nos commentaires.   

Le joueur peut rentrer les coordonnées de ses bateaux sur son plateau. Lors de la création des bateaux, plusieurs tests sont effectués afin d'éviter le plus d'erreurs possibles.   
Nos bateaux, par exemple ne peuvent pas se croiser, pour cela, lorsque nous créons un nouveau bateau, nous regardons dans notre *ArrayList* où les coordonnées sont stockées, s'ils sont déjà utilisées ou non. Si l'une des coordonnées est déjà présente le bateau ne pourra être créé.   
De plus, un test est réalisé pour que le bateau ne puisse pas faire qu'une case de longueur.   

Pour guider les joueurs, nous lui disons la longueur du bateau qu'il doit créer, et combien lui en reste-t-il a créer, car nous les avons stockés dans une *ArrayList*, donc grâce à la fonction **getNbBateauNonPlace**, nous pouvons afficher l'évolution des bateaux restant.   

Pour les plateaux, nous avons créé deux plateaux de 10 sur 10, les colonnes vont de **A** à **J** et les lignes de **1** à **10**.   
Nous disposons d'une fonction **attaquer**, qui bien évidemmment fonctionne que si les coordonnées sont dans le plateau, puis qui disposera une **X** sur son plateau attaque si un bateau ennemi a été touché. De même, cela disposera un **O** si aucun bateau n'est touché, ce qui correspond dans le jeu au plouf. Et une boucle for est intégrée à cette fonction afin de parcourir la liste où toutes les coordonnées sont enregistrées et si elles sont toutes déjà touchées alors les **X** se transforment en **#** pour représenter le touché coulé.   

Par la suite, nous avons, afin de respecter les vraies règles, fait en sorte que le joueur rejoue si celui-ci a réussi à toucher une partie d'un bateau ennemi. La partie se terminera lorsque tous les bateaux du joueur adverse seront touchés coulés. Un message sera affiché chez les deux joueurs, adapté évidemment. L'un verra s'afficher *VICTOIRE bien joué* et l'autre *Vous avez perdu*. Une fois ce message apparu, cela signifie que la partie est terminée, de plus, les joueurs seront déconnectés automatiquement. Un message sera affiché également afin d'en informer les joueurs.

## 💬 Problèmes rencontrés

- Dans la partie Java, nous avons eu des difficultés à gérer les messages envoyés et reçus par le serveur tout en prenant en compte, quel joueur été autorisé à joueur.

- Dans la partie Docker, la plus grosse difficulté a été la compilation des fichiers par le conteneur, mais aussi l'accès par tous les membres du réseau à la machine hôte.

## ➕ Améliorations possibles

- Avec du temps supplémentaire, une interface homme machine aurait pu être mise en place pour éviter l'interaction via un terminal.

- Une autre amélioration possible, aurait été de faire plusieurs parties sur la même exécution pour pourvoir faire des statistiques.é
