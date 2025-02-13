package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}

	@Override
	public String toString() throws IllegalStateException{
		return super.getType().getCasBotte();
	}

}
