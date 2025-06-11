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
	        int scelta;
	        while (true) {
	            System.out.print(this.toString() + "\nGiocatore " + nave.getColor()
	                + ", vuoi atterrare su un pianeta e perdere " + this.getGiorniVolo() + " giorni? (0=No, 1=Si): ");
	            try {
	                scelta = Integer.parseInt(scanner.nextLine().trim());
	                if (scelta == 0 || scelta == 1) break;
	            } catch (NumberFormatException e) { }
	            System.out.println("Input non valido! Inserisci 0 o 1.");
	        }
	        if (scelta == 1 && !pianeti.isEmpty()) {
	            nave.minusGiorniVolo(this.getGiorniVolo());
	            int merceCarta = this.getMerce();
	            if (merceCarta < 0) {
	                int daPerdere = -merceCarta;
	                for (int k = 0; k < daPerdere; k++) {
	                    if (!nave.minusStiva()) break;
	                }
	            }
	            Pianeta pianetaScelto;
	            while (true) {
	                System.out.print("Scegli un pianeta (1–" + pianeti.size() + "): ");
	                String line = scanner.nextLine().trim();
	                try {
	                    int idx = Integer.parseInt(line) - 1;
	                    if (idx >= 0 && idx < pianeti.size()) {
	                        pianetaScelto = pianeti.remove(idx);
	                        break;
	                    }
	                } catch (NumberFormatException e) { }
	                System.out.println("Indice non valido! Inserisci un numero tra 1 e " + pianeti.size() + ".");
	            }
	            for (Map.Entry<Merce, Integer> e : pianetaScelto.getMercexPianeta().entrySet()) {
	                Merce tipo = e.getKey();
	                int quantita = e.getValue();
	                int caricate = 0;
	                for (int q = 0; q < quantita; q++) {
	                    if (nave.setStiva(tipo)) {
	                        caricate++;
	                    } else {
	                        System.out.println("Caricate " + caricate + " di " + quantita
	                            + " merci " + tipo + " sulla nave " + nave.getColor() + ".");
	                        break;
	                    }
	                }
	                if (caricate == quantita) {
	                    System.out.println("Caricate tutte e " + quantita
	                        + " merci " + tipo + " sulla nave " + nave.getColor() + ".");
	                }
	            }
	        }
	    }

	    scanner.close();
	}



}
