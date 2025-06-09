package carte;

public class DoveSonoFiniteLeMieCarte extends NullPointerException {

	public DoveSonoFiniteLeMieCarte() {
		super();
	}

	public Carta getCartaDemergenza() {
		System.err.println("Il mazzo e' terminato");
		return new OratriceMecaniquedAnalyseCardinale(151, "Oratrice Mecanique d'Analyse Cardinale", Livello.III , 0, 0, 0, 0);
	}
	
}
