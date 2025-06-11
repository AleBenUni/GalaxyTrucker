package carte;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import componenti.Componente;
import componenti.Lato;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;
import galaxyTrucker.Posizione;

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
	void attivaCarta(Gioco flotta) {
	    List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    Random rand = new Random();

	    for (Meteorite m : meteoriti) {

	        int tiro = rand.nextInt(6) + 1 + rand.nextInt(6) + 1;
	        System.out.println("Lancio dei dadi per meteorite " + m + ", risultato: " + tiro);


	        for (int i = 0; i < flotta.getNGiocatori(); i++) {
	            Nave nave = ordinate.get(i);

	            System.out.println("Giocatore " + nave.getColor()+ ", vuoi difenderti con uno scudo?");
	            System.out.print("Scelta (0=No, 1=Si): ");
	            int scelta = scanner.nextInt();


	            if (scelta == 1 ) {

	                if (nave.isLatoProtetto(m.getLato()) && m.getDimensione().equals(Dimensione.piccolo)) {

	                    if (nave.getEnergiaNave() >= 1) {
	                        nave.minusEnergiaNave(1);
	                        System.out.println("Giocatore " + nave.getColor() + ", difesa riuscita con lo scudo!");
	                    } else {
	                        System.out.println("Giocatore " + nave.getColor() + ", energia insufficiente per lo scudo.");

	                        if (nave.isLatoProtetto(m.getLato(), tiro)) {
	                            System.out.println("Che fortuna: avete il cannone a portata. Usarlo?");
	                            System.out.print("Scelta (0=No, 1=Si): ");
	                            scelta = scanner.nextInt();
	                            if (scelta == 1) {
	                                System.out.println("Ma sei serio? Senza energia non puoi neanche sparare!");
	                            }
	                        }
	                    }
	                } else if (nave.isLatoProtetto(m.getLato(), tiro) && m.getDimensione().equals(Dimensione.grande)) {
	                
	                	 System.out.println("Che fortuna: avete il cannone a portata. Usarlo?");
		                    System.out.print("Scelta (0=No, 1=Si): ");
		                    scelta = scanner.nextInt();
		                    if (scelta == 1) 
		                        if (nave.getEnergiaNave() >= 1) {
		                            nave.minusEnergiaNave(1);
		                            System.out.println("Giocatore " + nave.getColor()
		                                + ", difesa riuscita con il cannone!");
		                        } else {
		                            System.out.println("Giocatore " + nave.getColor()
		                                + ", mi dispiace, non hai energia per difenderti.");
		                        }
	                }

	            } else if (scelta == 0) {
	                if (nave.isLatoProtetto(m.getLato(), tiro)) {
	                    System.out.println("Che fortuna: avete il cannone a portata. Usarlo?");
	                    System.out.print("Scelta (0=No, 1=Si): ");
	                    scelta = scanner.nextInt();
	                    if (scelta == 1) {
	                        if (nave.getEnergiaNave() >= 1) {
	                            nave.minusEnergiaNave(1);
	                            System.out.println("Giocatore " + nave.getColor()
	                                + ", difesa riuscita con il cannone!");
	                        } else {
	                            System.out.println("Giocatore " + nave.getColor()
	                                + ", mi dispiace, non hai energia per difenderti.");
	                        }
	                    }
	                }
	            }

	        }
	    }

	    scanner.close();
	}
}