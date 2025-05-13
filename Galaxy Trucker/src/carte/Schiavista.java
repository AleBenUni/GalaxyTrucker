package carte;

public class Schiavista extends Carta{

	int cannonateRichieste;
	public Schiavista(String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		super(NomeSpeciale.SCHIAVISTI, nome, livello, ggVolo, merce, equipaggio, credito);
		this.cannonateRichieste=ggVolo;
		setMerce(0);
	}
	
}
