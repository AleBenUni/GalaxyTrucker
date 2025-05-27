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
	
	public static Livello toLivello(String livello) {
		switch(livello.toUpperCase()) {
			case "I":
				return Livello.I;
			case "II":
				return Livello.II;
			case "III":
				return Livello.III;
			case "1":
				return Livello.I;
			case "2":
				return Livello.II;
			case "3":
				return Livello.III;
			default:
				return null;
		}
	}
}
