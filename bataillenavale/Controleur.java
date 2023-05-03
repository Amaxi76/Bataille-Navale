package bataillenavale;

import bataillenavale.metier.*;
import iut.algo.Clavier;

public class Controleur
{
	public static void main(String[] args)
	{
		Jeu jeu = new Jeu();
		Plateau plateau = new Plateau(jeu);
		plateau.initialiserBateaux(2, 3, 3, 4, 5);

		Coordonnees c1 = new Coordonnees ( 'A', 7  );
		Coordonnees c2 = new Coordonnees ( 'A', 10 );

		System.out.println("Coords de bateau de taille 3 :"); // A 7 A 10
		plateau.placerBateau ( c1, c2, 4 );
		plateau.placerBateau ( new Coordonnees ( 'H', 1  ) , new Coordonnees ( 'H', 2 ), 2);
		plateau.placerBateau ( new Coordonnees ( 'B', 7  ) , new Coordonnees ( 'F', 7 ), 5);
		plateau.placerBateau ( new Coordonnees ( 'D', 2  ) , new Coordonnees ( 'F', 2 ), 3);
		
		/*String posDep, posArr;
		do
		{
			System.out.println("Rentrer les coordonnées de départ du bateau de taille 3");
			posDep = Clavier.lireString();

			System.out.println("Rentrer les coordonnées de départ du bateau de taille 3");
			posArr = Clavier.lireString();
		}
		while (!plateau.placerBateau ( new Coordonnees ( posDep.charAt(0), Integer.parseInt(posDep.charAt(1) + "") ) , new Coordonnees ( posArr.charAt(0), Integer.parseInt(posArr.charAt(1) + "") ), 3));
		*/

		System.out.println ( plateau.toString ( ) );
		
	}
}