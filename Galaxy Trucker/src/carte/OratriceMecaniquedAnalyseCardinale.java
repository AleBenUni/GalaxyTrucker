package carte;

import java.util.List;

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
		System.out.println("\nE' tempo di giudicare il tuo viaggio, abbi fede nel tuo viaggio e sarai giustamente giustiziato!");
		//Avverranno i diversi calcoli sui punteggi
		//Manca settare e prendere il credito della nave di ogni giocatore
		List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
		for (int i = 0; i < flotta.getNGiocatori(); i++) {
			System.out.println("\nLa nave del giocatore " + ordinate.get(i).getColor() + " ha guadagnato " + (ordinate.get(i).calcolaPunteggio() + flotta.getNGiocatori()-i + ordinate.get(i).getCreditoVolo()));
		}
		System.out.println("\nAvete raggiunto tutti l'obbiettivo: fare soldi! E ne avete fatti. A chi importa se\r\n"
				+ "qualche pagliaccio ne ha fatti di più? Anche se devo dire che il giocatore " + flotta.getNGiocatori()
				+ " nella classifica mi ha deluso.\n" );
		}

}
