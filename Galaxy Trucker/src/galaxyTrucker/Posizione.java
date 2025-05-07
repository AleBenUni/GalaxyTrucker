package galaxyTrucker;

public class Posizione {
	private final int riga;
	private final int colonna;
	
	public Posizione(int riga, int colonna) {
		this.riga=riga;
		this.colonna=colonna;
	}
	
	public int getRiga() {
		return riga;
	}
	
	public int getColonna() {
		return colonna;
	}
}

