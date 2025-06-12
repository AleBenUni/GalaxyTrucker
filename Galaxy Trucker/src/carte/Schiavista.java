package carte;

import java.util.List;
import java.util.Scanner;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class Schiavista extends Carta{
	//i cannoni per questo file sono merce, che verra settato a 0 (parlo di merce)
	int cannonateRichieste;
	public Schiavista(Integer id, String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		super(id, NomeSpeciale.SCHIAVISTI, nome, livello, ggVolo, merce, equipaggio, credito);
		this.cannonateRichieste=merce;
		setMerce(0);
	}
	public int getCannonateRichieste() {
		return cannonateRichieste;
	}
	public void setCannonateRichieste(int cannonateRichieste) {
		this.cannonateRichieste = cannonateRichieste;
	}
	
	@Override
	public String toString() {
		return " Per difenderti avrai bisogno di almeno " + getCannonateRichieste() + " o più cannoni. " + getEquipaggio() + " membri del tuo equipaggio saranno coinvolti in questo attacco";
	}
	
	@Override 
	void attivaCarta(Gioco flotta) { 
	    List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println(toString() + 
	        "\nSarete attaccati in ordine di rotta. Sconfiggere il nemico vi darà crediti ma vi rallenterà. Rinunciare vi fa evitare lo scontro, ma niente ricompensa.\n");

	    for (int i = 0; i < flotta.getNGiocatori(); i++) {
	        Nave nave = ordinate.get(i);
	        System.out.println("È il turno di: " + nave.getColor());

	        if (nave.getPotenzaFuoco() >= this.cannonateRichieste) {
	            System.out.println("Hai abbastanza potenza di fuoco (" + nave.getPotenzaFuoco() + 
	                ") per combattere. Vuoi affrontare il nemico? (s/n)");
	            String scelta = scanner.nextLine();

	            if (scelta.equalsIgnoreCase("s")) { // volevo testare una versione con la lettera e non il numero
	                System.out.println("Hai sconfitto il nemico! Guadagni " + this.getCredito() + " crediti.");
	                nave.setCreditoVolo(nave.getCreditoVolo()+this.getCredito());
	                nave.minusGiorniVolo(this.getGiorniVolo());
	            } else {
	                System.out.println("Hai scelto di non combattere. Non ottieni nulla, ma non perdi tempo.");
	            }
	        } else {
	            System.out.println("Non hai abbastanza potenza di fuoco per combattere (" + nave.getPotenzaFuoco() + 
	                "). Gli Schiavisti ti costringono a rinunciare a parte del tuo equipaggio.");

	            nave.setEquipaggioABordo(nave.getEquipaggioABordo()-this.getEquipaggio());
	        }

	        System.out.println();
	    }
	}

}


