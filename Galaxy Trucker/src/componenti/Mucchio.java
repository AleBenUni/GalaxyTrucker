package componenti;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Mucchio {
	List<Componente> mucchio;
	
	public Mucchio() {
		 mucchio=new LinkedList<>();
		
	}
	
	public void riempiMucchio() {
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> jsonDataList = objectMapper.readValue(
                    new File("src/componenti/Componenti.json"),
                    new TypeReference<List<Map<String, Object>>>() {}
            );
            String conUpStr;
            String conDxStr;
            String conSxStr;
            String conDwStr;
            Integer capacita;
            boolean speciale;

            for (Map<String, Object> itemData : jsonDataList) {
                String tipo = (String) itemData.get("Tipo");
                if (tipo == null) {
                    System.err.println("Oggetto JSON senza 'Tipo', saltato: " + itemData);
                    continue;
                }

                conUpStr = (String) itemData.get("ConnettoreUp");
                conDxStr = (String) itemData.get("ConnettoreDx");
                conSxStr = (String) itemData.get("ConnettoreSx");
                conDwStr = (String) itemData.get("ConnettoreDw");

                if (conUpStr == null || conDxStr == null || conSxStr == null || conDwStr == null) {
                    System.err.println("Oggetto JSON con campi obbligatori mancanti per Tipo '" + tipo + "', saltato: " + itemData);
                    continue;
                }
                

                Connettore connettoreUp = Connettore.valueOf(conUpStr.toLowerCase());
                Connettore connettoreDx = Connettore.valueOf(conDxStr.toLowerCase());
                Connettore connettoreSx = Connettore.valueOf(conSxStr.toLowerCase());
                Connettore connettoreDw = Connettore.valueOf(conDwStr.toLowerCase());

                String imagePath = (String) itemData.get("imagePath");

                Componente componenteCreato = null;

                switch (tipo) {
                    case "Batteria":
                    	capacita = (int) itemData.get("Capacita");
                        componenteCreato = new Batteria(capacita, connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    case "Cannone":
                    	capacita = (int) itemData.get("Capacita");
                        componenteCreato = new Cannone(capacita, connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    case "Scudo":
                        componenteCreato = new Scudo(connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    case "Modulo Strutturale":
                        componenteCreato = new ModuloStrutturale(connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    case "Motore":
                    	capacita = (int) itemData.get("Capacita");
                        componenteCreato = new Motore(capacita, connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    case "Cabina":
                    	capacita = (int) itemData.get("Capacita");
                         componenteCreato = new Cabina(capacita, connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                         break;
                    case "Stiva":
                        speciale = false;
                        capacita = (int) itemData.get("Capacita");
                        if (itemData.containsKey("Speciale") && itemData.get("Speciale") != null) {
                            speciale = (Boolean) itemData.get("Speciale");
                        }
                        componenteCreato = new Stiva(capacita, speciale, connettoreUp, connettoreDx, connettoreSx, connettoreDw, imagePath);
                        break;
                    default:
                        System.err.println("Tipo di componente sconosciuto nel file: " + tipo + ". Oggetto: " + itemData);
                        break;
                }

                if (componenteCreato != null) {
                    this.mucchio.add(componenteCreato);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura o il parsing del file JSON '" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) { 
            System.err.println("Errore: valore non valido per Enum Connettore o altro argomento illegale. Controlla i valori nel JSON. " + e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) { 
            System.err.println("Errore: tipo di dato inatteso nel JSON (es. Capacita non è un numero intero, Speciale non è booleano). " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { 
             System.err.println("Errore imprevisto durante l'elaborazione di un oggetto JSON: " + e.getMessage());
             e.printStackTrace();
        }
    }

    public List<Componente> getMucchio() {
        return mucchio;
    }
    
    public void shuffle(List<Componente> mucchio) {
    	Collections.shuffle(mucchio);
    }
    
    public void add(Componente componente) {
    	mucchio.add(componente);
    	shuffle(mucchio);
    }
    
    public void removeComponenteAt(int indice) {
    	if(indice>=0&&indice<mucchio.size())
    		mucchio.remove(indice);
    }
    
    public Componente pesca() {
    	if(!mucchio.isEmpty()) {
    		return ((LinkedList<Componente>)this.mucchio).removeFirst();
    	}
    	return null;
    }
    
    public int dimensione() {
    	return mucchio.size();
    }
    
    public boolean isEmpty() {
    	return mucchio.isEmpty();
    }
    
    public Componente getComponenteAt(int indice) {
        if(indice>=0&&indice<mucchio.size())
            return mucchio.get(indice);
        else
            return null;
    }
}
