package bataillenavale;

import java.net.*;
import java.io.*;
import iut.algo.Clavier;

public class Client
{
	public static void main(String[] arg)
	{
		try
		{
			Socket toServer = new Socket ( "penguin", 9000 );

			System.out.println ( "connexion au serveur..." );

			PrintWriter    out = new PrintWriter    ( toServer.getOutputStream(), true );
			BufferedReader in  = new BufferedReader ( new InputStreamReader ( toServer.getInputStream() ) );

			System.out.println ( "connecté..." );

			// Banière
			System.out.println ( in.readLine ( ) );
			System.out.println ( in.readLine ( ) );

			String m1 = " ";
			String strServ;
			do
			{
				//System.out.println ( in.readLine() );
				strServ = in.readLine();
				if ( strServ.equals("true") )
				{
					m1 = Clavier.lireString();
					out.println(m1);
				}
			}
			while ( !m1.equals ( "" ) );
			
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