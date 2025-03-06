package testsFonctionnels;

import jeu.*;

import java.util.Iterator;

import cartes.*;

public class TestSabot {
	
	public void testSabotPiocher() {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (int i = 0; i < tabCartes.length; i++) {
			System.out.println("Je pioche "+sabot.piocher().toString());
		}
		if(sabot.estVide()) System.out.println("C'est bien vide\n");
	}
	
	public void testSabotRemove() {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			System.out.println("Je pioche "+iterator.next().toString());
			iterator.remove();
		}
		if(sabot.estVide()) System.out.println("C'est bien vide\n");
	}
	
	public void testSabotRemoveAltPiocher() {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			System.out.println("Je pioche "+iterator.next().toString());
			iterator.remove();
		}
		if(sabot.estVide()) System.out.println("C'est bien vide\n");
		sabot.piocher();
	}
		
	public static void main(String[] args) {
		TestSabot test = new TestSabot();
		test.testSabotPiocher();
		test.testSabotRemove();
		test.testSabotRemoveAltPiocher();
	}
}
