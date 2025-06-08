package galaxyTrucker;
import componenti.*;
import carte.*;

public class main {

	public static void main(String[] args) {
       
		//NON TOCCARE
		 String currentVersion = System.getProperty("java.version");
	        String[] versioneAttuale = currentVersion.split("\\.");
	        int versioneUtenteJava = Integer.parseInt(versioneAttuale[0]);
	        
	        if (versioneUtenteJava < 22) {
	            System.err.println("======================================================");
	            System.err.println(" Questo progetto richiede Java 22 o superiore");
	            System.err.println(" La tua versione attuale è: " + currentVersion);
	            System.err.println("\n Per favore scarica l'ultima versione da:");
	            System.err.println(" https://www.oracle.com/java/technologies/downloads/");
	            System.err.println("======================================================");
	            System.err.println(" Per cambiare la versione di Java in Eclipse vai su Window > Preferences > Installed JREs > Seleziona la versione di Java scaricata");
	            System.exit(1);
	        } 
	     // DA QUI IN POI POTETE TOCCARE
	       
	        /*
	        Nave na=new Nave(Livello.I);
	        Mazzo mazzo = new Mazzo(true);
	        mazzo.pescaAttivaEffetto(na);
	        */
	        
		Gioco gioco=new Gioco(1,Livello.I);
		gioco.getMucchio();
		
	        
		Mazzo mazzo = new Mazzo(false);
		mazzo.rivelaCarteMazzo();
		Interfaccia.main(args);
		
}

}