//IL supporto per questa classe termina 11/06/2025. Da riprendere in data non definita



package carte;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class Epidemia extends Carta {

	public Epidemia(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito) {
		super(id, NomeSpeciale.EPIDEMIA, nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	void attivaCarta(Gioco flotta) { 
		//Logica che controlla se due Cabine sono adiaccenti e collegate con un if
		
		}

	
}
