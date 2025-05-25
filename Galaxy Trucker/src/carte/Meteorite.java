package carte;

import componenti.Lato;

public class Meteorite {
	//3 . quantita, tipo, direzione
//	private final Lato direzione;
	private final Dimensione dimensione;
	private final int quantita;
	
	public Meteorite( Dimensione dimensione, int quantita) {
		this.dimensione = dimensione;
		this.quantita = quantita;
	}

	public Dimensione getDimensione() {
		return dimensione;
	}

	public int getQuantita() {
		return quantita;
	}

	@Override
	public String toString() {
		return " Meteorite [dimensione=" + dimensione + ", quantita=" + quantita + "]";
	}
	
	
}
