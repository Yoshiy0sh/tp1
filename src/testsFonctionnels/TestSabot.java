package testsFonctionnels;

import jeu.*;

import java.util.Iterator;

import cartes.*;

public class TestSabot {
	
	public void testSabotPiocher() {
		Carte[] tabCartes = {new Parade(Type.ACCIDENT),
				new Parade(Type.CREVAISON),
				new Borne(12),
				new Borne(20),
				new Borne(30),
				new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (int i = 0; i < tabCartes.length; i++) {
			System.out.println("Je pioche "+sabot.piocher().toString());
		}
		if(sabot.estVide()) System.out.println("C'est bien vide");
	}
	
	public void testSabotRemove() {
		Carte[] tabCartes = {new Parade(Type.ACCIDENT),
				new Parade(Type.CREVAISON),
				new Borne(12),
				new Borne(20),
				new Borne(30),
				new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			iterator.next();
			iterator.remove();
		}
		if(sabot.estVide()) System.out.println("C'est bien vide");
	}
	
	public void testSabotRemoveAltPiocher() {
		Carte[] tabCartes = {new Parade(Type.ACCIDENT),
				new Parade(Type.CREVAISON),
				new Borne(12),
				new Borne(20),
				new Borne(30),
				new Botte(Type.ACCIDENT)
		};
		Sabot sabot = new Sabot(tabCartes);
		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
			iterator.next();
			iterator.remove();
		}
		if(sabot.estVide()) System.out.println("C'est bien vide");
		sabot.piocher();
	}
		
	public static void main(String[] args) {
		TestSabot test = new TestSabot();
		test.testSabotPiocher();
		test.testSabotRemove();
		test.testSabotRemoveAltPiocher();
	}
}
