# sae_2.03_grp_08

**Nom :** Yanis Verdier | Maximilien Lesterlin | Célia Antunes   
**Groupe :** B équipe 08   
**Année :** 2023   
**IUT Le Havre - SAE** 

## Introduction

Pour cette SAE, nous avons décidé de créer une bataille navale en ligne, deux joueurs pourront s'affronter. Nous avons d'abord eu une autre idée de projet, faire un sudoku, mais après réflexion, nous n'avons pas trouvé de lien avec docker.   

## Principe du jeu

Nous avons donc réalisé notre bataille navale en java, avec une classe Bateau permettant de créer des bateaux en entrant leur coordonnée de début puis de fin. Puis une classe Plateau qui affiche deux plateaux, un contenant les bateaux du joueur puis le second qui marquera au fur et à mesure les attaques qu'il a envoyé au joueur adverse. Le but est donc qu'un des joueurs est touchés coulé tous les bateaux du joueur adverse, représentés sur notre plateau par des #.  

## Comment avons nous procédé

Nos fichiers **.java** sont donc en relation avec notre serveur, qui permet aux deux joueurs d'échanger, et de jouer chacun leur tour. Les joueurs peuvent se connecter indépendamment, et une fois que le serveur les a accepté, la partie peut commencer.   

Le joueur peut rentrer les coordonnées de ses bateaux sur son plateau. Lors de la création des bateaux plusieurs test sont effectués afin d'éviter le plus d'erreurs possible.   
Nos bateaux, par exemple ne peuvent pas se croiser, pour cela, lorsque nous créons un nouveau bateau, nous regardons dans notre *ArrayList* où les coordonnées sont stockées, s'ils sont déjà utilisées ou non. Si l'une des coordonnées est déjà présente le bateau ne pourra être créé.   
De plus, un test est réalisé pour que le bateau ne peuve faire une case de longueur.   

Pour guider les joueurs, nous lui disons la longueur du bateau qu'il doit créer, et combien lui en reste-t-il a créer, car nous les avons stockés dans une *ArrayList*.   

Pour les plateaux, nous avons créé deux plateaux de 10 sur 10, les colonnes vont de **A** à **J** et les lignes de **1** à **10**.   
Nous disposons d'une fonction **attaquer**, qui bien evidemmment fonctionne qui si les coordonnées sont dans le plateau, puis disposera une **X** sur son plateau attaque si un bateau ennemi a été touché. De même, cela disposera **O** si aucun bateau est touché, ce qui correspond dans le jeu au plouf. Et une boucle for est intégrée à cette fonction afin de parcourir la liste où toutes les coordonnées sont enregistrés et si elles sont toutes déjà touchées alors les **X** se transforme en **#** pour représenter le touché coulé.

## Problème rencontré

## Amélioration possible
Faire une interface en IHM

