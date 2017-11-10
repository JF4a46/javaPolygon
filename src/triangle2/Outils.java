package triangle2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Outils {

	public ArrayList<String> findDiagonales(String polygone, ArrayList<String> restrictions) {
		ArrayList<String> diagonales = new ArrayList<String>();
		if (polygone.length() > 3) {
			for (int i = 0; i < polygone.length(); i++) {
				String data = lexicoGraphOrder(polygone.charAt(i), polygone.charAt(next(i, polygone.length(), 2)));
				if (!diagonales.contains(data) && !restrictions.contains(data))
					diagonales.add(data);
			}
		}
		return diagonales;

	}

	public String removeTriangle(String polygone, String separation) {
		String polyA = "";
		String polyB = "";
		for (int i = polygone.indexOf(separation.charAt(0));; i = next(i, polygone.length(), 1)) {
			polyA += polygone.charAt(i);
			if (polygone.charAt(i) == separation.charAt(1))
				break;
		}
		for (int i = polygone.indexOf(separation.charAt(1));; i = next(i, polygone.length(), 1)) {
			polyB += polygone.charAt(i);
			if (polygone.charAt(i) == separation.charAt(0))
				break;
		}
		// System.out.println(polyA + " " + polyB);
		// System.out.println(polygone + " " + newPoly + " " + separation);
		if (polyA.length() < polyB.length())
			return polyB;
		else
			return polyA;
	}

	/*
	 * boolean record = true; int compteur = 0; int pointer =
	 * polygone.indexOf(separation.charAt(0)); while (compteur < polygone.length())
	 * { // System.out.println(pointer); if (polygone.charAt(pointer) ==
	 * separation.charAt(1)) record = true; if (record) newPoly +=
	 * polygone.charAt(pointer); if (polygone.charAt(pointer) ==
	 * separation.charAt(0)) record = false; pointer = next(pointer,
	 * polygone.length(), 1); compteur++;
	 * 
	 * }
	 */
	private String lexicoGraphOrder(char car, char car2) {

		if (car < car2) {
			return "" + car + car2;
		} else
			return "" + car2 + car;
	}

	private int next(int j, int length, int step) {
		j += step;
		if (j >= length)
			j = j - length;

		return j;
	}

	private int before(int j, int length) {
		j--;
		if (j <= -1)
			j = length - 1;
		return j;
	}

	public ArrayList<String> readFile() {
		ArrayList<String> lineArr = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader("data/data.txt"))) {
			String line = br.readLine();
			int longeur = Integer.parseInt(line);
			for (int i = 0; i < longeur; i++) {
				lineArr.add(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Erreur Input");
		}

		return lineArr;

	}

	public int numberCatalan(String polygone) {
		int n = polygone.length();
		n = n - 2;
		int[] catalan = new int[n + 1];
		catalan[0] = catalan[1] = 1;
		/*
		 * Formule par recurrence Pour k allant de 0 a n-1 C[k] * C[n-k-1]
		 */
		int compteur = 2;
		while (compteur <= n) {
			int second = 0;
			while (second < compteur) {
				catalan[compteur] += catalan[second] * catalan[compteur - second - 1];
				second++;
			}
			compteur++;
		}

		return catalan[n];
	}

	public int maxDiagonales(String polygone) {

		return polygone.length() - 3;
	}

}
