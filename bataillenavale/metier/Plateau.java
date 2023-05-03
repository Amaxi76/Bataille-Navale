package bataillenavale.metier;

import java.util.ArrayList;

public class Plateau
{
	private static final int TAILLE = 10;

	private char[][] pltBateaux;
	private char[][] pltAttaques;
	private Bateau[] bateaux;
	private Jeu      jeu;
	private ArrayList<Coordonnees> attaques;

	public Plateau(Jeu jeu)
	{
		this.pltBateaux  = new char[10][10];
		this.initialiserTableau(this.pltBateaux);
		this.pltAttaques = new char[10][10];
		this.initialiserTableau(this.pltAttaques);
		this.bateaux     = new Bateau[5];
		this.jeu         = jeu;
		this.attaques    = new ArrayList<>();
	}

	public boolean attaquer(Coordonnees c)
	{
		if (c.getLig()-1 > Plateau.TAILLE || c.getCol() > (char)('A' + Plateau.TAILLE) || this.attaques.contains(c)) return false;

		if (this.jeu.estTouche(c, this))
		{
			this.pltAttaques[c.getLig()-1][c.getCol() - 'A'] = 'X';
			for (Bateau b : this.bateaux)
			{
				for (Coordonnees cos : b.getCoordonnees())
					if (c.getLig() == cos.getLig() && c.getCol() == cos.getCol())
						b.ajouterTouche(c);
				
				if (b.estCoule())
					for (Coordonnees cos : b.getCoordonnees())
						this.pltAttaques[cos.getLig()-1][cos.getCol() - 'A'] = '#';
			}

		}
		else
			this.pltAttaques[c.getLig()][c.getCol() - 'A'] = 'O';
		

		this.attaques.add(c);
		return true;
	}

	public void initialiserBateaux(int l1, int l2, int l3, int l4, int l5)
	{
		this.bateaux[0] = new Bateau(l1);
		this.bateaux[1] = new Bateau(l2);
		this.bateaux[2] = new Bateau(l3);
		this.bateaux[3] = new Bateau(l4);
		this.bateaux[4] = new Bateau(l5);
	}

	public boolean contientBateau(Coordonnees c)
	{
		if (c.getLig() > Plateau.TAILLE || c.getCol() > (char)('A' + Plateau.TAILLE)) return false;

		for (Bateau b : this.bateaux)
			if (b.estTouche(c)) return true;

		return false;
	}

	public ArrayList<Bateau> getNbBateauNonPlace()
	{
		ArrayList<Bateau> ensBateauNonPlace = new ArrayList<Bateau>();

		for (Bateau b : this.bateaux)
			if (b.getPosDebut().getLig() == 0)
				ensBateauNonPlace.add(b);

		return ensBateauNonPlace;
	}


	public boolean placerBateau(Coordonnees posDeb, Coordonnees posFin, int taille)
	{
		for ( Bateau b : this.bateaux )
			if ( b.getTaille() == taille && b.getPosDebut().getLig() == 0 && b.placerBateau ( posDeb, posFin, this.bateaux ))
			{
				for ( Coordonnees cos : b.getCoordonnees ( ) )
					this.pltBateaux[cos.getLig ( )-1][cos.getCol ( ) - 'A'] = 'B';

				return true;
			}

		return false;
	}

	public String afficherTableau(char[][] tab)
	{
		String sLigne =          "+----+---+---+---+---+---+---+---+---+---+---+";
		String sRet   = sLigne + "\n|    | A | B | C | D | E | F | G | H | I | J | "+"\n"+sLigne+"\n";
		
		for (int lig = 0 ; lig < tab.length ; lig++)
		{
			sRet += "| " + String.format( "%02d",lig+1) + " ";
			for (int col = 0 ; col < tab.length ; col++)
				sRet += "| " + tab[lig][col] + " ";

			sRet += "|\n" + sLigne +"\n";
		}

		return sRet;

	}

	public String toString()
	{
		return "Vos attaques :"                                 + "\n"    +
		       this.afficherTableau(this.pltAttaques)           + "\n"    +
		       "----------------------------------------------" + "\n\n"  +
			   "Vos bateaux"                                    + "\n"    +
		       this.afficherTableau(this.pltBateaux);
	}

	public void initialiserTableau(char[][] tab)
	{
		for (int lig = 0 ; lig < tab.length ; lig++)
			for (int col = 0 ; col < tab.length ; col++)
				tab[lig][col] = ' ';
	}

	public boolean partieTerminee ( )
	{
		boolean partieTerminee = true;

		for ( Bateau b : this.bateaux )
			if ( !b.estCoule() )
				partieTerminee = false;
		
		return partieTerminee;
	}
}