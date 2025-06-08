package carte;

import galaxyTrucker.Nave;

public class OratriceMecaniquedAnalyseCardinale extends Carta{
//Questa doveva essere un Easter egg che permetteva di terminare una partita indipendentemente dallo stato di essa. 
//Adesso mantiene l'effetto ma viene pescata a fine corsa.
	
	public OratriceMecaniquedAnalyseCardinale(Integer id, String nome, Livello livello,
			int ggVolo, int merce, int equipaggio, int credito) {
		super(id, NomeSpeciale.FINE , nome, livello, ggVolo, merce, equipaggio, credito);
	}
	
	@Override
	void attivaCarta(Nave naveLeader) { 
		System.out.println("E' tempo di giudicare il tuo viaggio, abbi fede nel gioco e sarai giustamente giustiziato");
		//Avverranno i diversi calcoli
		
		System.exit(0);
		}

}
