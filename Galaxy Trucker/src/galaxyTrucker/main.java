package galaxyTrucker;
import carte.Carta;
import carte.Livello;
import carte.SpazioAperto;
import componenti.Connettore;
import componenti.Motore;
import carte.Mazzo;
import carte.MazzoVuoto;

import java.util.Map;
import java.util.Random;

import carte.CaricaCSV;

public class main {

	public static void main(String[] args) {
        
		//questo main è solo una prova del file csv. Può essere modificato senza preavviso
		
		String c="Vuoto";
		Motore m1 = new Motore(20, Connettore.doppio, Connettore.doppio, Connettore.doppio, Connettore.doppio);
		
		try {

		Mazzo mazzo = new Mazzo();
        
        Carta pescata = mazzo.pescadalMazzo();
        System.out.printf("Ha pescato--> " + pescata);
        mazzo.rivelaCarteMazzo();
        Mazzo empty = new MazzoVuoto(); // Qualcosa qui non quadra, da Caricate 8 carte quando ne carica 0
empty.aggiungiAlMazzo(pescata);
        empty.rivelaCarteMazzo();
        
        empty.trasferisciCartaDaMazzo(Livello.III, mazzo);
        empty.rivelaCarteMazzo();
        pescata = empty.pescadalMazzo();
        System.out.println("Ha pescato--> " + pescata);
        empty.aggiungiAlMazzo(pescata);
        System.out.println("Il mazzo principale ora: " );
        mazzo.rivelaCarteMazzo();
        
        
        System.out.println("\nSposto tutte le in empty: " );
        empty.unisciMazzi(mazzo);
        System.out.println("\nOra in Empty : " );
        empty.rivelaCarteMazzo();
        System.out.println("\nMentre nel mazzo prinicipale : " );
        mazzo.rivelaCarteMazzo();
        /*
       if (pescata instanceof SpazioAperto) // istanceof == è un? 
       {
    	   pescata.setGiorniVolo(m1.getNTubiScappamento());
    	   System.out.printf("\nL'effetto della carta ora è: " + pescata);
       }
        
        System.out.printf("\n\nCi sono ancora " + mazzo.size() + " carte\n\n");
        for (Map.Entry<Integer, Carta> i : mazzo.entrySet()) {
            System.out.printf("ID: " + i.getKey() + " " + i.getValue() + "\n");       
        }
*/ 
	} catch (Exception e) {
        e.printStackTrace();
    }
       
}

}