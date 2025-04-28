package testsFonctionnels;
import java.util.ArrayList;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.JeuDeCartes;
import cartes.Parade;
import cartes.Type;
import jeu.*;
import main.main;

public class TestJeu {
	public static void test1() {
		ArrayList<Joueur> participants = new ArrayList<>();
		participants.add(new Joueur("Jack"));
		participants.add(new Joueur("Bill"));
		participants.add(new Joueur("Luffy"));
		
		
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)};
		int[] tabNb = {10,10,10,12,4,14,6,6,6,6,5,4,3,3,3,1,1,1,1};
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		jeuDeCartes.ajouterConfiguration(tabCartes, tabNb);
		
		
		Jeu jeu = new Jeu(jeuDeCartes);
		for (Joueur joueur : participants) {
			jeu.inscrire(joueur);
		}
		
		jeu.distribuerCartes();
		
		for (Joueur joueur : participants) {
			System.out.println(joueur.afficherEtatJoueur());
		}
		
		for (Joueur joueur : participants) {
			System.out.println(jeu.jouerTour(joueur));
		}
	}
	
	public static void test2() {
		ArrayList<Joueur> participants = new ArrayList<>();
		participants.add(new Joueur("Jack"));
		participants.add(new Joueur("Bill"));
		participants.add(new Joueur("Luffy"));
		
		
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)};
		int[] tabNb = {10,10,10,12,4,14,6,6,6,6,5,4,3,3,3,1,1,1,1};
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		jeuDeCartes.ajouterConfiguration(tabCartes, tabNb);
		
		
		Jeu jeu = new Jeu(jeuDeCartes);
		for (Joueur joueur : participants) {
			jeu.inscrire(joueur);
		}
		
		jeu.distribuerCartes();
		
		for (Joueur joueur : participants) {
			System.out.println(joueur.afficherEtatJoueur());
		}
		
		String chaineFinale = jeu.lancer();
		
		System.out.println(chaineFinale);
	}
	
	public static void main(String[] args) {
//		System.out.println("Debut du test1");
//		test1();
//		System.out.println("fin du test 1");
		
		System.out.println("Debut du test 2");
		test2();
		System.out.println("fin du test 2");
	}
	
}
