package galaxyTrucker;

import java.util.Objects;

public class Posizione {
	private final int riga;
	private final int colonna;
	
	public Posizione(int riga, int colonna) {
		this.riga=riga;
		this.colonna=colonna;
	}
	
	public int getRiga() {
		return riga;
	}
	
	public int getColonna() {
		return colonna;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se sono lo stesso oggetto in memoria, sono uguali
        if (o == null || getClass() != o.getClass()) return false; // Se l'altro è nullo o di una classe diversa, non sono uguali
        Posizione posizione = (Posizione) o; // Cast dell'oggetto
        // Confronta i valori interni per determinare l'uguaglianza
        return riga == posizione.riga && colonna == posizione.colonna;
    }

    /**
     * Metodo FONDAMENTALE, va sempre insieme a equals().
     * Fornisce un codice numerico (hash code) basato sul contenuto dell'oggetto.
     * Le collezioni come HashSet lo usano per memorizzare e trovare oggetti in modo efficiente.
     */
    @Override
    public int hashCode() {
        // Genera un codice hash usando i valori di riga e colonna
        return Objects.hash(riga, colonna);
    }
}

