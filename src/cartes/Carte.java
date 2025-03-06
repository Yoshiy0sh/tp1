package cartes;

public abstract class Carte {
	public abstract String toString();
	
	@Override
	public boolean equals(Object obj) throws NullPointerException{
		boolean rep = false;
		try {
			rep = (this.getClass() == obj.getClass());
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return rep;
	}
	
}
