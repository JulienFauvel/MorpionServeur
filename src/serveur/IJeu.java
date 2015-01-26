package serveur;

public interface IJeu {

	/**
	 * Permet de faire avancer le jeu en envoyant le coup du joueur courant.
	 * 
	 * @param action
	 *            Action du joueur
	 */
	public String action(String action);

	/**
	 * Retourne l'état du jeu initial.
	 * 
	 * @return l'état du jeu
	 */
	public String getEtatInitial();

	/**
	 * 
	 * @return le classement des joueurs
	 */
	public String classement();

	/**
	 * 
	 */
	public String getOrdreDuTour();
}
