package carte;

import componenti.Lato;

public class Meteorite {
	 private final Dimensione dimensione;
	 private final Lato lato;

	
	 public Meteorite(Dimensione dimensione, Lato lato) {
	        this.dimensione = dimensione;
	        this.lato = lato;
	    }

	 public Dimensione getDimensione() {
	        return dimensione;
	    }
	 
	 public Lato getLato() {
	        return lato;
	    }

	    @Override
	    public String toString() {
	        return "un meteorite " + dimensione + " da " + lato.stampaLato();
	    }
	
	
}
