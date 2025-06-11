package carte;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import carte.Livello;
import carte.NomeSpeciale;
import componenti.Lato;
import componenti.Merce;

//questo file potrebbe presentare eccezioni non previsti, controllare gli errori dopo averne verificato il corretto funzionamento

public class CaricaCSV {
	public static Map<Integer, Carta> loadMap(String path) throws IOException { // ?
	    Map<Integer, Carta> mazzo = new HashMap<>();
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	        br.readLine();
	        br.readLine();
	        String riga="-1";
	        while ((riga = br.readLine()) != null) {
	            String[] p = riga.split(",", 9);
	            
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
	            	carta = new SpazioAperto(id, nome, livello, ggVolo, merce, equipaggio, credito);
                    break;
	            case PIANETI:
	            	 String raw = p[8].trim(); // parte di codice che ho dovuto cercare.
	            	 raw = raw.replace("\"", "");
                     List<Pianeta> pianeti = new ArrayList<>();
                     for (String pianetaStr : raw.split(";")) {
                         String[] coppie = pianetaStr.split(",");
                         Map<Merce,Integer> mappa = new EnumMap<>(Merce.class);
                         for (String coppia : coppie) {
                             String[] kv = coppia.split(":");
                             Merce tipo = Merce.valueOf(kv[0]);
                             int qta    = Integer.parseInt(kv[1].trim());
                             mappa.put(tipo, qta);
                         }// cercata fino a qua
                         pianeti.add(new Pianeta(mappa));
                     }
                     carta = new Pianeti(id, nome, livello, ggVolo, merce, equipaggio, credito, pianeti);
                     break;
	            case STAZIONE_ABBANDONATA:
	            	String raw2 = p[8];
	            	raw2 = raw2.replace("\"", "");
	            	List<Pianeta> merceAbordo = new ArrayList<>();
	            	String[] coppie = raw2.split(",");
	            	Map<Merce,Integer> mappa = new EnumMap<>(Merce.class);
	            	for (String coppia : coppie) {
	            		String[] kv = coppia.split(":");
	            		Merce tipo = Merce.valueOf(kv[0]);
	            		int qta = Integer.parseInt(kv[1].trim());
	            		mappa.put(tipo, qta);
	            	}
	            	merceAbordo.add(new Pianeta(mappa));
	            	carta = new StazioneAbbandonata(id, nome, livello, credito, credito, credito, credito, merceAbordo);
	            	break;
	            case SCHIAVISTI:
	            	carta = new Schiavista(id, nome, livello, ggVolo, merce, equipaggio, credito);
                    break;
	            case NAVE_ABBANDONATA: 
	            	carta = new NaveAbbandonata(id, nome, livello, ggVolo, merce, equipaggio, credito);
                    break;
	            case POLVERE_STELLARE:
	            	carta = new PolvereStellare(id, nome, livello, ggVolo, merce, equipaggio, credito);
	            	break;
	            case SABOTAGGIO:
	            	carta = new Sabotaggio(id, nome, livello, ggVolo, merce, equipaggio, credito);
	            	break;
	/*            case EPIDEMIA:
	            	carta = new Epidemia(id, nome, livello, ggVolo, merce, equipaggio, credito);
	            	break;
	*/
	            case PIOGGIA_METEORITI:      	
	            	String raw3 = p[8];
	            	raw3 = raw3.replace("\"", "");
	            	List<Meteorite> meteora = new ArrayList<Meteorite>(); // Da Java 7+ List<Meteorite> meteora = new ArrayList<>(); equivale a ciò che ho gia scritto, viene dato per scontato
	            	String[] coppie2 = raw3.split(",");
	            	for (String coppia : coppie2) {
	            		String[] kv = coppia.split(":");
	            		Dimensione dimensione = Dimensione.valueOf(kv[0]);
	            		Lato lato = Lato.valueOf(kv[1]);
	            		meteora.add(new Meteorite(dimensione, lato));
	            	}
	            	carta = new PioggiaMeteoriti(id, nome, livello, credito, credito, credito, credito, meteora);
	            	break;
               default:
                     carta = new Carta(id, effetto, nome , livello, ggVolo, merce, equipaggio, credito);
	            }
	            
	            mazzo.put(id, carta);
	        }
        }
	  catch (FileNotFoundException e) {
	        System.err.println("Non trovo il file, hai toccato il codice?");
	        return null;
	    }
       return mazzo;
    }
	
}
