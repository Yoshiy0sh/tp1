package testsFonctionnels;

import cartes.*;

public class TestMethodEquals {
	public static void main(String[] args) {
		Borne borne1 = new Borne(25);
		Borne borne2 = new Borne(25);
		System.out.println("Deux cartes borne 25km identiques ? : "+borne1.equals(borne2));
		Attaque feu1 = new Attaque(Type.FEU);
		Attaque feu2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes feu rouge identiques ? : "+feu1.equals(feu2));
		Parade feuVert = new Parade(Type.FEU);
		System.out.println("Carte feu rouge et feu vert identiques ? : "+feu1.equals(feuVert));
	}
}
