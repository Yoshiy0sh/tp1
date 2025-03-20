package jeu;

import java.util.ArrayList;

import cartes.Carte;

public class MainJoueur {
	private ArrayList<Carte> main = new ArrayList<>();
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert(main.contains(carte));
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("La main de ce joueur contient :\n");
		
		for (Carte carte : main) {
			chaine.append(carte.toString()+'\n');
		}
		return chaine.toString();
	}
}
