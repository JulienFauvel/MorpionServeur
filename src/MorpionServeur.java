import controlleur.Controlleur;

public class MorpionServeur {
	public static void main(String[] args) {
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("Le port doit être un nombre.");
		}

		if (port > 0) {
			new Controlleur(port);
		}
	}
}
