package carte;

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

		
		Scanner scanner=new Scanner(System.in);
		
		int scelta = scanner.nextInt();
		for (int i=0; i<flotta.getNGiocatori(); i++) {
			System.out.println("Giocatore"+ordinate.get(i)  +"vuoi, in cambio di " + this.getEquipaggio() + " pedine equipaggio e " + this.getGiorniVolo() + " giorni di viaggio in meno, guadagnare " + this.getCredito() + " crediti?");
			 System.out.print("Scelta (0=No, 1=Si): ");
			 if (scelta == 0) {
				 applicaEffetti(ordinate.get(i));
				 i=flotta.getNGiocatori();
		} 
		scanner.close();
	}
	}
	
}
