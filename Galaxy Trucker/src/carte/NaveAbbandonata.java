package carte;

import java.util.Scanner;

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
	void attivaCarta(Nave naveLeader) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Giocatore"+naveLeader +"vuoi in cambio di " + this.getEquipaggio() + " pedine equipagio e " + this.getGiorniVolo() + " di viaggio in meno guadagnare " + this.getCredito() + " crediti?");
		int scelta = scanner.nextInt();
		if (scelta == 0) {
			super.attivaCarta(naveLeader);
		} else {
			//Si passa al prossimo giocatore dopo il Leader, calcolarlo dai giorni di volo che una nave possiede fino al quarto
		}
		scanner.close();
	}
	
}
