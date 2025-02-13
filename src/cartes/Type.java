package cartes;

public enum Type {
	FEU("FeuRouge","FeuVert","VegiculePrioritaire"),
	ESSENCE("PanneEssence","StationEssence","Citerne"),
	CREVAISON("Crevaison","NouveauPneu","Increvable"),
	ACCIDENT("AccidentVoiture","RéparationVoiture","AsDuVolant");
	
	private final String casAttaque;
	private final String casParade;
	private final String casBotte;
	
	private Type(String casAttaque,String casParade,String casBotte) {
		this.casAttaque = casAttaque;
		this.casBotte = casBotte;
		this.casParade = casParade;
	}
	
	public String getCasAttaque() {
		return casAttaque;
	}
	
	public String getCasParade() {
		return casParade;
	}
	
	public String getCasBotte() {
		return casBotte;
	}
}
