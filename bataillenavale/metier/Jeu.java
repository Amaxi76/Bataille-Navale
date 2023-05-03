package bataillenavale.metier;

import java.util.*;

public class Jeu
{
	private Plateau[] ensPlateaux;

	public Jeu()
	{
		this.ensPlateaux    = new Plateau[2];
		this.ensPlateaux[0] = new Plateau(this);
		this.ensPlateaux[1] = new Plateau(this);

		for (Plateau plt : this.ensPlateaux)
		{
			plt.initialiserBateaux(2, 3, 3, 4, 5);
		}
	}

	public boolean estTouche(Coordonnees c, Plateau plt)
	{
		if (plt.equals(this.ensPlateaux[0]))
			if (this.ensPlateaux[1].contientBateau(c)) return true;

		if (plt.equals(this.ensPlateaux[1]))
			if (this.ensPlateaux[0].contientBateau(c)) return true;

		return false;
	}

	public boolean placerBateau(int numJoueur  ,Coordonnees posDep, Coordonnees posFin, int taille)
	{
		if (numJoueur == 1)
			return this.ensPlateaux[0].placerBateau(posDep, posFin, taille);
		else
			return this.ensPlateaux[1].placerBateau(posDep, posFin, taille);
	}

	public ArrayList<Bateau> getNbBateauNonPlace(int numJoueur)
	{
		if (numJoueur == 1)
			return this.ensPlateaux[0].getNbBateauNonPlace();
		else
			return this.ensPlateaux[1].getNbBateauNonPlace();
	}

	public boolean attaquer(int joueur, Coordonnees c)
	{
		if (joueur == 1)
			return this.ensPlateaux[0].attaquer(c);
		else
			return this.ensPlateaux[1].attaquer(c);
	}

	public String toString(int joueur)
	{
		if (joueur == 1)
			return this.ensPlateaux[0].toString();
		else
			return this.ensPlateaux[0].toString();
	}
		
	public static void main(String[] args)
	{
		Jeu jeu = new Jeu();

		while (jeu.getNbBateauNonPlace(1).size() != 0)
		{
			jeu.placerBateau (1, new Coordonnees ( 'A', 1  ) , new Coordonnees ( 'A', 4 ), 4);
			jeu.placerBateau (1, new Coordonnees ( 'H', 1  ) , new Coordonnees ( 'H', 2 ), 2);
			jeu.placerBateau (1, new Coordonnees ( 'B', 7  ) , new Coordonnees ( 'F', 7 ), 5);
			jeu.placerBateau (1, new Coordonnees ( 'D', 2  ) , new Coordonnees ( 'F', 2 ), 3);
			jeu.placerBateau (1, new Coordonnees ( 'J', 8  ) , new Coordonnees ( 'J', 10), 3);
		}

		while (jeu.getNbBateauNonPlace(2).size() != 0)
		{
			jeu.placerBateau (2, new Coordonnees ( 'A', 1  ) , new Coordonnees ( 'A', 4 ), 4);
			jeu.placerBateau (2, new Coordonnees ( 'H', 1  ) , new Coordonnees ( 'H', 2 ), 2);
			jeu.placerBateau (2, new Coordonnees ( 'B', 7  ) , new Coordonnees ( 'F', 7 ), 5);
			jeu.placerBateau (2, new Coordonnees ( 'D', 2  ) , new Coordonnees ( 'F', 2 ), 3);
			jeu.placerBateau (2, new Coordonnees ( 'J', 8  ) , new Coordonnees ( 'J', 6 ), 3);
		}

		System.out.println(jeu.toString(1));
		System.out.println(jeu.attaquer(1, new Coordonnees('A', 5)));
		System.out.println(jeu.toString(1));
		System.out.println(jeu.attaquer(1, new Coordonnees('A', 2)));
		System.out.println(jeu.toString(1));
		System.out.println(jeu.attaquer(1, new Coordonnees('A', 1)));
		System.out.println(jeu.attaquer(1, new Coordonnees('A', 3)));
		System.out.println(jeu.attaquer(1, new Coordonnees('A', 4)));
		System.out.println(jeu.toString(1));
	}
}
