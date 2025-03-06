package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes;
	
	public void ajouterConfiguration(Carte[] cartes, int[] nbCartes) {
		Configuration[] tabFinal = new Configuration[nbCartes.length];
		for (int i = 0; i < tabFinal.length; i++) {
			tabFinal[i] = new Configuration(cartes[i],nbCartes[i]);
		}
		typesDeCartes = tabFinal;
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("JEU : \n\n");
		for(Configuration config : typesDeCartes) {
			chaine.append(config.getNbExemplaires() + " " + config.getCarte().toString()+"\n");
		}
		return chaine.toString();
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for(Configuration config : typesDeCartes) {
			nbCartes += config.getNbExemplaires();
		}
		Carte[] tab = new Carte[nbCartes];
		
		int indexTab = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaires(); j++) {
				tab[indexTab + j] = typesDeCartes[j].getCarte();
				indexTab ++;
			}
		}
		return tab;
	}
	
	public boolean checkCount() {
		Carte[] tabDonnerCartes = donnerCartes();
		int compteur = 0;
		for(Configuration config : typesDeCartes) {
			compteur += config.getNbExemplaires();
		}
		return compteur == tabDonnerCartes.length;
	}
	
	private class Configuration{
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
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
