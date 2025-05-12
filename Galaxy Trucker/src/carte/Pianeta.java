package carte;

import java.util.Map;

import componenti.Merce;

public class Pianeta {
//questa classe serve solo per le carte realizzate in Pianeti.java
	
	private Map<Merce, Integer> mercexPianeta; // Tipo merce e quantita di quella merce

	 public Pianeta(Map<Merce, Integer> mercexPianeta) {
	        this.mercexPianeta = mercexPianeta;
	    }
	 
	public Map<Merce, Integer> getMercexPianeta() {
		return mercexPianeta;
	}
	
	public int getnMercexPianeta(){
		int totale = 0;
	    if (mercexPianeta != null) {
	        for (Integer quantita : mercexPianeta.values()) { //fai per ogni Integer chiamato quantita delle cose per ogni singolo valore che trovi in questa collezione
	            totale += quantita;
	        }
	    }
	    return totale;
	}

	@Override
	public String toString() {
		return " Abbiamo un pianeta con " + getnMercexPianeta() + " merci " + getMercexPianeta()  ;
	}
	
	
}
