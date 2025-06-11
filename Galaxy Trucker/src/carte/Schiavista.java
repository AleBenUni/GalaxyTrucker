package carte;

import java.util.List;
import java.util.Scanner;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class Schiavista extends Carta{
	//i cannoni per questo file sono merce, che verra settato a 0 (parlo di merce)
	int cannonateRichieste;
	public Schiavista(Integer id, String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		super(id, NomeSpeciale.SCHIAVISTI, nome, livello, ggVolo, merce, equipaggio, credito);
		this.cannonateRichieste=merce;
		setMerce(0);
	}
	public int getCannonateRichieste() {
		return cannonateRichieste;
	}
	public void setCannonateRichieste(int cannonateRichieste) {
		this.cannonateRichieste = cannonateRichieste;
	}
	
	@Override
	public String toString() {
		return " Per difenderti avrai bisogno di almeno " + getCannonateRichieste() + " o più cannoni. " + getEquipaggio() + " membri del tuo equipaggio saranno coinvolti in questo attacco";
	}
	
	@Override 
	 void attivaCarta(Gioco flotta) { 
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    System.out.print(toString() + "\nSarete attaccati in ordine di rotta, vittoria o sconfitta saranno automatici. (Una situazione in cui essere primi non giova sulla propria nave\n");
	    for (int i = 0; i < flotta.getNGiocatori(); i++) {
	    	//Mi serve conoscere la forza totle dei cannoni installati su una nave
	    	System.out.print("Player: " + ordinate.get(i).getColor() + "" ); 
	    }
	}
	
}


