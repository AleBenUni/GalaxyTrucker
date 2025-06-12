package carte;

import java.util.List;
import java.util.Scanner;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class SpazioAperto extends Carta{

	public SpazioAperto(Integer id, String nome, Livello livello, int ggVolo, int merce, int equipaggio,
			int credito) {
		super(id, NomeSpeciale.SPAZIO_APERTO, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
	    return super.toString() + ". Al momento ti potresti spostare di " + ggVolo + " giorni, contando la tua potenza motrice";
	}
	
	@Override 
	 void attivaCarta(Gioco flotta) { 
		System.out.println("Spazio Aperto: \n");
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    
	   for (int i = 0; i < flotta.getNGiocatori(); i++) {
		   Nave nave = ordinate.get(i);
		   nave.setGiorniVolo(nave.getGiorniVolo() + nave.getPotenzaMotrice());
		   System.out.println("Giocatore: " + nave.getColor() + " ti sei spostato di " + (nave.getGiorniVolo() + nave.getPotenzaMotrice()) + " giorni in avanti.\n");
	   }
	}
	

}
