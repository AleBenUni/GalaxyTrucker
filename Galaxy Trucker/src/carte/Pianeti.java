package carte;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import componenti.Merce;
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
	    Scanner scanner = new Scanner(System.in);

	    for (int i = 0; i < flotta.getNGiocatori(); i++) {
	        Nave nave = ordinate.get(i);
	        System.out.print("Giocatore " + nave.getColor() +
	            ", vuoi atterrare su un pianeta e perdere " + this.getGiorniVolo() + " giorni? (0=No, 1=Si): ");
	        int scelta = scanner.nextInt();
	        if (scelta == 1 && !pianeti.isEmpty()) {
	            nave.addGiorniVolo(-this.getGiorniVolo());

	            int merceDaPerdere = Math.abs(this.getMerce());
	            for (int k = 0; k < merceDaPerdere; k++) {
	                if (!nave.minusStiva()) break;
	            }

	            Pianeta pianetaScelto;
	            do {
	                System.out.print("Scegli un pianeta (1–" + pianeti.size() + "): ");
	                int idx = scanner.nextInt() - 1;
	                if (idx >= 0 && idx < pianeti.size()) {
	                    pianetaScelto = pianeti.remove(idx);
	                    break;
	                }
	                System.out.println("Indice non valido.");
	            } while (true);

	            Map<Merce, Integer> risorse = pianetaScelto.getMercexPianeta();
	            for (Map.Entry<Merce, Integer> e : risorse.entrySet()) {
	                Merce tipo = e.getKey();
	                int quantita = e.getValue();
	                for (int q = 0; q < quantita; q++) {
	                    if (!nave.setStiva(tipo)) break;
	                }
	            }
	        }
	    }

	    scanner.close();
	}
}
