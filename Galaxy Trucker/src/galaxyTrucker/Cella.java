package galaxyTrucker;

import componenti.Componente;

public class Cella {
	
	Posizione posizione;
	Componente componente;

	public Cella(Componente componente, Posizione posizione) {
		this.componente=componente;
		this.posizione=posizione;
	}
	
	public Posizione getPosizione() {
		return posizione;
	}
	
	public Componente getCoponente() {
		return componente;
	}
	
	public void setComponente(Componente componente) {
		this.componente=componente;
	}
	
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
}
