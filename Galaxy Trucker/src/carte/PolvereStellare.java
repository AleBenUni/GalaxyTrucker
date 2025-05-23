package carte;

public class PolvereStellare extends Carta{

	public PolvereStellare(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.POLVERE_STELLARE, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
		return super.toString();	
	}

}
