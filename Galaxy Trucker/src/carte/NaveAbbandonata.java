package carte;

public class NaveAbbandonata extends Carta {
	
	public NaveAbbandonata(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.NAVE_ABBANDONATA, nome, livello, ggVolo, merce, equipaggio, credito);
	}

	@Override
	public String toString() {
		return super.toString(); 
	}
	
}
