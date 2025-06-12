package carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import carte.Livello; 
import pezzettini.Pedine;
import carte.NomeSpeciale;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;

public class Carta {
	
	
	private final int id; 
	int ggVolo; //Spazio Aperto puo cambiare questo attributo. Non più final 
	private final int credito; 
	private final int equipaggio;
	private int merce; //Non più final in quanto il dato sarà azzerato dalla carta Schiavisti
	final String nome; //Rimosso private per le sottoclassi dello stesso pacchetto "Un membro senza modificatore di accesso () è accessibile solo all'interno delle classi nello stesso pacchetto"
	private final NomeSpeciale effetto;
	private final Livello livello;

	/**
	 * Costruttore della carta.
	 * 
	 * @param id
	 * @param effetto
	 * @param nome
	 * @param livello
	 * @param ggVolo
	 * @param merce
	 * @param equipaggio
	 * @param credito
	 */
	public Carta(Integer id, NomeSpeciale effetto,String nome ,Livello livello, int ggVolo, int merce, int equipaggio, int credito) {
		this.id = id; 
		this.ggVolo = ggVolo;// con - indico un guadagno di giorni, con numero positivo indico una perdita di giorni (fatto per ridurre i caratteri nel file Excel)
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
	
	
	/**
     * Applica gli effetti della carta sulla nave leader.
     * <p>
     * Effetti applicati:
     * <ul>
     *   <li>Modifica giorni di volo (valori negativi riducono il volo)</li>
     *   <li>Aggiunge/rimuove crediti</li>
     *   <li>Gestisce merce (perdita o consumo energia)</li>
     *   <li>Modifica equipaggio (valori negativi riducono l'equipaggio)</li>
     * </ul>
     * 
     * @param naveLeader Nave su cui applicare gli effetti
     */
	void applicaEffetti(Nave naveLeader)
	{
		if (this.getGiorniVolo()<0) {
    		naveLeader.addGiorniVolo(ggVolo);
    	} else {
    		naveLeader.minusGiorniVolo(ggVolo);
    	}
    	naveLeader.setCreditoVolo(naveLeader.getCreditoVolo()+credito);
    	
    	if ( merce>0) {
    		for (int i=1; i<=merce; i++) {
    			if (naveLeader.minusStiva()==false) {
    				naveLeader.minusEnergiaNave(1);
    			}
    		}
    	}
    	if ( equipaggio <0 ) {
    		naveLeader.setEquipaggioABordo(naveLeader.getEquipaggioABordo()+equipaggio);
    	}
	}
    void attivaCarta(Gioco flotta) { 
    	
    	List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
    	Nave naveLeader = ordinate.get(0);
    	
    	applicaEffetti(naveLeader);
    	
	}

	
}