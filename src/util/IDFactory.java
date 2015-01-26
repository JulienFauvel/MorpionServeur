package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public abstract class IDFactory {

	public static String genererID() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	public static ArrayList<String> getIDs() {
		ArrayList<String> al = new ArrayList<String>();
		String path = System.getProperty("user.dir") + "/cfg/keys";
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				String key = sc.nextLine();
				al.add(key);
			}
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<String> genererIDs(int numberOfKey) {
		ArrayList<String> al = new ArrayList<String>();
		String path = System.getProperty("user.dir") + "/cfg/keys";
		try {
			FileWriter fw = new FileWriter(new File(path));
			for (int i = 0; i < numberOfKey; i++) {
				String key = genererID() + "|" + (i + 1);
				al.add(key);
				fw.write(key + "\n");
			}
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return al;
	}
}
