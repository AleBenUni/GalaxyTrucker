package carte;

import java.util.List;
import java.util.Scanner;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class Pianeti extends Carta{
	private final List<Pianeta> pianeti;
	
	public Pianeti(Integer id, String nome, Livello livello, int ggVolo, int merce, int equipaggio,
			int credito, List<Pianeta> pianeti) {
		super(id, NomeSpeciale.PIANETI, nome, livello, ggVolo, merce, equipaggio, credito);
		this.pianeti=pianeti;
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	@Override
	public String toString() {
		return " Ci sono " + pianeti.size() + " pianeti. Abbiamo " + pianeti + "]";
	}
	
	@Override
	void attivaCarta(Gioco flotta) {
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
    	
		Scanner scanner=new Scanner(System.in);
		int scelta = scanner.nextInt();
		for (int i=0; i<flotta.getNGiocatori(); i++) {
			System.out.println("Giocatore"+ordinate.get(i) +"vuoi atterare su un pianeta e perdere così " + this.getGiorniVolo() + " giorni di viaggio ");
			 System.out.print("Scelta (0=No, 1=Si): ");
			 if (scelta == 0) {
					System.out.print("Scegli un pianeta (1,2,3,4: ");
					int sceltaPianeta = scanner.nextInt() - 1;
					//La merce viene caricata a bordo, manca la possibilità di vedere se il giocatore può portare a bordo della merce
					//Se il giocatore può portare a bordo la merce il pianeta viene disintegrato (modo carino per dire che viene eliminata la scelta)
					Pianeta pianetaScelto = pianeti.remove(sceltaPianeta);
		} 
		scanner.close();
		}
	}
}
