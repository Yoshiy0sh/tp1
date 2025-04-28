package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Probleme;
import cartes.Type;

public class ZoneDeJeu {
	private HashSet<Botte> bottes = new HashSet<>();
	private ArrayList<Carte> listeLimites = new ArrayList<>();
	private ArrayList<Carte> listeBataille = new ArrayList<>();
	private ArrayList<Carte> listeBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(listeLimites.isEmpty()) {
			return 200;
		}
		Carte last = listeLimites.get(listeLimites.size()-1);
		if(last.equals(new FinLimite()) || estPrioritaire()) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int somme = 0;
		for (Carte carte : listeBornes) {
			Borne borne = (Borne)carte;
			somme += borne.getKm();
		}
		return somme;
	}
	
	public void deposer(Carte carte) {
		if(carte instanceof Borne borne) {
			listeBornes.add(borne);
		}
		else if(carte instanceof Limite limite) {
			listeLimites.add(limite);
		}
		else if(carte instanceof Bataille bataille) {
			listeBataille.add(bataille);
		}
		else if(carte instanceof Botte botte) {
			bottes.add(botte);
		}
	}
	
	public boolean possedeBotte(Probleme carte) {
		return bottes.contains(new Botte(carte.getType()));
	}
	
	public boolean peutAvancer() {
		if(listeBataille.isEmpty()) {
			return estPrioritaire();
		}
		else {
			Carte last = listeBataille.get(listeBataille.size()-1);
			return ((last.equals(Cartes.FEU_VERT)) || ((last instanceof Parade) && estPrioritaire()) 
					|| ((last.equals(Cartes.FEU_ROUGE)) && estPrioritaire()) || (possedeBotte((Probleme)last) && estPrioritaire()));
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		if(estPrioritaire()) {
			return false;
		}
		if(listeBataille.isEmpty()) {
			return true;
		}
		Carte last = listeBataille.get(listeBataille.size()-1);
		return last.equals(new Attaque(Type.FEU))
				|| ((last instanceof Parade) && (!last.equals(new Parade(Type.FEU))))
				|| possedeBotte((Probleme)last);
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		int limitation = donnerLimitationVitesse();
		int kmBorne = borne.getKm();
		return peutAvancer() && (kmBorne <= limitation) && (donnerKmParcourus() + kmBorne <= 1000);
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(estPrioritaire()) {
			return false;
		}
		if(limite instanceof DebutLimite) {
			if(listeLimites.isEmpty()) {
				return true;
			}
			return (listeLimites.get(listeLimites.size()-1) instanceof FinLimite);
		}
		else {
			return !listeLimites.isEmpty() && (listeLimites.get(listeLimites.size()-1) instanceof DebutLimite);
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(possedeBotte(bataille)) {
			return false;
		}
		if(bataille instanceof Attaque) {
			return peutAvancer();
		}
		else if(bataille instanceof Parade) {
			if(bataille.equals(new Parade(Type.FEU))) {
				if(listeBataille.isEmpty()) {
					return true;
				}
				Carte last = listeBataille.get(listeBataille.size()-1);
				return last.equals(new Attaque(Type.FEU)) 
						|| ((last instanceof Parade) && !last.equals(new Parade(Type.FEU)));
			}
			else {
				if(listeBataille.isEmpty()) {
					return false;
				}
				Probleme last = (Probleme) listeBataille.get(listeBataille.size()-1);
				Probleme probBataille = bataille;
				return !listeBataille.isEmpty() && last.getType().equals(probBataille.getType());
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Bataille bataille) {
			if(carte.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			}
			else {
				return estDepotBatailleAutorise(bataille);
			}
		}
		else if(carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		else if(carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		else if(carte instanceof Botte) {
			return true;
		}
		return false;
	}
	
	private boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
	
	public Set<Botte> getBottes() {
		return bottes;
	}
	
	public List<Carte> getListeBataille() {
		return listeBataille;
	}
}