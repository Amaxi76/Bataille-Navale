

import java.net.*;
import java.io.*;
import iut.algo.Clavier;

public class Client
{
	public static void main(String[] arg)
	{
		/*try
		{
			String hostname = arg[0];*/
			boolean partieEnCours = true;
			boolean ecriture      = true;
			try
			{
				
				Socket toServer = new Socket ( "localhost" , 9000 );

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

				// Le client attend dès le début
				boolean attente = true;
				
				// Tant que la partie est en cours on lui demande ces actions
				while ( partieEnCours )
				{
					// tant qu'il est en attente, on lui demande d'attendre et de lire s'il recoit de ne plus attendre
					// sinon on affiche ce qu'on recoit de la part du serveur
					while ( attente )
					{
						//attendre();
						String sRet = in.readLine();
						if (sRet.equals("ATTENTE")) {
							attente = !attente; }
						else                        System.out.println(sRet);
					}

					attendre();
					
					String sRet;

					while ( in.ready() && !attente )
					{
						sRet = in.readLine();
						if (sRet.equals("ATTENTE")) attente = !attente;
						else                        System.out.println(sRet);
						if ( sRet.equals("La partie est terminée.")) partieEnCours = false;
					}

					if ( !attente && partieEnCours)
						out.println ( Clavier.lireString ( ) );
				}

				in.close();
				out.close();

				toServer.close();
			}
			catch (IOException ioe)
			{
				System.out.println("Erreur : Pas de serveur ouvert à ce nom");
			}

			System.out.println("Vous avez été déconnecté.");
		/*}
		catch (Exception e)
		{
			System.out.println("Mauvais format d'exécution.\nFormat demandé : java Client [nom du serveur]");
		}*/
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