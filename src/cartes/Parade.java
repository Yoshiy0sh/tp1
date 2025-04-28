package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
		}
	
	@Override
	public String toString() throws IllegalStateException{
		return super.getType().getCasParade();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Parade parade) {
			return this.getType().equals(parade.getType());
		}
		return false;
	}

}
