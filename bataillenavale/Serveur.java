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
			
			while ( true )
			{
				System.out.println ( "En attente des clients" );
				Socket       clientUn   = ss.accept();
				System.out.println ("Client 1 accepté");
	
				PrintWriter    outUn   = new PrintWriter    ( clientUn  .getOutputStream(), true                );
				BufferedReader inUn    = new BufferedReader ( new InputStreamReader ( clientUn.  getInputStream() ) );

				// Bannière joueur 1
				outUn     .println ( "Connecté en tant que joueur 1. En attente du second joueur..." );
				System.out.println ( "Joueur 1 arrivé" );

				Socket       clientDeux = ss.accept();
				System.out.println("Client 2 accepté");

				PrintWriter    outDeux = new PrintWriter    ( clientDeux.getOutputStream(), true                );
				BufferedReader inDeux  = new BufferedReader ( new InputStreamReader ( clientDeux.getInputStream() ) );
				
				// Bannière joueur 2
				outDeux   .println ( "Connecté en tant que joueur 2." );
				System.out.println ( "Joueur 2 arrivé" );

				// Début de partie
				outUn  .println("Adversaire trouvé. La partie commence.");
				outDeux.println("Adversaire trouvé. La partie commence.");
				Serveur.jeu = new Jeu();

				System.out.println ("test 1 : debut du jeu");

				placerBateau(1, inUn  , outUn  );
				placerBateau(2, inDeux, outDeux);
				
				String messageUn, messageDeux;
				messageUn = messageDeux = "";
				do
				{
					outUn  .println("true");
					messageUn = inUn.readLine();
					System.out.println("Le client a dit : " + messageUn);
					outUn  .println("false");

					outDeux.println("true");
					messageDeux = inDeux.readLine();
					System.out.println("The client said : " + messageDeux);
					outDeux.println("false");
				}
				while (messageUn != null && !messageUn.equals("") && messageDeux != null && !messageDeux.equals("") );

				inUn.close();
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

	public static void placerBateau(int joueur, BufferedReader in, PrintWriter out)
	{
		String posDep, posArr;
		posDep = posArr = "   ";

		ArrayList<Bateau> alBateaux = Serveur.jeu.getNbBateauNonPlace ( joueur );
		try
		{
			for ( Bateau b : alBateaux )
			{
				System.out.println("test");
				do
				{
					do
					{
						System.out.println("Taille de la liste : " + Serveur.jeu.getNbBateauNonPlace(joueur).size());
						for (Bateau bat : Serveur.jeu.getNbBateauNonPlace(joueur)) System.out.println("Taille du bateau : " + bat.getTaille());
						System.out.println("On veut un bateau de taille : " + b.getTaille());

						out.println ( Serveur.jeu.toString ( joueur ) );
						out.println ( "Rentrer les coordonnées de départ du bateau de taille " + b.getTaille ( ) );
						posDep = in.readLine ( );
						System.out.println("Entrée : " + posDep);
						if ( posDep.length ( ) != 3 ) out.println ( "Erreur de format. Exemples : A02, E04, H10" );
					} while ( posDep != null && posDep.length ( ) != 3 || ! ( ( posDep.charAt ( 0 ) >= 'A' && posDep.charAt ( 0 ) <= 'J' ) && ( posDep.charAt ( 1 ) == '0' || posDep.charAt ( 1 ) == '1' ) && ( posDep.charAt ( 2 ) >= '0' && posDep.charAt ( 2 ) <= '9' ) ) );

					do
					{
						out.println("Rentrer les coordonnées finales du bateau de taille " + b.getTaille() + " commençant en " + posDep );
						posArr = in.readLine();
						System.out.println("Entrée2 : " + posArr);
						if (posArr.length() != 3) out.println("Erreur de format. Exemples : A02, E04, H10");
					} while (posDep != null && posArr.length() != 3 || !((posArr.charAt(0) >= 'A' && posArr.charAt(0) <= 'J') && (posArr.charAt(1) == '0' || posArr.charAt(1) == '1') && (posArr.charAt(2) >= '0' && posArr.charAt(2) <= '9')) && posDep != posArr);

					System.out.println("Joueur " + joueur + " créé un bateau de taille " + b.getTaille() + " de " + new Coordonnees ( posDep.charAt(0), Integer.parseInt("" + posDep.charAt(1) + posDep.charAt(2)) ) + new Coordonnees ( posArr.charAt(0), Integer.parseInt("" + posArr.charAt(1) + posArr.charAt(2)) ));
					
					System.out.println("SORT");
				} while (!Serveur.jeu.placerBateau ( joueur, new Coordonnees ( posDep.charAt(0), Integer.parseInt("" + posDep.charAt(1) + posDep.charAt(2)) ) , new Coordonnees ( posArr.charAt(0), Integer.parseInt("" + posArr.charAt(1) + posArr.charAt(2)) ), b.getTaille()));
				alBateaux = Serveur.jeu.getNbBateauNonPlace(joueur);
				System.out.println("Vous avez créé un bateau de " + posDep + " à " + posArr);
				out.println(Serveur.jeu.toString(joueur));
			}
		}
		catch ( IOException e ) { System.out.println("Erreur"); }
	}
}