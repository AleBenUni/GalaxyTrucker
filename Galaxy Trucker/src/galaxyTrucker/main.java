package galaxyTrucker;
import componenti.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

import carte.*;

public class main {

	public static void main(String[] args) {
       
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
	          
	      
	    
		/*
		Gioco gioco=new Gioco(1,Livello.I);
		gioco.getMucchio();
		*/
		Mazzo mazzo = new Mazzo(false);
		mazzo.rivelaCarteMazzo();
		
		setupJavaFX();
		Interfaccia.main(args); // Carica il main di un altra classe, una novità che non conoscevo.
}
	private static void setupJavaFX() { //cercata
        // Risolve i warning di accesso nativo
        System.setProperty("javafx.allow.anonymous.module", "true");
        
        // Forza il software rendering se necessario
        if (System.getProperty("prism.order") == null) {
            System.setProperty("prism.order", "sw");
        }
        
        // Configura il classpath per JavaFX
        try {
            File libDir = new File("lib");
            URL[] urls = new URL[Objects.requireNonNull(libDir.listFiles()).length];
            int i = 0;
            for (File file : libDir.listFiles()) {
                urls[i++] = file.toURI().toURL();
            }
            
            ClassLoader fxLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            Thread.currentThread().setContextClassLoader(fxLoader);
            
        } catch (Exception e) {
            System.err.println("ERROR: Failed to load JavaFX libraries");
            e.printStackTrace();
            System.exit(1);
        }
    }
}