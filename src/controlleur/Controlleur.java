package controlleur;

import ihm.IHM;
import metier.Plateau;
import serveur.IJeu;
import serveur.Serveur;

public class Controlleur implements IJeu {

	private Plateau plateau;
	private IHM ihm;
	private Serveur serv;

	public Controlleur(int port) {
		plateau = new Plateau();
		ihm = new IHM();
		serv = new Serveur(port);
		serv.setJeu(this);
		jouer();
	}

	public void jouer() {

		serv.accepte();
		ihm.afficher(plateau.toString());
		
		while (!plateau.aGagne() && !plateau.isRempli()) {
			
			String isValide = action(serv.recevoirClientCourant());
			while(!(isValide.equals("valide") || isValide.equals("fin"))) {
				isValide = action(serv.recevoirClientCourant());
			}
			ihm.afficher(plateau.toString());
		}

		if (plateau.aGagne()) {
			ihm.afficher(classement());
			serv.envoieClients(classement());			
		} else {
			ihm.afficher("Egalité !");
			serv.envoieClients("Egalité !");
		}
	}

	@Override
	public String action(String action) {
		String coor = action.split("\\|")[2];
		String[] coord = coor.split(",");
		boolean valide = plateau.jouer(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
		
		if (plateau.aGagne() || plateau.isRempli()) {
			serv.envoieClients("fin");
			serv.envoieClients(coor);
			return "fin";
		}
		
		if (valide) {
			serv.envoieClients("valide");
			serv.envoieClients(coor);
			plateau.changerJoueur();
			serv.changerJoueur();
			return "valide";
		} else {
			serv.envoieClients("invalide rejoue");
			return "invalide rejoue";
		}
	}

	@Override
	public String getEtatInitial() {
		return plateau.toString();
	}

	@Override
	public String classement() {
		return "Le joueur " + plateau.getJoueurCourant() + " a gagné !";
	}

	@Override
	public String getOrdreDuTour() {
		return "12";
	}
}
