package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes;
	
	public String affichageJeuDeCartes() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("JEU : \n\n");
		for(Configuration config : typesDeCartes) {
			chaine.append(config.getNbExemplaires() + " " + config.getCarte().toString());
		}
		return chaine.toString();
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for(Configuration config : typesDeCartes) {
			nbCartes += config.getNbExemplaires();
		}
		Carte[] tab = new Carte[nbCartes];
		
		return tab;
	}
	
	private class Configuration{
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
}
