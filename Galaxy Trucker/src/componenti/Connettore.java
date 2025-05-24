package componenti;

public enum Connettore {
	assente,singolo, doppio, multiplo;
	
	    // Metodo che verifica se due connettori possono collegarsi tra loro
	    public boolean puoConnettersi(Connettore altro) {
	        // Se uno dei due è MULTIPLO, la connessione è sempre possibile
	        if (this == MULTIPLO || altro == MULTIPLO) {
	            return true;
	        }

	        // Se uno dei due è ASSENTE, non si possono collegare
	        if (this == ASSENTE || altro == ASSENTE) {
	            return false;
	        }

	        // Altrimenti si possono collegare solo se sono dello stesso tipo
	        return this == altro;
	    }
}
