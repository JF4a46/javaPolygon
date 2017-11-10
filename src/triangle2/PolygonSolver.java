package triangle2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import triangle2.Outils;
import triangle2.PolygonSolver;

public class PolygonSolver {

	Outils outils;
	int maxSolutions;
	int maxDiagonales;
	HashSet<String> solFound = new HashSet<String>();
	ArrayList<String> solutions = new ArrayList<String>();

	public PolygonSolver(String polygone, Outils outils) {
		this.outils = outils;
		maxSolutions = outils.numberCatalan(polygone);
		maxDiagonales = outils.maxDiagonales(polygone);
		ArrayList<String> restrictions = new ArrayList<String>();
		ArrayList<String> diagonales = outils.findDiagonales(polygone, restrictions);
		for (int i = 0; i < diagonales.size(); i++) {
			// System.out.println(polygone + " " + diagonales.get(2) + " first");
			recursiveSolve(polygone, diagonales.get(i), diagonales.get(i), restrictions);
			restrictions.add(diagonales.get(i));
		}
		System.out.println(maxSolutions);
		System.out.println(solutions.size());

		// System.out.println(maxSolutions);
		// System.out.println(solutions.size());
		// System.out.println(solutions.toString());
		// recursiveSolve("PQRSTUVWX", "PR", "PR");
		// outils.removeTriangle("ABCDEF", "EA");
		// System.out.println(sort("BC DE AB "));
	}

	private void recursiveSolve(String polygone, String separation, String solution, ArrayList<String> restrictions) {

		polygone = outils.removeTriangle(polygone, separation);
		if (polygone.length() == 3) {
			// System.out.println(solution);
			solutions.add(sort(solution));
			return;
		}
		ArrayList<String> newRestrictions = (ArrayList<String>) restrictions.clone();
		ArrayList<String> diagonales = outils.findDiagonales(polygone, restrictions);
		for (int i = 0; i < diagonales.size(); i++) {
			// System.out.println(polygone + " " + diagonales.get(i) + " second");
			recursiveSolve(polygone, diagonales.get(i), solution + " " + diagonales.get(i), newRestrictions);
			newRestrictions.add(diagonales.get(i));
		}

	}

	public String sort(String notSorted) {
		String[] temp = notSorted.split(" ");
		String retour = "";
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {
			retour += temp[i] + " ";
		}

		if (solutions.contains(retour))
			System.out.println(retour + " idem");

		System.out.println(retour);
		return "";
	}

	public static void main(String[] args) {
		Outils outils = new Outils();
		ArrayList<String> cases = outils.readFile();

		// PolygonSolver pol = new PolygonSolver(cases.get(0), outils);
		// PolygonSolver pol1 = new PolygonSolver(cases.get(1), outils);
		PolygonSolver pol2 = new PolygonSolver(cases.get(2), outils);
	}

}
