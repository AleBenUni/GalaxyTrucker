package carte;
public class PioggiaMeteoriti extends Carta{
	// private final List

	public PioggiaMeteoriti(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.PIOGGIA_METEORITI , nome, livello, ggVolo, merce, equipaggio, credito);
	}
//questa classe ha ancora senso di esistere dopo aver creato l'enum NomeSpeciale?
	//2,Pioggia di meteoriti,II,0,0,0,0,PIOGGIA_METEORITI
	//3 . quantita, tipo, direzione
	
}
