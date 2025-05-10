package carte;

import componenti.Merce;  
import carte.Livello; 
import pezzettini.Pedine;

public class Carte {
	
	private final int giorniVolo; 
	private final int credito; 
	private final int equipaggio;
	private final int merce;
	

	private Livello livello;

//richiama da tabellone la funzione riguardante la perdita di equipaggio
	public Carte(Livello livello, int giorniVolo, int merce, int equipaggio, int credito) {
	    this.giorniVolo = giorniVolo;
	    this.equipaggio = equipaggio;
	    this.livello= livello;
	    this.credito= credito;
	    this.merce= merce;
	}

	public Livello getLivello() {
		return livello;
	}

	public int getGiorniVolo() {
		return giorniVolo;
	}

	public int getCredito() {
		return credito;
	}

	public int getEquipaggio() {
		return equipaggio;
	}

	public int getMerce() {
		return merce;
	}

	@Override
	public String toString() {
		return "Carte [ Livello carta=" + livello + ", Giorni di volo=" + giorniVolo + ", Credito guadagnato=" + credito + ", Equipaggio perso=" + equipaggio + ", Merce="
				+ merce + "]\nIl + indica un guadagno, il - indica una perdita (solo per merce e Giorni di volo).";
	}
	
	
	
}