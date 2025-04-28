package cartes;

public abstract class Probleme extends Carte {
	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return 31 * type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Probleme probleme) {
			return this.type.equals(probleme.type);
		}
		return false;
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj.getClass() == this.getClass()) {
//			Probleme probleme = (Probleme)obj;
//			return (this.type == probleme.getType());
//		}
//		else return false;
//	}
}
