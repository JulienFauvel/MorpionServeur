package ihm;

import java.util.Scanner;

public class IHM {

	private Scanner sc;

	public IHM() {
		sc = new Scanner(System.in);
	}

	public int[] saisirCoord() {
		int[] coord = new int[2];

		System.out.print(Message.SAISIE_LIG);
		coord[0] = sc.nextInt();

		while (coord[0] < 1 || coord[0] > 3) {
			System.out.print(Message.ERREUR_COL);
			coord[0] = sc.nextInt();
		}

		System.out.print(Message.SAISIE_LIG);
		coord[1] = sc.nextInt();

		while (coord[1] < 1 || coord[1] > 3) {
			System.out.print(Message.ERREUR_LIG);
			coord[0] = sc.nextInt();
		}

		coord[0]--;
		coord[1]--;

		return coord;
	}

	public void afficher(String aff) {
		System.out.println(aff);
	}

}
