package carte;

public class Sabotaggio extends Carta {

	public Sabotaggio(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.SABOTAGGIO, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
