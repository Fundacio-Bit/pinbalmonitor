package es.caib.pinbalmonitor.plugins;

public enum Entorns {
	PRODUCCIO("produccio"), 
	PROVES("proves");
	
    private String codi;

    Entorns(String codi) {
	this.codi = codi;
	}
	public String getCodi(){return codi;}
}


