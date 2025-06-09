package carte;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import galaxyTrucker.Nave;

public class PioggiaMeteoriti extends Carta{
	private final List<Meteorite> meteoriti;

	public PioggiaMeteoriti(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito, List<Meteorite> meteoriti) {
		super(id, NomeSpeciale.PIOGGIA_METEORITI , nome, livello, ggVolo, merce, equipaggio, credito);
		this.meteoriti=meteoriti;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Che prevede (in ordine di arrivo) " + meteoriti;
	}
	
	@Override
	void attivaCarta(Nave naveLeader) {
		//Avviene il lancio casuale dei dadi. Ogni giocatore viene colpito dal meteorite. Vede se si possono difendere tutti e poi si passa al prossimo meteorite
		
		//Al posto di passare il Leader servirebbe passare l'intera flotta
		 Random rand = new Random();
		 for (Meteorite m : meteoriti) {
	            int tiro = rand.nextInt(13);
	            System.out.println("Lancio del dado per meteorite " + m + ": " + tiro);

	          //  for (Nave nave : naveLeader) {
	                //Controllo se il lato della nave è liscia, altrimenti passa alla difesa
	            	//se non is può difendere o si rifiuta subisce il danno e perde il componente
	                
	        //    }
	        }
	}
	
}
