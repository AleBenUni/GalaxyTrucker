package carte;

import java.util.Map;

public class Pianeta {
//questa classe serve solo per le carte realizzate in Pianeti.java
	
	private Map<Merce, Integer> mercexPianeta = null;

	 public Pianeta(Map<Merce, Integer> mercexPianeta) {
	        this.mercexPianeta = mercexPianeta;
	    }
	public Map<Merce, Integer> getMercexPianeta() {
		return mercexPianeta;
	}

	@Override
	public String toString() {
		return "Pianeta [mercexPianeta=" + mercexPianeta + "]";
	}
	
	
}
