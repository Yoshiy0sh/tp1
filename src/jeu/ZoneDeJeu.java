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
import cartes.Type;

public class ZoneDeJeu {
	private ArrayList<Carte> listeLimites = new ArrayList<>();;
	private ArrayList<Carte> listeBataille = new ArrayList<>();
	private ArrayList<Carte> listeBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		Carte last = listeLimites.get(listeLimites.size()-1);
		if(listeLimites.isEmpty() || last.equals(new FinLimite())) {
			return 200;
		}
		else {
			return 50;
		}
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
		Carte last = listeBataille.get(listeBataille.size()-1);
		return !listeBataille.isEmpty() && last.equals(new Parade(Type.FEU));
	}
	
	private boolean estDepotFeuVertAutorise() {
		Carte last = listeBataille.get(listeBataille.size()-1);
		return listeBataille.isEmpty() || last.equals(new Attaque(Type.FEU))
				|| ((last instanceof Parade) && (!last.equals(new Parade(Type.FEU))));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		int limitation = donnerLimitationVitesse();
		int kmBorne = borne.getKm();
		return peutAvancer() && (kmBorne <= limitation) && (donnerKmParcourus() + kmBorne <= 1000);
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			return listeLimites.isEmpty() || (listeLimites.get(listeLimites.size()-1) 
					instanceof FinLimite);
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
			Carte last = listeBataille.get(listeBataille.size()-1);
			if(bataille.equals(new Parade(Type.FEU))) {
				return listeBataille.isEmpty() || last.equals(new Attaque(Type.FEU)) 
						|| ((last instanceof Parade) && !last.equals(new Parade(Type.FEU)));
			}
			else {
				return !listeBataille.isEmpty() && last.equals(bataille);
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