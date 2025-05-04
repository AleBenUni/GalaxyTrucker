package carte;

public enum Livello {
	I(1),
	II(2),
	III(3);
	
	private final int livello; // dato final in quanto non modificabile nel tempo
	
	// Costruttore
	Livello(int livello){
		this.livello = livello;
	}
	
	// Metodi
	public int getLivello() {
		return livello;
	}
}
