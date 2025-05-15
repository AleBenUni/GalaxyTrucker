package carte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import carte.CaricaCSV;

public class Mazzo {
	String CSV = "src\\carte\\carte.csv";
	Map<Integer, Carta> mazzo;
	private Random r = new Random();
	
	private int Casuale () {
		Integer[] listaIDMazzo = mazzo.keySet().toArray(new Integer[0]); 
		Integer idCasuale = listaIDMazzo[r.nextInt(listaIDMazzo.length)];
		return idCasuale;
    }
	
	 public Mazzo() {
	        caricaMazzo(CSV);
	    }
	 
	 public void MazzoVuoto() {
	        this.mazzo = new HashMap<>();
	    }
	
	private void caricaMazzo(String CSV) {
		try {
            this.mazzo = CaricaCSV.loadMap(CSV);
            System.out.printf("Caricate " + mazzo.size() + " carte\n\n");
        } catch (IOException e) {
        	 e.printStackTrace();
        }
	}
	
	public Carta pescadalMazzo() {
		 return mazzo.remove(Casuale ());
	}
	
	
}

