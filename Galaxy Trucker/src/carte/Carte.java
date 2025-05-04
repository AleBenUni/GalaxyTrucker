package carte;

import componenti.Merce;
import carte.Livello;
import pezzettini.Pedine;

public class Carte {

	
	/*
	 * IN COMUNE
	 * ---------------------------------------
	 * 1. Livello: 
	 *    - Classificazione in I, II, III
	 * 
	 * 2. Giorni di volo: 
	 *    - Modificatore (+/- giorni) 
	 *    - Eccezioni (0 giorni):
	 *      a) Pioggia di Meteoriti 
	 *      b) Epidemia 
	 *      c) Sabotaggio
	 * 
	 * 3. Merci: 
	 *    - Guadagno/Perdita di 4 tipi (priorità nella perdita: ROSSO > GIALLO > VERDE > BLUE) 
	 *    		(possibile uso di enum)
	 *    - Eccezioni (0 merci):
	 *      a) Nave Abbandonata
	 *      b) Schiavisti/Pirati (ma la sua carta gemella viola "contrabbandieri" tratta le merci, 
	 *      	quindi che fare, unirle in un gruppo solo, tenerle separate o creare 
	 *      	un ragruppamento per colore?)
	 *      c) Carte gialle
	 *      d) Spazio Aperto
	 * 
	 * 4. Equipaggio (umani e alieni): 
	 *    - Solo perdita
	 *    - Eccezioni (0 perdite):
	 *      a) Pioggia di Meteoriti
	 *      b) Pianeti
	 *      c) Nave Abbandonata
	 *      d) Pirati e Contrabbandieri (sono carte viole, Schiavisti perde equipaggio)
	 *      e) Sabotaggio e Polvere stellare  (sono carte gialle, Epidemia perde equipaggio)
	 *      f) Stazione Abbandonata
	 * 
	 * 5. Crediti: 
	 *    - Solo guadagno (mai perdita)
	 *    - Presente solo in:
	 *      a) Nave Abbandonata
	 *      b) Schiavisti e Pirati (In "Contrabbandieri" si guadagna merce al posto dei crediti)
	 * 
	 * 
	 * NON IN COMUNE
	 * -----------------------
	 * 1. Pioggia di Meteoriti: 
	 *    - Simile a Zona di Guerra ma con meccaniche diverse, la considero non comune
	 * 
	 * 2. Sabotaggio: 
	 *    - Distrugge un componente (simile a meteorite e cannonate), ma è un colpo sicuro,
	 *   determinato con tre lanci di dadi. Presente solo una volta tra le 150 carte,
	 *   la considero non comune.
	 *   
	 * 
	 * NOTE/DUBBI APERTI
	 * -----------------
	 *	  - Cannonate:
	 *	  Presenti in Zona di guerra e Pirati. Hanno una logica dietro l’effetto.
 	 *	  Da valutare se considerare cannonate come comune a tutte le carte o meno.
	 */
	
	private final int giorniVolo; //Ricorda che private < default < protected < public, sarà necessario salire di livello o basta il get?
	//final da rimuovere per via dell'effeto di "Spazio aperto"?
	private final int credito; 
	
	private Pedine perditeEquipaggio;
	private Livello livello;
	
	
	public Carte(Livello livello, int giorniVolo, Pedine perditeEquipaggio, int credito) {
	    this.giorniVolo = giorniVolo;
	    this.perditeEquipaggio = perditeEquipaggio;
	    this.livello= livello;
	    this.credito= credito;
	    //merci ho deciso di tenerla come ultima. Ha regole che forse non la rendono adatta ad una classe comune
	}
	
}
