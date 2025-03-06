package cartes;

public class Borne extends Carte {
	private int km;
	
	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km+"KM";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.getClass() == obj.getClass()) {
			Borne borne = (Borne)obj;
			//comme je suis dans la classe borne, je peux accéder au champ km de borne.
			return this.km == borne.km;
		}
		return false;
	}
}
