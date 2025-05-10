package carte;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import carte.Livello;
import carte.NomeSpeciale;

//questo file potrebbe presentare eccezioni non previsti, controllare gli errori dopo averne verificato il corretto funzionamento

public class CaricaCSV {
	public static Map<Integer, Carta> loadMap(String path) throws IOException {
	    Map<Integer, Carta> mazzo = new HashMap<>(); // int non funziona, sostituita con Integer
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	        br.readLine();
	        String riga="-1";
	        while ((riga = br.readLine()) != null) {
	            String[] p = riga.split(",", -1);
	            
	            int id = Integer.parseInt(p[0]);
	            String nome = p[1];
	            Livello livello = Livello.valueOf(p[2]); //valueOf converte stringhe in enum 
	            int ggVolo = Integer.parseInt(p[3]);
	            int merce = Integer.parseInt(p[4]);
	            int equipaggio = Integer.parseInt(p[5]);
	            int credito = Integer.parseInt(p[6]);
	            NomeSpeciale effetto = NomeSpeciale.valueOf(p[7]);
	            
	            Carta carta = new Carta(effetto, nome , livello, ggVolo, merce, equipaggio, credito);
	            
	            mazzo.put(id, carta);
	        }
        }
	 /*   catch (NumberFormatException e) {
	        System.err.println("La riga: " + riga + "presenta problemi. Sicuro di aver messo la virgola nel file carte.csv?");
	        continue;
	    }
     */   return mazzo;
    }
	
}
