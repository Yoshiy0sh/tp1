package jeu;

import java.util.ArrayList;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Probleme;
import cartes.Type;

public class ZoneDeJeu {
	private ArrayList<Carte> listeLimites = new ArrayList<>();;
	private ArrayList<Carte> listeBataille = new ArrayList<>();
	private ArrayList<Carte> listeBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(listeLimites.isEmpty()) {
			return 200;
		}
		Carte last = listeLimites.get(listeLimites.size()-1);
		if(last.equals(new FinLimite())) {
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
	}
	
	public boolean peutAvancer() {
		if(listeBataille.isEmpty()) {
			return false;
		}
		Carte last = listeBataille.get(listeBataille.size()-1);
		return !listeBataille.isEmpty() && last.equals(new Parade(Type.FEU));
	}
	
	private boolean estDepotFeuVertAutorise() {
		if(listeBataille.isEmpty()) {
			return true;
		}
		Carte last = listeBataille.get(listeBataille.size()-1);
		return last.equals(new Attaque(Type.FEU))
				|| ((last instanceof Parade) && (!last.equals(new Parade(Type.FEU))));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		int limitation = donnerLimitationVitesse();
		int kmBorne = borne.getKm();
		return peutAvancer() && (kmBorne <= limitation) && (donnerKmParcourus() + kmBorne <= 1000);
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
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
				Probleme probBataille = (Probleme) bataille;
				return !listeBataille.isEmpty() && last.getType().equals(probBataille.getType());
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Bataille bataille) {
			if(carte.equals(new Parade(Type.FEU))) {
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
		return false;
	}
}