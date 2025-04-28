package jeu;

import java.util.Comparator;

public class JoueurComparator implements Comparator<Joueur> {
	@Override
	public int compare(Joueur joueur1,Joueur joueur2) {
		return joueur1.donnerKmParcourus() - joueur2.donnerKmParcourus();
	}
}
