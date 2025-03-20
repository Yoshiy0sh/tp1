package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	public static void testPartie2() {
		ZoneDeJeu zone = new ZoneDeJeu();
		System.out.println("deposer borne de 25");
		zone.deposer(new Borne(25));
		System.out.println("deposer borne de 50");
		zone.deposer(new Borne(50));
		System.out.println("deposer borne de 75");
		zone.deposer(new Borne(75));
		System.out.println("donner nbKm : "+ zone.donnerKmParcourus());
		System.out.println("limitation de vitesse : "+zone.donnerLimitationVitesse());
		System.out.println("poser limite");
		zone.deposer(new DebutLimite());
		System.out.println("limitation de vitesse : "+zone.donnerLimitationVitesse());
		System.out.println("poser fin limite");
		zone.deposer(new FinLimite());
		System.out.println("limitation de vitesse : "+zone.donnerLimitationVitesse());
	}
	
	public static void testPartie3() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("Test de la partie 2");
		testPartie2();
		System.out.println("Test de la partie 3");
		testPartie3();
	}
}
