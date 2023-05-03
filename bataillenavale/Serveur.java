package bataillenavale;

import java.net.*;

import java.io.*;

public class Serveur
{
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
}