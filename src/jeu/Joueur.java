package jeu;

import java.security.KeyRep.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Botte;
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
			Carte carte = sabot.piocher();
			mainJoueur.prendre(carte);
			return carte;
		}
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.jouer(carte);
	}
	
	private Coup choisirCoupAleatoire(Set<Coup> set) {
		if(set.isEmpty()) {
			return null;
		}
		else {
			ArrayList<Coup> list = new ArrayList<>(set);
			int index = new Random().nextInt(set.size());
			return list.get(index);
		}
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Coup coup = new Coup(this,null,null);
		Set<Coup> tousLesCoups = coup.coupsPossibles(participants);
		Set<Coup> coupsValides = new HashSet<>();
		
		//vérification de si le coup est autorisé
		for (Coup coupCourant : tousLesCoups) {
			if(coupCourant.getCible().zoneDeJeu.estDepotAutorise(coupCourant.getCarteJouee())) {
				coupsValides.add(coupCourant);
			}
		}
		
		if(!coupsValides.isEmpty()) {
			return choisirCoupAleatoire(coupsValides);
		}
		else {
			return choisirCoupAleatoire(coup.coupsDefausse(mainJoueur.getMain()));
		}
	}
	
	public String afficherEtatJoueur() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le joueur courant est " + nom + "\n");
		
		Set<Botte> bottes = zoneDeJeu.getBottes();
		for(Botte botte : bottes) {
			chaine.append(botte.toString());
		}
		if(zoneDeJeu.donnerLimitationVitesse() == 200) {
			chaine.append("Limitation de vitesse : false\n");
		}
		else {
			chaine.append("Limitation de vitesse : true\n");
		}
		List<Carte> bataille = zoneDeJeu.getListeBataille();
		if(bataille.isEmpty()) {
			chaine.append("Sommet pile de bataille : null\n");
		}
		else {
			chaine.append(bataille.get(bataille.size() -1).toString());
		}
		List<Carte> main = mainJoueur.getMain();
		chaine.append("Contenu de la main : [");
		for (Carte carte : main) {
			chaine.append(carte.toString() + ";");
		}
		chaine.append("]\n");
		return chaine.toString();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}
	
	public List<Carte> donnerCartesMain() {
		return mainJoueur.getMain();
	}
}
