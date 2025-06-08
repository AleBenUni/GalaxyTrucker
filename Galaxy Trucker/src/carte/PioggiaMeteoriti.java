package carte;

import java.util.List;

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

}
