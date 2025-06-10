package galaxyTrucker;
import carte.Livello;
import componenti.Mucchio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class Gioco {
	
	private Nave[] nave;
	private Plancia plancia;
	private Livello livello=null;
	private int nGiocatori;
	Mucchio mucchio;
	String colori[]= {"red","green","blue","yellow"};
	public Gioco(int nGiocatori, Livello livello) {
		this.nGiocatori=nGiocatori;
		this.livello=livello;
		mucchio=new Mucchio();
		mucchio.riempiMucchio();
		nave=new Nave[nGiocatori];
		plancia=new Plancia(livello);
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello,colori[i]);
	}
	
	
	
	public void gioca() {
		int difesa=0;
		Scanner scanner=new Scanner(System.in);
		do {
			scanner.nextLine();
			System.out.println("Inserire numero giocatori");
			nGiocatori = scanner.nextInt();
		}while(nGiocatori<=1||nGiocatori>4);
		
		do {
			scanner.nextLine();
			System.out.println("Inserire Livello");
			livello=Livello.toLivello(scanner.nextLine());
		}while(livello==null);
		
		System.out.println("GIOCO: Avvio Fase Costruzione Navi (Grafica)...");
        this.mucchio.riempiMucchio();
        CountDownLatch costruzioneCompletataLatch = new CountDownLatch(1);
        if (this.interfacciaCallback != null) {
            // Chiede all'Interfaccia di avviare la sua UI per la costruzione.
            // Questa chiamata deve essere fatta in modo che l'Interfaccia possa
            // eseguire le operazioni UI sul thread JavaFX.
            this.interfacciaCallback.avviaFaseCostruzioneGrafica(
                    this.nGiocatori, // Passa il numero di giocatori
                    this.navi,       // Passa l'array delle navi da costruire
                    this.mucchioComune, // Il mucchio da cui pescare
                    this.plancia,    // La plancia se l'UI della costruzione la mostra
                    costruzioneCompletataLatch // Il latch che l'UI segnalerà
            );

            try {
                System.out.println("GIOCO: In attesa che la fase di costruzione grafica termini...");
                costruzioneCompletataLatch.await(); // Questo thread si blocca qui
                System.out.println("GIOCO: Fase di costruzione grafica terminata (segnale ricevuto).");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("GIOCO: Thread di gioco interrotto mentre attendeva la fine della fase di costruzione UI.");
                return; // Termina il gioco se interrotto
            }
        } else {
            System.err.println("GIOCO: Interfaccia non disponibile! Impossibile avviare la costruzione grafica.");
            // Potresti implementare un fallback a una costruzione console qui se lo desideri
            return; 
        }
		
		int componentiDisponibili;
		while(true) {	//Gestisce turni costruzione navi
			
			//Appertura finestra
			for(int i=0;i<nGiocatori;i++) {
				
				//Visualizza nave
				//gestione della costruzione della nave all'intermo della finestra
				//if Passa turno -> pulisci interfaccia
			}
			
			break;
			
		}
		
		while(true) {	//Gestisce pescaggio carte
			
			//Viene pescata una carta dalla quale posso difendermi e  mi dice dove mi colpirebbe
			int costoDifesa=0;
			for(int i=0;i<nGiocatori;i++) {
				do {
					scanner.nextLine();
					System.out.println("Vuoi difenderti? (0 || 1)");
					difesa = scanner.nextInt();
				}while(difesa<0||difesa>1);
				if(difesa==1) {
					int energiaDisponibile=nave[i].getEnergiaNave();
					if(energiaDisponibile>costoDifesa) {
						//Componente distrutto
						System.out.println("Non hai abbastanza energia per difenderti");
					}
					else
						nave[i].minusEnergiaNave(costoDifesa);
				}
			}
				
			
			break;
		}
		
		
		
		
	}
	
	public Livello getLivello() {
		return livello;
	}
	
	public Nave getNave(int nave) {
		if(nave>=0||nave<nGiocatori)
			return this.nave[nave];
		else
			return null;
	}
	
	public List<Nave> getFlottaNaveOrdinata(){
		Integer[] giorniNavi = new Integer [this.getNGiocatori()];
    	List <Nave> giocatori = new ArrayList<>();
    	for (int i=0; i<this.getNGiocatori(); i++) {
    		giorniNavi[i]=this.getNave(i).getGiorniVolo();
    	}
    	Arrays.sort(giorniNavi, Collections.reverseOrder());
    	for (int i=0; i<this.getNGiocatori(); i++) {
    		for (int j=0; j<this.getNGiocatori(); j++) {
    			if (giorniNavi[j]==this.getNave(i).getGiorniVolo()) {
    				giocatori.add(this.getNave(i));
    			}
    		}
    	}
    	return giocatori;
	}
	
	public Plancia getPlancia() {
		return plancia;
	}
	
	 public Mucchio getMucchio() { return this.mucchio; }
	 
	 public int getNGiocatori() { return this.nGiocatori; }
	
}
