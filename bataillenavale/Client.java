package bataillenavale;

import java.net.*;
import java.io.*;
import iut.algo.Clavier;

public class Client
{
	public static void main(String[] arg)
	{
		boolean partieEnCours = true;
		boolean ecriture      = false;
		try
		{
			Socket toServer = new Socket ( "penguin", 9000 );

			System.out.println ( "connexion au serveur..." );

			PrintWriter    out = new PrintWriter    ( toServer.getOutputStream(), true );
			BufferedReader in  = new BufferedReader ( new InputStreamReader ( toServer.getInputStream() ) );

			System.out.println ( "connecté..." );

			// Bannière
			System.out.println ( in.readLine ( ) );
			System.out.println ( in.readLine ( ) );

			System.out.println ( in.readLine ( ) );

			String m1 = " ";
			String strServ;
			do
			{
				String sRet;
				while (in.ready())
				{
					sRet = in.readLine();
					if ( sRet.equals("true" )) ecriture = true;
					else
					{
						if ( sRet.equals("false")) ecriture = false;
						else                       System.out.println(sRet);
					}

					if ( sRet.equals("La partie est terminée.")) partieEnCours = false;
				}
				if ( ecriture )
				{
					System.out.println("test écriture");
					m1 = Clavier.lireString();
					out.println(m1);
				}
			}
			while ( partieEnCours );
			
			in.close();
			out.close();

			toServer.close();
		}

		catch (IOException ioe)
		{
			System.out.println("Erreur");
		}
	}
}