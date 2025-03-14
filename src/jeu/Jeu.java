package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.JeuDeCartes;
import cartes.Parade;
import cartes.Type;

public class Jeu {
	private Sabot sabot;
	
	public Jeu(JeuDeCartes jeuDeCartes) {
		Carte[] tabCartes = jeuDeCartes.donnerCartes();
	}
	
	public static void main(String[] args) {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)};
		
		List<Carte> liste = new ArrayList<>();
	}
}
