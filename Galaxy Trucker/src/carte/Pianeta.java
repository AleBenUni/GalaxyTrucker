package carte;

import java.util.Map;

import componenti.Merce;

public class Pianeta {
//questa classe serve solo per le carte realizzate in Pianeti.java e in Stazione Abbandonata (userà un solo pianeta)
	
	private Map<Merce, Integer> mercexPianeta; // Tipo merce e quantita di quella merce

	 public Pianeta(Map<Merce, Integer> mercexPianeta) {
	        this.mercexPianeta = mercexPianeta;
	    }
	 //servira un set? per le casse prese dal giocatore o semplicemente impediamo di fare la stessa scelta ad altri giocatori? 
	 
	public Map<Merce, Integer> getMercexPianeta() {
		return mercexPianeta;
	}
	
	public int getnMercexPianeta(){
		int totale = 0;
	    if (mercexPianeta != null) {
	        for (Integer quantita : mercexPianeta.values()) {
	            totale += quantita;
	        }
	    }
	    return totale;
	}
	
	//realizza una get del valore per ogni pianeta (in futuro)  <--- Forse non serve

	@Override
	public String toString() {
		return "un pianeta con " + getnMercexPianeta() + " merci " + getMercexPianeta()  ;
	}
	
}
