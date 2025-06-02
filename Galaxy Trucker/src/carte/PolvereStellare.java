package carte;

import java.util.Comparator;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;
import galaxyTrucker.Posizione;
import componenti.Componente;
import componenti.Lato;

public class PolvereStellare extends Carta{

	public PolvereStellare(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.POLVERE_STELLARE, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	public void appliaEffetto(Gioco gioco) {
		List<Posizione> posizioni = gioco.getPosizioniGiocatori();
		
		posizioni.sort(Comparator.comparingInt(posizione::getCasella).reversed());
		
		for (Posizione p : posizioni) {
			Nave nave = p.giocatore().getNave();
			int connettoriEsposti = contaConnettoriEsposi(nave);
			System.out.println(p.getGiocatore().getNome + " ha " + connettoriEsposti + " conettori esposti.");
			gioco.arretraGiocatore(p, connettoriEsposti);
		}
 	}
	
	private int contaConnettoriEsposti(Nave nave) {
		int count = 0;
		for (Componente c : nave.getComponenti()) {
			for (Lato lato : Lato.values()) {
				if (c.getConnettore(lato) != null && !c.getConnettore(lato).èconnesso()) {
					count++;
				}
			}
		}
		return count;
	}
	
	@Override
	public String toString() {
		return super.toString();	
	}

}
