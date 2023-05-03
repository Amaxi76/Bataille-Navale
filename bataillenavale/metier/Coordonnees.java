package bataillenavale.metier;

public class Coordonnees
{
	public char col;
	public int  lig;

	public Coordonnees ( char col, int lig )
	{
		this.col = col;
		this.lig = lig;
	}

	public int  getLig ( ) { return this.lig; }
	public char getCol ( ) { return this.col; }
	
	public String toString ( )
	{
		return this.col + "," + this.lig;
	}
}
