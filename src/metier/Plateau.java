package metier;

public class Plateau {

	private char[][] tab;

	private char joueurCourant;

	public Plateau() {
		tab = new char[3][3];

		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab[0].length; j++)
				tab[j][i] = ' ';

		joueurCourant = 'X';
	}

	public boolean jouer(int col, int lig) {
		if (col < 0 || col >= tab.length || lig < 0 || lig >= tab[0].length)
			return false;

		if (tab[col][lig] != ' ')
			return false;

		tab[col][lig] = joueurCourant;

		return true;
	}

	public boolean isRempli() {
		for (int i = 0; i < tab.length; i++)
			for (int j = 0; j < tab[0].length; j++)
				if (tab[j][i] == ' ')
					return false;
		return true;
	}

	public boolean aGagne() {
		for (int i = 0; i < tab[0].length; i++)
			if (tab[i][0] != ' ' && tab[i][0] == tab[i][1]
					&& tab[i][0] == tab[i][2])
				return true;

		for (int i = 0; i < tab[0].length; i++)
			if (tab[0][i] != ' ' && tab[0][i] == tab[1][i]
					&& tab[0][i] == tab[2][i])
				return true;

		if (tab[0][0] != ' ' && tab[0][0] == tab[1][1]
				&& tab[0][0] == tab[2][2])
			return true;

		if (tab[0][2] != ' ' && tab[0][2] == tab[1][1]
				&& tab[0][2] == tab[2][0])
			return true;

		return false;
	}

	public void changerJoueur() {
		joueurCourant = joueurCourant == 'O' ? 'X' : 'O';
	}

	public char getJoueurCourant() {
		return joueurCourant;
	}

	public String toString() {

		String delimiter = " +---+---+---+\n";

		StringBuilder sb = new StringBuilder();
		sb.append("   1   2   3\n");
		for (int i = 0; i < tab.length; i++) {
			sb.append(delimiter);
			sb.append(i + 1);
			for (int j = 0; j < tab[0].length; j++) {
				sb.append("| " + tab[j][i] + " ");
			}
			sb.append("|\n");
		}
		sb.append(delimiter+"\n");
		sb.append("Joueur courant : "+joueurCourant);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Plateau());
	}

}
