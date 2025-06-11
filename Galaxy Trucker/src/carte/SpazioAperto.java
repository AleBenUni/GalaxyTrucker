package carte;

import java.util.List;

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
		
	}
	

}
