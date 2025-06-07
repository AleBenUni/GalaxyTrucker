package galaxyTrucker;
import componenti.*;
import carte.*;

public class main {

	public static void main(String[] args) {
       
		String currentVersion = System.getProperty("java.version");
		String[] versioneAttuale = currentVersion.split("\\.");
		int versioneUtenteJava = Integer.parseInt(versioneAttuale[0]);
		
	    if (versioneUtenteJava<22) {
	        
	        System.err.println("======================================================");
	        System.err.println(" Questo progetto richiede Java 22 o superiore");
	        System.err.println(" La tua versione attuale è: " + currentVersion);
	        System.err.println("\n Per favore scarica l'ultima versione da:");
	        System.err.println(" https://www.oracle.com/java/technologies/downloads/");
	        System.err.println("======================================================");
	        
	    }
		/*
		Gioco gioco=new Gioco(1,Livello.I);
		gioco.getMucchio();
		*/
		Mazzo mazzo = new Mazzo(false);
		mazzo.rivelaCarteMazzo();
}

}