package carte;

import java.util.List;

public class PioggiaMeteoriti extends Carta{
	// private final List
	private final List<Pianeta> pianeti; // Verrà modifcata dopo i test

	public PioggiaMeteoriti(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito, List<Pianeta> pianeti) {
		super(id, NomeSpeciale.PIOGGIA_METEORITI , nome, livello, ggVolo, merce, equipaggio, credito);
		this.pianeti=pianeti;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Che prevede " + pianeti;
	}
//questa classe ha ancora senso di esistere dopo aver creato l'enum NomeSpeciale?
	//2,Pioggia di meteoriti,II,0,0,0,0,PIOGGIA_METEORITI
	//3 . quantita, tipo, direzione
	
}
