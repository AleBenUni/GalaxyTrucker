package carte;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class NaveAbbandonata extends Carta {
	
	public NaveAbbandonata(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.NAVE_ABBANDONATA, nome, livello, ggVolo, merce, equipaggio, credito);
	}

	@Override
	public String toString() {
		return super.toString(); 
	}
	
	
	@Override
	void attivaCarta(Gioco flotta) {
	    List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    
	    for (int i = 0; i < flotta.getNGiocatori(); i++) {
	    	System.out.println("Giocatore "+ordinate.get(i).getColor()  +" vuoi, in cambio di " + this.getEquipaggio() + " pedine equipaggio e " + this.getGiorniVolo() + " giorni di viaggio in meno, guadagnare " + this.getCredito() + " crediti?");
	        
	        boolean controllo = false;
	        do {
	            System.out.print("Scelta (0=No, 1=Si): ");
	            try {
	                int scelta = scanner.nextInt();
	                scanner.nextLine();
	                
	                if (scelta == 1) {
	                    applicaEffetti(ordinate.get(i));
	                    i = flotta.getNGiocatori();
	                    controllo = true;
	                } else if (scelta == 0) {
	                    controllo = true;
	                } else {
	                    System.out.println("Input non valido! Inserire 0 o 1.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Errore! Inserisci un numero intero (0 o 1).");
	                scanner.nextLine();
	            }
	        } while (!controllo);
	    }
	    scanner.close();
	}
}