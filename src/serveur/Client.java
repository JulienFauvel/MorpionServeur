package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private String nom;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	/**
	 * Crée une instance d'un client.
	 * 
	 * @param socket
	 *            Socket d'un client
	 */
	public Client(Socket socket) {

		this.socket = socket;

		try {
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())));
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Envoie un message au client.
	 * 
	 * @param message
	 *            Message à envoyer au client
	 */
	public void envoyer(String message) {
		pw.println(message);
		pw.println("/finmessage/");
		pw.flush();
	}

	/**
	 * Retourne un message envoyé par le client.
	 * 
	 * @return message du client
	 */
	public String recevoir() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void fermer() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}
