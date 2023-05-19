# Bataille Navale - Multijoueur

## ℹ Introduction et Prérequis

Notre projet est con

- Avoir une installation Java sur sa machine
- Posseder le fichier iut.algo disponible ici [lien du fichier]https://diw.iut.univ-lehavre.fr/pedago/info1/R1_01_Init_Dev/ressources/fichiersource/java_installation/iut.jar

## ⚙️ Instalation du Serveur 

1. Clonner le contenue de GitHub sur sa machine via : 
```shell
git clone https://github.com/Amaxi76/docker-sae203.git
```

2. Se déplacer dans le dossier src :
```shell
cd src
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
cd src
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





## Introduction

Pour cette SAE, nous avons décidé de créer une bataille navale en ligne, deux joueurs pourront s'affronter. Nous avons d'abord eu une autre idée de projet, faire un sudoku, mais après réflexion, nous n'avons pas trouvé de lien avec **docker**.   

## Principe du jeu

Nous avons donc réalisé notre bataille navale en *java*, avec une classe **Bateau** permettant de créer des bateaux en entrant leur coordonnée de début puis de fin. Puis une classe **Plateau** qui affiche deux plateaux, un contenant les bateaux du joueur puis le second qui marquera au fur et à mesure les attaques qu'il a envoyé au joueur adverse. Le but est donc qu'un des joueurs est touchés coulé tous les bateaux du joueur adverse, représentés sur notre plateau par des #.  

## Comment avons nous procédé

Nos fichiers **.java** sont donc en relation avec notre serveur, qui permet aux deux joueurs d'échanger, et de jouer chacun leur tour. En effet, nous avons utilisé nos connaissances en réseaux afin de pouvoir créer un serveur et clients, communiquant entre eux. Les joueurs peuvent donc se connecter indépendamment, et une fois que le serveur les a acceptés, la partie peut commencer en se laissant guider par nos commentaires.   

Le joueur peut rentrer les coordonnées de ses bateaux sur son plateau. Lors de la création des bateaux, plusieurs tests sont effectués afin d'éviter le plus d'erreurs possibles.   
Nos bateaux, par exemple ne peuvent pas se croiser, pour cela, lorsque nous créons un nouveau bateau, nous regardons dans notre *ArrayList* où les coordonnées sont stockées, s'ils sont déjà utilisées ou non. Si l'une des coordonnées est déjà présente le bateau ne pourra être créé.   
De plus, un test est réalisé pour que le bateau ne puisse pas faire qu'une case de longueur.   

Pour guider les joueurs, nous lui disons la longueur du bateau qu'il doit créer, et combien lui en reste-t-il a créer, car nous les avons stockés dans une *ArrayList*, donc grâce à la fonction **getNbBateauNonPlace**, nous pouvons afficher l'évolution des bateaux restant.   

Pour les plateaux, nous avons créé deux plateaux de 10 sur 10, les colonnes vont de **A** à **J** et les lignes de **1** à **10**.   
Nous disposons d'une fonction **attaquer**, qui bien évidemmment fonctionne que si les coordonnées sont dans le plateau, puis qui disposera une **X** sur son plateau attaque si un bateau ennemi a été touché. De même, cela disposera un **O** si aucun bateau n'est touché, ce qui correspond dans le jeu au plouf. Et une boucle for est intégrée à cette fonction afin de parcourir la liste où toutes les coordonnées sont enregistrées et si elles sont toutes déjà touchées alors les **X** se transforment en **#** pour représenter le touché coulé.   

Par la suite, nous avons, afin de respecter les vraies règles, fait en sorte que le joueur rejoue si celui-ci a réussi à toucher une partie d'un bateau ennemi. La partie se terminera lorsque tous les bateaux du joueur adverse seront touchés coulés. Un message sera affiché chez les deux joueurs, adapté évidemment. L'un verra s'afficher *VICTOIRE bien joué* et l'autre *Vous avez perdu*. Une fois ce message apparu, cela signifie que la partie est terminée, de plus, les joueurs seront déconnectés automatiquement. Un message sera affiché également afin d'en informer les joueurs.

## Problème rencontré
aucun parce que mes 2 co-équipiers alias les bff sont trop fort en java

## Amélioration possible
Faire une interface en IHM, en effet, nous nous sommes concentré à appliquer les vraies règles du jeu et faire un code java élaboré, mais une interface en IHM peut être envisageable, mais prendrait aussi beaucoup de temps.
