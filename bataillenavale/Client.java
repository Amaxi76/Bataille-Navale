package bataillenavale;

import java.net.*;
import java.io.*;
import iut.algo.Clavier;

public class Client
{
	public static void main(String[] arg)
	{
		boolean partieEnCours = true;
		boolean ecriture      = true;
		try
		{
			Socket toServer = new Socket ( "penguin", 9000 );

			System.out.println ( "connexion au serveur..." );

			PrintWriter    out = new PrintWriter    ( toServer.getOutputStream(), true );
			BufferedReader in  = new BufferedReader ( new InputStreamReader ( toServer.getInputStream() ) );

			System.out.println ( "connecté..." );
			
			// Bannière
			System.out.println ( in.readLine ( ) );

			/*boolean debut = false;
			while (!debut)
				if (in.readLine().equals("DEBUT")) debut = true;

			boolean tour = false;
			while (!tour)
			{
				String sRet = in.readLine();
				if (sRet.equals("TOUR")) tour = true;
				else                     System.out.println(sRet);
			}*/

			boolean attente = true;
			

			while ( partieEnCours)
			{
				while (attente)
				{
					attendre();
					System.out.println("coucou max");
					String sRet = in.readLine();
					if (sRet.equals("ATTENTE")) {
						attente = !attente; }
					else                        System.out.println(sRet);
				}

				
				attendre();
				
				String sRet;
				while ( in.ready() || attente)
				{
					sRet = in.readLine();

					if (sRet.equals("ATTENTE")) attente = !attente;
					else                        System.out.println(sRet);
					if ( sRet.equals("La partie est terminée.")) partieEnCours = false;
				}

				out.println ( Clavier.lireString ( ) );
			}

			in.close();
			out.close();

			toServer.close();
		}

		catch (IOException ioe)
		{
			System.out.println("Erreur : \n" + ioe);
		}
	}

	public static void attendre()
	{
		try
		{
			Thread.sleep(200);
		}
		catch (Exception e) {}
	}
}