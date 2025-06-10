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
		//Avviene il lancio casuale dei dadi. Ogni giocatore viene colpito dal meteorite. Vede se si possono difendere tutti e poi si passa al prossimo meteorite
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
		Scanner scanner=new Scanner(System.in);
		Componente comp;
			            		
		Random rand = new Random();
		 for (Meteorite m : meteoriti) {
				 int tiro = rand.nextInt(6) + rand.nextInt(6) + 2; //Magari mi sbaglio, ma rand.nextInt(12)+2; non lo posso fare perchè non simula la reale probabilità dei dadi
	            System.out.println("Lancio dei dadi per meteorite " + m + ", risultato: " + tiro);
	            for (int i=0; i<flotta.getNGiocatori(); i++) {
	            	System.out.println("Giocatore"+ordinate.get(i)  + " vuoi difenderti con uno scudo?");
	            	 System.out.print("Scelta (0=No, 1=Si): ");
	            	 int scelta = scanner.nextInt();
	    			 
	            	if (m.getLato()==Lato.up) {
	            		if (scelta == 1) {
	    				 //controllo se si può effettivamente difendere in quel lato con uno scudo
	            		if (ordinate.get(i).isLatoProtetto(m.getLato())==true && m.getDimensione().equals(Dimensione.piccolo)) {
	            			if (ordinate.get(i).getEnergiaNave()>=1) {
	            				ordinate.get(i).minusEnergiaNave(1);
	            				System.out.println("Giocatore "+ordinate.get(i)  + " hai eseguito una difesa con i baffi!");
	            			}
	            			else {
	            				System.out.println("Giocatore "+ordinate.get(i)  + " hai scordato di non poter usare scudi per questo attacco");
	            				if (ordinate.get(i).isLatoProtetto(m.getLato(), tiro)) {
	            					System.out.println("Che ciurma fortunata "+ ordinate.get(i)  + " avete un cannone a portata di tiro. Utilizzarlo?");
	            					 System.out.print("Scelta (0=No, 1=Si): ");
	            	            	 scelta = scanner.nextInt();
	            	            	 if (scelta == 1) {
	            	            		 System.out.println("Giocatore "+ordinate.get(i)  + " ma sei serio?! Non hai energie per attivare uno scudo potrai mai attivare un cannone!?");
	            	            	 }
	            				}
	            			}
	            		}
	    			 } else if (scelta==0 ){
	    				 if (ordinate.get(i).isLatoProtetto(m.getLato(), tiro)) {
         					System.out.println("Che ciurma fortunata "+ ordinate.get(i)  + " avete un cannone a portata di tiro. Utilizzarlo?");
         					 System.out.print("Scelta (0=No, 1=Si): ");
        	            	 scelta = scanner.nextInt();
        	            	 if (scelta == 1 ) {
        	            		 if (ordinate.get(i).getEnergiaNave()>=1) {
     	            				ordinate.get(i).minusEnergiaNave(1);
     	            				System.out.println("Giocatore "+ordinate.get(i)  + " hai eseguito una difesa con i baffi!");
     	            			} else {
     	            				System.out.println("Giocatore "+ordinate.get(i)  + " mi dispiace, non ti puoi difendere");
     	            			}
        	            	 }
	    				 }
	    				 
	    			 }
	            		ordinate.get(i).getCella(new Posizione(0,tiro));
	            	}
	            	
	            }
	        }
	}
	
}
