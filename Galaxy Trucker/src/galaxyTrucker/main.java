package galaxyTrucker;
import carte.Carte;
import carte.Livello;

public class main {

	//main di prova, creato per verificare la funzionalita di carta, modificalo senza problemi
	
	public static void main(String[] args) {
	        
	        Carte Uno = new Carte(Livello.I, 5, 0, 100);
	        
	        System.out.println("Oggetto Carte creato con successo.");
	        
	        System.out.println(Uno);
	}

}