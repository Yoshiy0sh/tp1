package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.*;

public class Sabot implements Iterable<Carte>{
	private Carte[] tabCartes;
	private int nbCartes;
	private int nombreOperations = 0;
	
	public Sabot(Carte[] carte) {
		this.tabCartes = carte;
		this.nbCartes = carte.length;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte){
		if(nbCartes == tabCartes.length) throw new IllegalStateException();
		else {
			tabCartes[nbCartes] = carte;
		}
		nombreOperations ++;
	}
	
	public Carte piocher() {
		Iterator<Carte> iter = iterator();
		Carte carte = iter.next();
		iter.remove();
		return carte;
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next(){
			verificationConcurrence();
			if(hasNext()) {
				Carte carteCourante = tabCartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carteCourante;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove(){
			verificationConcurrence();
			if(nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				tabCartes[i] = tabCartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
		}
		
		private void verificationConcurrence(){
			if(nombreOperations != nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
