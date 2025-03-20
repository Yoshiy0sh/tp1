package jeu;

import java.util.ArrayList;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private ArrayList<Carte> listeLimites = new ArrayList<>();;
	private ArrayList<Carte> listeBataille = new ArrayList<>();
	private ArrayList<Carte> listeBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(listeLimites.get(listeLimites.size()).equals(new FinLimite())
				|| listeLimites.isEmpty()) {
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
}
