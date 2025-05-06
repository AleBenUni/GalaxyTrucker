package pezzettini;

public class Pedine {

	//questa classe necessita di modifche, scritta solo per definire il package carte

	private int umani;
	private int alieni;

	public Pedine(int umani, int alieni) {
		this.umani =  umani;
		this.alieni = alieni;
	}

	public int getUmani() {
		return umani;
	}

	public int getAlieni() {
		return alieni;
	}

	public int getPedine() {
		return umani + alieni;
	}

	public void aggiungiUmani(int n) {
		if (n > 0) 
			this.umani += n;
		 throw new IllegalArgumentException("Non puoi aggiungere un numero negativo di umani: " + n); //così e come lo desidera il professore, ma qualcosa non torna, in questo modo constringo al giocatore di rifare la partita, non è una bella cosa da fare. Chiedere cosa intenda per "Permettete le azioni illegali ma segnalatelo con un eccezione"? 
	}

	public void aggiungiAlieni(int n) {
		if (n > 0) 
			this.alieni += n;
	}

	public void rimuoviUmani(int n) {
		this.umani = this.umani - n;
	}

	public void rimuoviAlieni(int n) {
		this.alieni = this.alieni - n;
	}

	public void rimuoviPedine(int n) {
		int toRemove = n;
		  if (umani >= toRemove) {
		        umani -= toRemove;
		        toRemove=0;
		    } else {
		        toRemove -= umani;
		        umani = 0;
		    }

		    if (toRemove > 0) {
		        if (alieni >= toRemove) {
		            alieni -= toRemove;
		        } else {
		            alieni = 0;
		        }
		    }

	@Override
	public String toString() {
		return "Umani: " + umani + ", Alieni: " + alieni + ", Totale: " + getPedine();
	}
}
