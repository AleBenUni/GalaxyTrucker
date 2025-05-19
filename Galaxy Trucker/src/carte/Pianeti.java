package carte;

import java.util.List;

public class Pianeti extends Carta{
	private final List<Pianeta> pianeti; // per quanto volessi evitare di usare le liste, non ho pensato ad un'alternativa migliore
	
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
		return " Ci sono " + pianeti.size() + " pianeti. Abbiamo" + pianeti + "]";
	}
	
}
