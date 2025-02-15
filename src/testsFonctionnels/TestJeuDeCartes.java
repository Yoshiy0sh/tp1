package testsFonctionnels;

import cartes.*;

public class TestJeuDeCartes {
	public void testAffichage() {
		Carte[] tabCartes = {new Borne(25),new Attaque(Type.FEU), new Botte(Type.ESSENCE)};
		int[] tabNb = {10,14,1};
		JeuDeCartes jeu = new JeuDeCartes();
		jeu.ajouterConfiguration(tabCartes, tabNb);
		System.out.println(jeu.affichageJeuDeCartes());
	}
	
	public static void main(String[] args) {
		TestJeuDeCartes test = new TestJeuDeCartes();
		test.testAffichage();
	}
}