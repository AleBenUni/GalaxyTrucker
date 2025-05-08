package carte;

import componenti.Merce;  
import carte.Livello; 
import pezzettini.Pedine;

public class Carte {
	
	private final int giorniVolo; 
	private final int credito; 
	private final int perditeEquipaggio;
	

	private Livello livello;

//richiama in tabellone un metodo
	public Carte(Livello livello, int giorniVolo, int perditeEquipaggio, int credito) {
	    this.giorniVolo = giorniVolo;
	    this.perditeEquipaggio = perditeEquipaggio;
	    this.livello= livello;
	    this.credito= credito;
	}


	@Override
	public String toString() {
		return "Carte [giorniVolo=" + giorniVolo + ", credito=" + credito + ", perditeEquipaggio=" + perditeEquipaggio
				+ ", livello=" + livello + "]";
	}
	
}