package carte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;  //questa mi permette di rimuovere Map da Map.Entry nei cicli che interessano le Mappe 
import java.util.Random;
import java.util.Scanner;

import carte.CaricaCSV;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;


/**
 * Rappresenta un mazzo di carte giocabili, gestendo operazioni come pesca, trasferimento e attivazione.
 * <p>
 * Il mazzo può essere inizializzato in due modi:
 * <ul>
 *   <li><b>Mazzo completo</b>: Carica 50 carte da file CSV (simulando il gioco base)</li>
 *   <li><b>Mazzo vuoto</b>: Contenitore inizialmente vuoto per gestione dinamica delle carte</li>
 * </ul>
 * 
 * @see carte.Carta
 * @see galaxyTrucker.Gioco
 */
public class Mazzo {
	String CSV = "src\\carte\\carte.csv";
	Map<Integer, Carta> mazzo;
	private Random r = new Random();
	
	private void caricaMazzo(String CSV) {
		try {
            mazzo = CaricaCSV.loadMap(CSV);
            if (mazzo == null) {
            	System.err.printf("Verra generato un mazzo vuoto\n");
            	Empty();
            } else { 
            	mazzo = CaricaCSV.loadMap(CSV);
            System.out.printf("Il mazzo di 50 carte è pronto\n");
            }
            
           
        } catch (IOException e) {
        	 e.printStackTrace();
        	 System.out.printf("Il mazzo di 0 carte è pronto\n");
        }
	}
	 
	/**
	 * 
     * Costruisce un mazzo di carte.
     * 
     * @param vuoto Se true, crea un mazzo vuoto. Se false, carica 150 carte da CSV
     * @implNote Per il gioco base usare {@code new Mazzo(false)}
     *
	 * @param vuoto
	 */
	public Mazzo(boolean vuoto) {
		if(vuoto==true)
			Empty();
		else
	        caricaMazzo(CSV);
	    }
	
	private void Empty() {
		mazzo = new HashMap<>();
		System.out.printf("Il mazzo di 0 carte è pronto\n");
	}
	
	private int Casuale () {
		Integer[] listaIDMazzo = mazzo.keySet().toArray(new Integer[0]); 
		Integer idCasuale ;
		try {
			idCasuale = listaIDMazzo[r.nextInt(listaIDMazzo.length)];
		} catch (IllegalArgumentException e) {
			throw new DoveSonoFiniteLeMieCarte();
		}
		return idCasuale;
    }
	
	public Carta pescadalMazzo() {
		Carta carta;
		try {
			 carta= mazzo.remove(Casuale ());
		} catch (DoveSonoFiniteLeMieCarte e) {
			carta = e.getCartaDemergenza();
		}
		return carta;
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
		//	System.out.println(" Trasferimento avvenuto con successo");
		} else {
			System.err.println(" Sono terminate le carte del Livello "+carta+". Trasferimento annullato");
		}
	}
	
	public void unisciMazzi(Mazzo dacuiTrasferire) {
		 mazzo.putAll(dacuiTrasferire.mazzo);
		 dacuiTrasferire.mazzo.clear(); //Ecco cosa sbagliavo, per i mazzi trasferiti come parametro va inserita .mazzo
	}
	
	public void svuotaMazzo() {
		mazzo.clear();
	}
	
	
	
	public void pescaAttivaEffetto(Gioco flotta) {
		Carta pescata = pescadalMazzo();
		pescata.attivaCarta(flotta);
		pescata=null;
	}

	
}

