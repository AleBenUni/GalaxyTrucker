package carte;

import java.util.List;

public class StazioneAbbandonata extends Carta{
	
	private final List<Pianeta> casse;

	public StazioneAbbandonata(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito, List<Pianeta> casse) {
		super(id, NomeSpeciale.STAZIONE_ABBANDONATA, nome, livello, ggVolo, merce, equipaggio, credito);
		this.casse = casse;
	}

	@Override
	public String toString() {
		return super.toString() + " che ti fa guadagnare " + casse;
	}

}
