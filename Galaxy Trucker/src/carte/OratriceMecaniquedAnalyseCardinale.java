package carte;

import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class OratriceMecaniquedAnalyseCardinale extends Carta{
//Questa doveva essere un Easter egg che permetteva di terminare una partita indipendentemente dallo stato di essa. 
//Adesso mantiene l'effetto ma viene pescata anche a fine corsa e calcola i punteggi dei gicatori.
	
	public OratriceMecaniquedAnalyseCardinale(Integer id, String nome, Livello livello,
			int ggVolo, int merce, int equipaggio, int credito) {
		super(id, NomeSpeciale.FINE , nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	void attivaCarta(Gioco flotta) { 
		System.out.println("E' tempo di giudicare il tuo viaggio, abbi fede nel tuo viaggio e sarai giustamente giustiziato!");
		//Avverranno i diversi calcoli sui punteggi
		
		}

}
