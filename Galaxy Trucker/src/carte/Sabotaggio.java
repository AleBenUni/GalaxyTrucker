package carte;

import java.util.List;
import java.util.Random;

import componenti.Componente;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;
import galaxyTrucker.Posizione;

public class Sabotaggio extends Carta {

	public Sabotaggio(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.SABOTAGGIO, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	 void attivaCarta(Gioco flotta) { 
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
    	Nave naveLeader = ordinate.get(0);
    	int equipNave = 3000;
    	int naveBersaglio=0;
    	Random rand = new Random();
    	int tiroRiga =0;
    	int tiroColonna =0;
    	
    	
    	for (int i = 0; i < flotta.getNGiocatori(); i++) {
    		naveLeader = ordinate.get(0);
    		if (naveLeader.getEquipaggioABordo() < equipNave) {
    			equipNave = naveLeader.getEquipaggioABordo() ;
    			naveBersaglio=i;
    		}
    	}
    	
    	int i=0;
    	for (i=0; i<3; i++) {
    	System.out.print("Player: " + ordinate.get(naveBersaglio).getColor() 
    			+ " sei sfortunato. Riceverai un colpo casuale da cui non ti puoi difendere\n" ); 
    	tiroRiga = rand.nextInt(6) + 1 + rand.nextInt(6) + 1 - 3; 
    	tiroColonna = rand.nextInt(6) + 1 + rand.nextInt(6) + 1 - 3; 
    	Componente comp = naveLeader.getCella(new Posizione(tiroRiga, tiroColonna)).getComponente();
    	if (comp != null ) {
    		naveLeader.eliminaComponente(tiroRiga, tiroColonna);
    		i = 4;
    		System.out.println("Meteorite innarestabile distrugge (" + tiroRiga + "," + tiroColonna + ").\n");
    	}
    	}
    	if (i!=4) {
    		System.out.println("Falso allarme, non sembrano arrivare colpi alla tua nave Player" + ordinate.get(naveBersaglio).getColor() + "\n");
    	}
    	
    	
	}
	
}
