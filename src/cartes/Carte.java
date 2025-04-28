package cartes;

public abstract class Carte {
	public abstract String toString();
	
	@Override
	public int hashCode() {
		return 31 * getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean rep = false;
		try {
			rep = (this.getClass() == obj.getClass());
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return rep;
	}
	
}
