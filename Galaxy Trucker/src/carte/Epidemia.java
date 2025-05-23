package carte;

public class Epidemia extends Carta {

	public Epidemia(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.EPIDEMIA, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
