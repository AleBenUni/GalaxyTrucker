package componenti;

public class Modstru {
	
	 private String nome;
	    private Connettore[] lati = new Connettore[4]; // Indici: 0 = Nord, 1 = Est, 2 = Sud, 3 = Ovest

	    // Costruttore: inizializza nome e i 4 connettori
	    public Modstru(String nome, Connettore nord, Connettore est,
	                   Connettore sud, Connettore ovest) {
	        this.nome = nome;
	        this.lati[0] = nord;
	        this.lati[1] = est;
	        this.lati[2] = sud;
	        this.lati[3] = ovest;
	    }

	    // Controlla se il modulo può connettersi a un altro modulo sui lati indicati
	    public boolean puoConnettersiA(Modstru altro, int mioLato, int latoAltro) {
	        return lati[mioLato].puoConnettersi(altro.lati[latoAltro]);
	    }

	    // Restituisce il nome del modulo
	    public String getNome() {
	        return nome;
	    }

	    // Restituisce il connettore su un lato specifico
	    public Connettore getConnettore(int lato) {
	        return lati[lato];
	    }

}
