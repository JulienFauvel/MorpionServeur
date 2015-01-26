package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import util.ConfigFile;
import util.IDFactory;

/**
 * La classe Server permet à des clients de se connecter à lui, puis accepte le
 * client et créer un Thread pour chaque client.
 * 
 * Le serveur peut acceuillir un serveur de jeu ainsi qu'un maximum de quatre
 * clients.
 * 
 * @author Julien F.
 * @version 1.0, 04/12/2014
 */
public class Serveur {

	private ServerSocket ss;
	private IJeu jeu;
	
	private int nbClient;
	private int nbMaxClient;
	private int timer;
	private int timeOut;
	private boolean tourVariable;
	private boolean etatInitial;

	private ArrayList<Client> clients;
	private ArrayList<String> ids;
	
	private Client clientCourant;
	private String ordreTour;

	
	public Serveur(int port) {
		nbClient = 0;
		clients = new ArrayList<Client>();

		ConfigFile cfg = new ConfigFile();
		nbMaxClient = cfg.getNumberPlayer();
		timer = cfg.getTimer();
		tourVariable = cfg.isVariableTurn();
		etatInitial = cfg.isInitialState();
		ids = IDFactory.getIDs();
		
		if(ids.size()==0) {
			ids = IDFactory.genererIDs(nbMaxClient);
		}

		try {
			ss = new ServerSocket(port);
			ss.setSoTimeout(cfg.getTimeOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setJeu(IJeu jeu) {
		this.jeu = jeu;
	}

	/**
	 * Accepte la connexion des clients tant que l'on a pas atteint le nombre de
	 * joueur maximum.
	 */
	public void accepte() throws NullPointerException {
		
		if(jeu==null) throw new NullPointerException();
		
		while (nbClient < nbMaxClient) {
			try {
				Socket socket = ss.accept();
				ajouterClient(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
		if(etatInitial)
			envoieClients(jeu.getEtatInitial());
		if(tourVariable)
			jeu.getOrdreDuTour();
		
		
	}

	/**
	 * Ajoute un client
	 * 
	 * @return true si le client est ajouté
	 */
	private boolean ajouterClient(Socket s) {
		if (nbClient == nbMaxClient)
			return false;

		Client c = new Client(s);
		//Format = "idJoueur|numJoueur|nomJoueur"
		String str = c.recevoir();
		if (ids.contains(str)) {
			ids.remove(str);
			clients.add(c);
		} else {
			c.envoyer("Connexion refusée");
			c.fermer();
			return false;
		}
		
		if(nbClient==0)
			clientCourant = c;
		
		nbClient++;

		return true;
	}
	
	/**
	 * Envoie le message passé en paramètre à tous les client connectés.
	 * 
	 * @param message
	 *            Message à envoyer aux clients
	 */
	public void envoieClients(String message) {
		for (Client c : clients) {
			c.envoyer(message);
		}
	}
	
	public String recevoirClientCourant() {
		return clientCourant.recevoir();
	}
	
	/**
	 * Change le client courant
	 */
	public void changerJoueur() {
		int i=0;
		if(tourVariable) {
			int numJoueur = clients.indexOf(clientCourant);
			while(Character.digit(ordreTour.charAt(i), 10)==numJoueur) {
				i++;
			}
			if(i==ordreTour.length())
				i=0;
									
		} else {
			i = clients.indexOf(clientCourant)+1;
			if(i==clients.size())
				i=0;
		}
		clientCourant=clients.get(i);
		System.out.println("Changement Client n°"+(i+1));
	}
	
	/**
	 * 
	 * @return liste des IDs
	 */
	public ArrayList<String> getIDs() {
		return ids;
	}
	
	public boolean isEtatInitial() {
		return etatInitial;
	}

	public void setEtatInitial(boolean etatInitial) {
		this.etatInitial = etatInitial;
	}

	public int getNbClient() {
		return nbClient;
	}

	public int getNbMaxClient() {
		return nbMaxClient;
	}
	
	public String getJoueurCourant() {
		return clientCourant.getNom();
	}

	public int getTimer() {
		return timer;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public boolean isTourVariable() {
		return tourVariable;
	}

	public String getOrdreTour() {
		return ordreTour;
	}
}
