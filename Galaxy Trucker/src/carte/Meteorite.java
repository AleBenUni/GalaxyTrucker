/*package carte;

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
	
	
}*/


package carte;

import componenti.Lato;

public class Meteorite {
	private final int quantita;
	private Meteorit[] meteoriti;
	
	public Meteorite(int quantita) {
		this.quantita = quantita;
		meteoriti=new Meteorit[quantità];
	}

	public int getQuantita() {
		return quantita;
	}
	
	public void setMeteoriti(Meteorit array[]) {
		if(Meteorit.length()==quantita)
			for(int i=0;i<quantita;i++)
				meteoriti[i]=array[i];
	}

	@Override
	public String toString() {
		return " Meteorite [dimensione=" + dimensione + ", quantita=" + quantita + "]";
	}
	
	
}
