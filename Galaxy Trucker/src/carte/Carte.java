package carte;

import componenti.Merce;  
import carte.Livello; 
import pezzettini.Pedine;

public class Carte {
	
	private final int giorniVolo; 
	private final int credito; 
	private final int equipaggio;
	

	private Livello livello;

//richiama da tabellone la funzione riguardante la perdita di equipaggio
	public Carte(Livello livello, int giorniVolo, int equipaggio, int credito) {
	    this.giorniVolo = giorniVolo;
	    this.equipaggio = equipaggio;
	    this.livello= livello;
	    this.credito= credito;
	}

	

	@Override
	public String toString() {
		return "Carte [giorniVolo=" + giorniVolo + ", credito=" + credito + ", perditeEquipaggio=" + equipaggio
				+ ", livello=" + livello + "]";
	}
	
}