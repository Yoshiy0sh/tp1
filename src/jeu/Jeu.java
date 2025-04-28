package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private ArrayList<Joueur> joueurs = new ArrayList<>();
	private Iterator<Joueur> iterJoueurs;

	public Jeu(JeuDeCartes jeuDeCartes) {
		Carte[] tabCartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, tabCartes);
		listeCartes = GestionCartes.melanger(listeCartes);

		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
//		sabot = new Sabot((Carte[]) listeCartes.toArray());
//		Je ne sais pas quel choix faire, à demander prof
	}
	
	public void inscrire(Joueur joueur) {
		joueurs.add(joueur);
	}
	
	public void distribuerCartes() {
		for(int i = 0;i<6;i++) {
			for (Joueur joueur : joueurs) {
				joueur.prendreCarte(sabot);
			}
		}
	}
	
	public String jouerTour(Joueur courant) {
		StringBuilder chaine = new StringBuilder();
		Carte cartePiochee = courant.prendreCarte(sabot);
		
		String stringCartePiochee;
		
		if(cartePiochee == null) {
			stringCartePiochee = "carte nulle";
		}
		else {
			stringCartePiochee = cartePiochee.toString();
		}
		chaine.append("Le joueur " + courant.getNom() + " pioche la carte "
				+ stringCartePiochee + ". ");
		
		Set<Joueur> set = new HashSet<>(joueurs);
		Coup coupChoisi = courant.choisirCoup(set);
		if(coupChoisi == null) return "Plus de carte";
		
		courant.retirerDeLaMain(coupChoisi.getCarteJouee());	
		
		Joueur joueurCible = coupChoisi.getCible();
		if(joueurCible == null) {
			sabot.ajouterCarte(coupChoisi.getCarteJouee());
		}
		else {
			joueurCible.deposer(coupChoisi.getCarteJouee());
		}
		
		chaine.append(coupChoisi.toString() + "\n");
		
//		chaine.append("LE JOUEUR "+ courant.getNom() +" A ACTUELLEMENT " + courant.donnerKmParcourus() + " bornes\n\n");
		chaine.append("   sabot vide ?" + sabot.estVide());
		
		return chaine.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if(!iterJoueurs.hasNext()) {
			iterJoueurs = joueurs.iterator();
		}
		return iterJoueurs.next();
	}
	
	private boolean joueurDessousLimite() {
		for (Joueur joueur : joueurs) {
			System.out.println("JOUEUR " + joueur.getNom() + " A " + joueur.donnerKmParcourus() + " BORNES");
			if(joueur.donnerKmParcourus() >= 1000) return false;
		}
		return true;
	}
	
	public String lancer() {
		iterJoueurs = joueurs.iterator();
		StringBuilder chaine = new StringBuilder();
		Joueur courant = donnerJoueurSuivant();
		while(joueurDessousLimite() && !sabot.estVide()) {
			chaine.append(jouerTour(courant));
			courant = donnerJoueurSuivant();
		}
		
		chaine.append("\nLe classement est : \n\n" + classement());
		return chaine.toString();
	}
	
	public NavigableSet<Joueur> classement(){
		TreeSet<Joueur> tree = new TreeSet<>(new JoueurComparator());
		for (Joueur joueur : joueurs) {
			tree.add(joueur);
		}
		return tree;
	}
}
