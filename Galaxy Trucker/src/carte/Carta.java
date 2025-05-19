package carte;

import carte.Livello; 
import pezzettini.Pedine;
import carte.NomeSpeciale;

public class Carta {
	
	private final int id; 
	int ggVolo; //Spazio Aperto puo cambiare questo attributo. Non più final 
	private final int credito; 
	private final int equipaggio;
	private int merce; //Non più final in quanto il dato sarà azzerato dalla carta Schiavisti
	final String nome; //Rimosso private per le sottoclassi dello stesso pacchetto "Un membro senza modificatore di accesso () è accessibile solo all'interno delle classi nello stesso pacchetto"
	
	NomeSpeciale effetto;
	private Livello livello;

	public Carta(Integer id, NomeSpeciale effetto,String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		this.id = id; 
		this.ggVolo = ggVolo;
	    this.equipaggio = equipaggio;
	    this.livello= livello;
	    this.credito= credito;
	    this.merce= merce;
	    this.effetto= effetto;
	    this.nome= nome;
	}
	
	public int getId() {
        return id;
    }

	public Livello getLivello() {
		return livello;
	}

	public int getGiorniVolo() {
		return ggVolo;
	}
	

	public void setGiorniVolo(int ggVolo) {
		this.ggVolo = ggVolo;
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

	public void setMerce(int merce) {
		this.merce = merce;
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