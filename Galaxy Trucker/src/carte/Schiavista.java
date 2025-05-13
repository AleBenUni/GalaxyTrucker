package carte;

public class Schiavista extends Carta{

	
	//i cannoni per questo file sono merce, che verra settato a 0 (parlo di merce)
	int cannonateRichieste;
	public Schiavista(String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		super(NomeSpeciale.SCHIAVISTI, nome, livello, ggVolo, merce, equipaggio, credito);
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
		return " Per difenderti avrai bisogno di almeno " + getCannonateRichieste() + " o più cannoni. " + getMerce() + " merce sara' coinvolta in questo attacco";
	}
	
	
	
}
