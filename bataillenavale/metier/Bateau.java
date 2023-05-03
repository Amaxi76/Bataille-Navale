package bataillenavale.metier;

import bataillenavale.metier.Coordonnees;
import java.util.ArrayList;

public class Bateau
{
	private Coordonnees posFin;
	private Coordonnees posDebut;

	private ArrayList<Coordonnees> coorTouche;

	private int longueur;

	public Bateau ( int longueur )
	{
		this.coorTouche = new ArrayList<>();

		this.posDebut = new Coordonnees ( 'A', 0 );
		this.posFin   = new Coordonnees ( 'A', 0 );

		this.longueur = longueur;
	}

	public int getTaille ( ) { return this.longueur; }

	public Coordonnees getPosDebut() { return this.posDebut; }

	public boolean placerBateau ( Coordonnees posDebut, Coordonnees posFin, Bateau[] ensBateaux )
	{
		//System.out.println("val : " + (Math.abs ( posDebut.getLig ( ) - posFin.getLig ( ) ) + Math.abs ( posDebut.getCol ( ) - posFin.getCol ( ) )));

		if ( Math.abs ( posDebut.getLig ( ) - posFin.getLig ( ) ) + Math.abs ( posDebut.getCol ( ) - posFin.getCol ( ) ) + 1 != this.longueur ) return false;

		this.posDebut = posDebut;
		this.posFin   = posFin;

		for ( Bateau b : ensBateaux )
			for ( Coordonnees coord : b.getCoordonnees() )
				for ( Coordonnees cos : this.getCoordonnees() )
					if (coord.getLig() == cos.getLig() && coord.getCol() == cos.getCol() && b != this)
					{
						System.out.println("test 1");
						this.posDebut = new Coordonnees ( 'A', 0 );
						this.posFin   = new Coordonnees ( 'A', 0 );
						return false;
					}
		
		return true;
	}

	public void ajouterTouche ( Coordonnees vise )
	{
		this.coorTouche.add ( vise );
	}
	
	public boolean estTouche(Coordonnees c)
	{
		for (Coordonnees cos : this.getCoordonnees())
			if (c.getLig() == cos.getLig() && c.getCol() == cos.getCol()) return true;
		return false;
	}

	public boolean estCoule ( )
	{
		return ( this.coorTouche.size() == this.longueur );
	}

	public Coordonnees[] getCoordonnees ()
	{
		Coordonnees[] tabCoordonnees = new Coordonnees[this.longueur];

		for ( int cpt = 0; cpt < tabCoordonnees.length; cpt++ )
		{
			if ( this.posDebut.getLig ( ) == this.posFin.getLig ( ) )
			{
				if ( this.posDebut.getCol ( ) < this.posFin.getCol ( ) )
					tabCoordonnees[cpt] = new Coordonnees( ( char ) ( this.posDebut.getCol ( ) + cpt ) , this.posDebut.getLig ( ) );
				else
					tabCoordonnees[cpt] = new Coordonnees( ( char ) ( this.posDebut.getCol ( ) - cpt ) , this.posDebut.getLig ( ) );
			}
			
			else 
			{
				if ( this.posDebut.getLig ( ) < this.posFin.getLig ( ) )
					tabCoordonnees[cpt] = new Coordonnees( this.posDebut.getCol ( ) , this.posDebut.getLig ( ) + cpt );
				else
					tabCoordonnees[cpt] = new Coordonnees( this.posDebut.getCol ( ) , this.posDebut.getLig ( ) - cpt );
			}
		}

		return tabCoordonnees;
	}

}