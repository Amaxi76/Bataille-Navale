// Problème avec le false droit ou pas 
package bataillenavale;

import java.net.*;

import bataillenavale.metier.*;
import bataillenavale.metier.Coordonnees;
import java.io.*;
import java.util.*;

public class Serveur
{
	private static Jeu jeu;
	public static void main(String[] arg)
	{
		try
		{
			ServerSocket ss       = new ServerSocket ( 9000 );
			
			System.out.println ( "En attente des clients" );

			// Arrivée du 1er joueur
			Socket       clientUn   = ss.accept();
			System.out.println ("Client 1 accepté");

			PrintWriter    outUn   = new PrintWriter    ( clientUn  .getOutputStream(), true                );
			BufferedReader inUn    = new BufferedReader ( new InputStreamReader ( clientUn.  getInputStream() ) );

			// Bannière joueur 1
			outUn     .println ( "Connecté en tant que joueur 1. En attente du second joueur..." );
			System.out.println ( "Joueur 1 arrivé" );

			// Arrivée du 2eme joueur
			Socket       clientDeux = ss.accept();
			System.out.println("Client 2 accepté");

			PrintWriter    outDeux = new PrintWriter    ( clientDeux.getOutputStream(), true                );
			BufferedReader inDeux  = new BufferedReader ( new InputStreamReader ( clientDeux.getInputStream() ) );
			
			// Bannière joueur 2
			outDeux   .println ( "Connecté en tant que joueur 2." );
			System.out.println ( "Joueur 2 arrivé" );

			// Début de partie
			outUn  .println("ATTENTE");
			outDeux.println("ATTENTE");
			outUn  .println("Adversaire trouvé. La partie commence.");
			outDeux.println("Adversaire trouvé. La partie commence.");
			Serveur.jeu = new Jeu();

			System.out.println ("test 1 : debut du jeu");

			// Le joueur 1 place ses bateaux
			outDeux.println("Le joueur 1 place ses bateaux...");
			outDeux.println("ATTENTE");
			placerBateau(1, inUn  , outUn  );
			outDeux.println("ATTENTE");
			
			// Le joueur 2 place ses bateaux
			outUn  .println("Le joueur 2 place ses bateaux...");
			outUn.  println("ATTENTE");
			placerBateau(2, inDeux, outDeux);
			outUn.  println("ATTENTE");

			outUn  .println("Fin de la phase de préparation. Début de la phase d'attaque...");
			outDeux.println("Fin de la phase de préparation. Début de la phase d'attaque...");

			boolean partieFinie = Serveur.jeu.partieTerminee ( );
			
			while ( !partieFinie )
			{
				outDeux.println("ATTENTE");
				attaquer ( 1, inUn, outUn );
				outDeux.println("ATTENTE");

				if ( !Serveur.jeu.partieTerminee ( ) )
				{
					outUn.println("ATTENTE");
					attaquer ( 2, inDeux, outDeux );
					outUn.println("ATTENTE");
				}
					

				partieFinie = Serveur.jeu.partieTerminee ( );
			}
			
			if ( Serveur.jeu.partieTerminee ( ) )
			{
				inUn .close();
				outUn.close();

				inDeux. close();
				outDeux.close();

				clientUn.  close();
				clientDeux.close();
			}

		} 
		catch (IOException ioe)
		{
			System.out.println("Erreur");
		}

	}

	public static void placerBateau ( int joueur, BufferedReader in, PrintWriter out )
	{
		String posDep, posArr;
		posDep = posArr = "   ";
		try
		{
			for ( Bateau b : Serveur.jeu.getNbBateauNonPlace ( joueur ) )
			{
				do
				{
					out.println ( Serveur.jeu.toString ( joueur ) );
					if (!(posDep.equals("   ") || posArr.equals("   ")))
						out.println ( "Vous avez créé un bateau de " + posDep + " à " + posArr );
					do
					{
						out.println ( "Entrez les coordonnées de départ du bateau de taille " + b.getTaille ( ) );
						do
						{
							posDep = in.readLine ( );
							System.out.println(posDep + !in.ready());
						} while (in.ready());
						
						if ( posDep.length ( ) != 3 ) out.println ( "Erreur de format. Exemples : A02, E04, H10" );
					} while ( posDep != null && posDep.length ( ) != 3 || ! ( ( posDep.charAt ( 0 ) >= 'A' && posDep.charAt ( 0 ) <= 'J' ) && ( posDep.charAt ( 1 ) == '0' || posDep.charAt ( 1 ) == '1' ) && ( posDep.charAt ( 2 ) >= '0' && posDep.charAt ( 2 ) <= '9' ) ) );

					do
					{
						out.println ( "Entrez les coordonnées finales du bateau de taille " + b.getTaille ( ) + " commençant en " + posDep );
						posArr = in.readLine();
						if ( posArr.length ( ) != 3) out.println ( "Erreur de format. Exemples : A02, E04, H10" );
					} while ( posDep != null && posArr.length ( ) != 3 || !( ( posArr.charAt ( 0 ) >= 'A' && posArr.charAt ( 0 ) <= 'J' ) && ( posArr.charAt ( 1 ) == '0' || posArr.charAt ( 1 ) == '1' ) && ( posArr.charAt ( 2 ) >= '0' && posArr.charAt ( 2 ) <= '9' ) ) && posDep != posArr );

					System.out.println("Joueur " + joueur + " créé un bateau de taille " + b.getTaille() + " de " + new Coordonnees ( posDep.charAt(0), Integer.parseInt("" + posDep.charAt(1) + posDep.charAt(2)) ) + new Coordonnees ( posArr.charAt(0), Integer.parseInt("" + posArr.charAt(1) + posArr.charAt(2)) ));
				} while (!Serveur.jeu.placerBateau ( joueur, new Coordonnees ( posDep.charAt(0), Integer.parseInt("" + posDep.charAt(1) + posDep.charAt(2)) ) , new Coordonnees ( posArr.charAt(0), Integer.parseInt("" + posArr.charAt(1) + posArr.charAt(2)) ), b.getTaille()));
			}
			out.println ( Serveur.jeu.toString ( joueur ) );

		}
		catch ( IOException e ) { System.out.println ( "Erreur :\n" + e ); }
	}

	public static void attaquer(int joueur,  BufferedReader in, PrintWriter out)
	{
		//Linux on peut écrire même si on a pas la main alors que sur windowns on ne peut pas du tout écrire
		String pos;
		pos = "";
		int resAtk;
		try
		{
			do
			{
				do
				{
					out.println(Serveur.jeu.toString(joueur));
					out.println("Entrez les coordonnées de votre attaque.");
					pos = in.readLine();
					if ( pos.length ( ) != 3) out.println ( "Erreur de format. Exemples : A02, E04, H10" );
				} while ( pos != null && pos.length ( ) != 3);
				
				resAtk = Serveur.jeu.attaquer(joueur, new Coordonnees ( pos.charAt(0), Integer.parseInt("" + pos.charAt(1) + pos.charAt(2)) ));
				
				out.println(Serveur.jeu.toString(joueur));
				if (resAtk == 2)
					out.println("Touché !");
				else
					if (resAtk == 1)
						out.println("Plouf.");

			}
			while ( resAtk == 0 || resAtk == 2);
		}
		catch ( IOException e ) { System.out.println ( "Erreur :\n" + e ); }
	}
}