package carte;

import componenti.Merce;  
import carte.Livello; 
import pezzettini.Pedine;
import carte.NomeSpeciale;

public class Carta {
	
	private final int ggVolo; 
	private final int credito; 
	private final int equipaggio;
	private final int merce;
	private final String nome;
	
	private NomeSpeciale effetto; 
	private Livello livello;

//richiama da tabellone la funzione riguardante la perdita di equipaggio
	public Carta(NomeSpeciale effetto,String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
	    this.ggVolo = ggVolo;
	    this.equipaggio = equipaggio;
	    this.livello= livello;
	    this.credito= credito;
	    this.merce= merce;
	    this.effetto= effetto;
	    this.nome= nome;
	}

	public Livello getLivello() {
		return livello;
	}

	public int getGiorniVolo() {
		return ggVolo;
	}

	public int getCredito() {
		return credito;
	}

	public int getEquipaggio() {
		return equipaggio;
	}

	public int getMerce() {
		return merce;
	}

	public NomeSpeciale getNomeEffetto() {
		return effetto;
	}

	public String getNomeCarta() {
		return nome;
	}

	@Override
	public String toString() {
	    return "La Carta " + nome + ( (effetto == NomeSpeciale.NESSUNO) ? " non ha effetti speciali" : " ha effetti speciali" );
	}
	
	
	
}