package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;

public class Coup {
	private Joueur current;
	private Carte carteJouee;
	private Joueur cible;
	
	public Coup(Joueur current,Carte carteJouee,Joueur cible) {
		this.current = current;
		this.carteJouee = carteJouee;
		this.cible = cible;
	}
	
	public Carte getCarteJouee() {
		return carteJouee;
	}
	
	public Joueur getCible() {
		return cible;
	}
	
	public Joueur getCurrent() {
		return current;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (current != null ? current.hashCode() : 0);
		result = 31* result + (carteJouee != null ? carteJouee.hashCode() : 0);
		result = 31* result + (cible != null ? cible.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean egalite = false;
		if(obj instanceof Coup coup) {
			egalite = this.current.equals(coup.current) && this.carteJouee.equals(coup.carteJouee);
			if(cible == null) {
				egalite &= coup.cible == null;
			}
			else {
				egalite &= cible.equals(coup.cible);
			}
		}
		return egalite;
	}
	
	public boolean estValide() {
		if((carteJouee instanceof DebutLimite) || (carteJouee instanceof Attaque)) {
			return cible != null;
		}
		else {
			return cible == current;
		}
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		//toutes les cartes qu'il est possible de jouer contre qqn
		//donc toutes les attaques ou limites
		List<Carte> mainJoueurCourant = current.donnerCartesMain();
		
		Set<Joueur> setParticipants = new HashSet<>(participants);
		
		HashSet<Coup> setCoupsValides = new HashSet<>();
		for (Joueur joueur : setParticipants) {
			for (Carte carte : mainJoueurCourant) {
				Coup nouv = new Coup(current,carte,joueur);
				if(nouv.estValide()) {
					setCoupsValides.add(nouv);
				}
			}
		}
		return setCoupsValides;
	}
	
	public Set<Coup> coupsDefausse(List<Carte> main) {
		HashSet<Coup> set = new HashSet<>();
		for (Carte carte : main) {
			set.add(new Coup(current, carte, null));
		}
		return set;
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		if(cible != null) {
			chaine.append("depose la carte " + carteJouee.toString() + " dans la zone de "
					+ cible.getNom());
		}
		else {
			chaine.append("defausse la carte " + carteJouee.toString());
		}
		return chaine.toString();
	}
	
}
