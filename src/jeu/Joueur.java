package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
	private MainJoueur mainJoueur = new MainJoueur();

	public Joueur(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return this.nom.equals(joueur.nom);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * (nom.hashCode());
	}

	@Override
	public String toString() {
		return "Le nom de ce joueur est " + nom;
	}
	
	public void donnerMain(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) {
			return null;
		}
		else {
			return sabot.piocher();
		}
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
}
