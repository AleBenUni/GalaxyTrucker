package carte;

import java.util.List;

public class Pianeti extends Carta{
// servira extends carta?
	// sì, altrimenti non sarà possibile creare il mazzo
	private final List<Pianeta> pianeti; // per quanto volessi evitare di usare le liste, non ho pensato ad un'alternativa migliore
	
	public Pianeti(String nome, Livello livello, int ggVolo, int merce, int equipaggio,
			int credito, List<Pianeta> pianeti) {
		super(NomeSpeciale.PIANETI, nome, livello, ggVolo, merce, equipaggio, credito);
		this.pianeti=pianeti;
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	@Override
	public String toString() {
		return "Pianeti [pianeti=" + pianeti + "]";
	}
	
}
