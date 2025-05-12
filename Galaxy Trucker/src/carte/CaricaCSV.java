package carte;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
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
	            
	            Carta carta;
	            switch(effetto) {
	            case SPAZIO_APERTO:
	            	carta = new SpazioAperto(nome, livello, ggVolo, merce, equipaggio, credito);
                    break;
	            case PIANETI:
	            	 String raw = p[8].trim(); // parte di codice che ho dovuto cercare.
                     if (raw.startsWith("\"") && raw.endsWith("\"")) {
                         raw = raw.substring(1, raw.length() - 1);
                     } 
                     List<Pianeta> pianeti = new ArrayList<>();
                     for (String pianetaStr : raw.split(";")) {
                         String[] coppie = pianetaStr.split(",");
                         Map<Merce,Integer> mappa = new EnumMap<>(Merce.class);
                         for (String coppia : coppie) {
                             String[] kv = coppia.split(":");
                             Merce tipo = Merce.valueOf(kv[0].trim().toLowerCase());
                             int qta    = Integer.parseInt(kv[1].trim());
                             mappa.put(tipo, qta);
                         }// cercata fino a qua
                         pianeti.add(new Pianeta(mappa));
                     }
                     carta = new Pianeti(nome, livello,
                                         ggVolo, merce, equipaggio, credito,
                                         pianeti);
                     
                    default:
                     carta = new Carta(effetto, nome , livello, ggVolo, merce, equipaggio, credito);
	            }
	            
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
