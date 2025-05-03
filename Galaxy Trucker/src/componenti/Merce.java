package componenti;

public enum Merce {
	rosso (4), 
	giallo (3), 
	verde (2), 
	blu (1); // istanze pre create con il costruttore Merce (int valore)
	
	private final int valore; // dato final in quanto non modificabile nel tempo
	
	// Costruttore
	Merce(int valore){
		this.valore = valore;
	}
	
	// Metodi
	public int getValore() {
		return valore;
	}
	
}
