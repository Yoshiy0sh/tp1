package testsFonctionnels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import main.main;
import utils.GestionCartes;

public class TestGestionCartes {
	
	private static Random random = new Random();
	
	
	public void testExtraire() {
		System.out.println("testExtraire\n");
		ArrayList<Carte> arrayCartes = init();
		ArrayList<Carte> arrayCartes2 = init(); 
		System.out.println(arrayCartes + "\n" + arrayCartes.size());
		System.out.println(GestionCartes.extraire(arrayCartes2));
		System.out.println(arrayCartes2.size());
		System.out.println(arrayCartes2);
		System.out.println(GestionCartes.extraireIterator(arrayCartes2));
	}
	
	public void testMelanger() {
		ArrayList<Carte> arrayCartes = init();
		System.out.println(arrayCartes);
		System.out.println(GestionCartes.melanger(arrayCartes));
	}
	
	public void testVerifierMelange() {
		ArrayList<String> liste1 = new ArrayList<>();
		liste1.add("1");
		liste1.add("2");
		liste1.add("2");
		liste1.add("3");
		ArrayList<String> liste2 = new ArrayList<>();
		liste2.addAll(liste1);
		liste2.add("3");
		
		System.out.println(liste1);
		System.out.println(liste2);
		System.out.println(GestionCartes.verifierMelange(liste1,liste2));
		
		System.out.println(liste1);
		System.out.println(liste1);
		System.out.println(GestionCartes.verifierMelange(liste1,liste1));
		
		liste1.add(0,"3");
		System.out.println(liste1);
		System.out.println(liste2);
		System.out.println(GestionCartes.verifierMelange(liste1,liste2));
	}
	
	public void testRassembler() {
		ArrayList<String> liste = new ArrayList<>();
		
	}
	
	public void testVerifierRassemblement() {
		
	}
	
	public static void main(String[] args) {
		TestGestionCartes test = new TestGestionCartes();
		test.testExtraire();
	}
	
	
	
}
