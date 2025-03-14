package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.JeuDeCartes;
import cartes.Parade;
import cartes.Type;

public class GestionCartes {
	private static Random random = new Random();
	
	public static <T> T extraire(List<T> liste){
		int index = random.nextInt(liste.size());
		T elt = liste.get(index);
		liste.remove(index);
		return elt;
	}
				
	public static <T> T extraireIterator(List<T> liste) {
		ListIterator<T> iterator = liste.listIterator(random.nextInt(liste.size()));
		T elt = iterator.next();
		iterator.remove();
		return elt;
	}
	
	//dans mélanger, on passe une liste en argument et on en retourne une autre, chaque élément sera extrait de la
	//liste argument et placée à un endroit aléatoire dans la nouvelle liste
	public static <T> List<T> melanger(List<T> liste) {
		List<T> nouvelleListe = new ArrayList<>(liste);
		Collections.shuffle(nouvelleListe);
		liste.clear();
		return nouvelleListe;
	}
	
	public static <T> boolean verifierMelange(List<T> l1,List<T> l2) {
		for (T t : l1) {
			if(Collections.frequency(l1,t) != Collections.frequency(l2, t)) return false;
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		List<T> listeStock = new ArrayList<T>();
		while(!liste.isEmpty()) {
			T elt = liste.get(0);
			while(liste.contains(elt)) {
				liste.remove(elt);
				listeStock.add(elt);
			}
		}
		return listeStock;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		T prev = liste.get(0);
		for (ListIterator<T> iterator = liste.listIterator(1); iterator.hasNext();) {
			T t = iterator.next();
			if(!t.equals(prev)) {
				for (ListIterator<T> iterator2 = liste.listIterator(liste.indexOf(t)); iterator2.hasNext();) {
					T temp = iterator2.next();
					if(temp.equals(prev)) return false;
				}
			}
			prev = t;
		}
		return true;
	}
	
	public static void test() {
		Carte[] tabCartes = {new Borne(25),new Borne(50),new Borne(75),new Borne(100)
				,new Borne(200),new Parade(Type.FEU),new FinLimite(), new Parade(Type.ESSENCE)
				,new Parade(Type.CREVAISON),new Parade(Type.ACCIDENT),new Attaque(Type.FEU)
				,new DebutLimite(),new Attaque(Type.ESSENCE),new Attaque(Type.CREVAISON),new Attaque(Type.ACCIDENT)
				,new Botte(Type.FEU),new Botte(Type.ESSENCE),new Botte(Type.CREVAISON),new Botte(Type.ACCIDENT)};
		int[] tabNb = {10,10,10,12,4,14,6,6,6,6,5,4,3,3,3,1,1,1,1};
		JeuDeCartes jeu = new JeuDeCartes();
		jeu.ajouterConfiguration(tabCartes, tabNb);
		
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste mélangée sans erreur ? "
		+ GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println(listeCartes + "\n");
		System.out.println("liste rassemblée sans erreur ? "
		+ GestionCartes.verifierRassemblement(listeCartes));
	}
	
	public static void main(String[] args) {
		test();
	}
}
