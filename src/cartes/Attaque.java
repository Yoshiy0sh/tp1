package cartes;

public class Attaque extends Bataille{

	public Attaque(Type type) {
		super(type);
	}
	
	@Override
	public String toString() throws IllegalStateException{
		return super.getType().getCasAttaque();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Attaque attaque) {
			return this.getType().equals(attaque.getType());
		}
		return false;
	}
}
