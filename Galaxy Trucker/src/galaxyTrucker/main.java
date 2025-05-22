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
		
		Mazzo mazzo = new Mazzo(false);
        
       Carta pescata = mazzo.pescadalMazzo();
       // System.out.printf("\nHa pescato--> " + pescata);
        mazzo.rivelaCarteMazzo();
        mazzo.svuotaMazzo();
        mazzo.rivelaCarteMazzo();
}

}