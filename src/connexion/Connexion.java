package connexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Connexion {

	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	/**
	 * Crée une instance d'une Connection.
	 * 
	 * @param adr
	 *            Adresse du serveur
	 */
	public Connexion(String adr, int port) {
		try {
			this.socket = new Socket(adr, port);
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())));
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Envoie un message au serveur.
	 * 
	 * @param message
	 *            Message à envoyer au serveur
	 */
	public void envoyer(String message) {
		pw.println(message);
		pw.flush();
	}

	/**
	 * Retourne un message envoyé par le serveur.
	 * 
	 * @return message du client
	 */
	public String recevoir() {
		try {
			return br.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
