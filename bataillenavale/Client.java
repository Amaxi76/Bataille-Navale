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
			Socket toServer = new Socket ( "DESKTOP-LSMERC2", 9000 );

			System.out.println ( "connexion au serveur..." );

			PrintWriter    out = new PrintWriter    ( toServer.getOutputStream(), true );
			BufferedReader in  = new BufferedReader ( new InputStreamReader ( toServer.getInputStream() ) );

			System.out.println ( "connecté..." );
			
			// Bannière
			System.out.println ( in.readLine ( ) );

			boolean debut = false;
			while (!debut)
				if (in.readLine().equals("DEBUT")) debut = true;

			boolean tour = false;
			while (!tour)
			{
				String sRet = in.readLine();
				if (sRet.equals("TOUR")) tour = true;
				else                     System.out.println(sRet);
			}

			while ( debut && partieEnCours && tour)
			{
				try
				{
					Thread.sleep(200);
				}
				catch (Exception e) {}
				
				String sRet;
				while (in.ready())
				{
					sRet = in.readLine();

					System.out.println(sRet);

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
}