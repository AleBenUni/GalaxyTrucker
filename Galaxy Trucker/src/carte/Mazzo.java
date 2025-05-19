package carte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;  //questa mi permette di rimuovere Map da Map.Entry nei cicli che interessano le Mappe 
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
	
	private void caricaMazzo(String CSV) {
		try {
            mazzo = CaricaCSV.loadMap(CSV);
            System.out.printf("Caricate " + mazzo.size() + " carte\n\n");
        } catch (IOException e) {
        	 e.printStackTrace();
        }
	}
	
	public Carta pescadalMazzo() {
		if (mazzo.isEmpty()) {
            return null;
        }
		 return mazzo.remove(Casuale ());
	}
	
	public void rivelaCarteMazzo() {
		System.out.println("\nCi sono " + mazzo.size() + " carte nel mazzo.");
	        for (Entry<Integer, Carta> carta : mazzo.entrySet()) {
	            System.out.printf("ID: " + carta.getKey() + " = " + carta.getValue() + "\n");
	    }
	}
	
	private Integer pescaPerLivello(Livello livello) {
		if (mazzo.isEmpty()) {
            return null;
        }
		int contatore=0;
		Carta carta;
		Integer idCasuale = 0;
		do {
			idCasuale = Casuale();
			carta = mazzo.get(idCasuale);
			if (carta.getLivello() == livello) {
	            return idCasuale;
	        } else {
	        	contatore++;
	        }
		}while(contatore<200);
		return null;
	}
	
	private Carta getCartaDaID(Integer ID) { 
		 return mazzo.get(ID);
	}
	
	private void rimuoviCartaDaID(Integer ID) {
		mazzo.remove(ID);
	}
	
	public void trasferisciCartaDaMazzo(Livello carta, Mazzo dacuiTrasferire) {
		 Integer IDinMovimento = dacuiTrasferire.pescaPerLivello(carta);
		 Carta inMovimento = dacuiTrasferire.getCartaDaID(IDinMovimento);
		if (inMovimento != null) {
			this.mazzo.put(IDinMovimento, inMovimento);
			dacuiTrasferire.rimuoviCartaDaID(IDinMovimento);
		} else {
			System.out.println("Non ci sono carte che soddisfano il livello richiesto. Operazione annulata");
		}
	}
}

