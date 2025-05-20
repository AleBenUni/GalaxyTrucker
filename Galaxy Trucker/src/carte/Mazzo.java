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
	
	private void caricaMazzo(String CSV) {
		try {
            mazzo = CaricaCSV.loadMap(CSV);
            System.out.printf("Il mazzo è pronto");
        } catch (IOException e) {
        	 e.printStackTrace();
        }
	}
	 
	public Mazzo() {
	        caricaMazzo(CSV);
	    }
	
	private int Casuale () {
		Integer[] listaIDMazzo = mazzo.keySet().toArray(new Integer[0]); 
		Integer idCasuale = listaIDMazzo[r.nextInt(listaIDMazzo.length)];
		return idCasuale;
    }
	
	public Carta pescadalMazzo() {
		if (mazzo.isEmpty()) {
            return null;
        }
		 return mazzo.remove(Casuale ());
	}
	
	public void aggiungiAlMazzo(Carta pescata) {
		mazzo.put(pescata.getId(), pescata);
	}
	
	public void rivelaCarteMazzo() {
		System.out.println("\nCi sono " + mazzo.size() + " carte nel mazzo.");
	        for (Entry<Integer, Carta> carta : mazzo.entrySet()) {
	            System.out.printf("ID: " + carta.getKey() + " = " + carta.getValue() + "\n");
	    }
	}
	
	private Carta pescaPerLivello(Livello livello) {
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
	            return mazzo.remove(idCasuale);
	        } else {
	        	contatore++;
	        }
		}while(contatore<2000);
		return null;
	}
	
	public void trasferisciCartaDaMazzo(Livello carta, Mazzo dacuiTrasferire) {
		Carta inMovimento  = dacuiTrasferire.pescaPerLivello(carta);
		if (inMovimento != null) {
			this.mazzo.put(inMovimento.getId(), inMovimento);
		} else {
			System.out.println("Non ci sono carte che soddisfano il livello richiesto. Operazione annulata");
		}
	}
	
	public void unisciMazzi(Mazzo dacuiTrasferire) {
		 mazzo.putAll(dacuiTrasferire.mazzo);
		 dacuiTrasferire.mazzo.clear(); //Ecco cosa sbagliavo, per i mazzi trasferiti come parametro va inserita .mazzo
	}
}

