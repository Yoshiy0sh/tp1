package testsFonctionnels;

import cartes.*;

public class TestJeuDeCartes {
	public void testAffichage() {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)};
		int[] tabNb = {10,10,10,12,4,14,6,6,6,6,5,4,3,3,3,1,1,1,1};
		JeuDeCartes jeu = new JeuDeCartes();
		jeu.ajouterConfiguration(tabCartes, tabNb);
		System.out.println(jeu.affichageJeuDeCartes());
	}
	
	public static void main(String[] args) {
		TestJeuDeCartes test = new TestJeuDeCartes();
		test.testAffichage();
	}
}