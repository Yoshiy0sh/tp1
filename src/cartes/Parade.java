package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
		}
	
	@Override
	public String toString() throws IllegalStateException{
		return super.getType().getCasParade();
	}

}
