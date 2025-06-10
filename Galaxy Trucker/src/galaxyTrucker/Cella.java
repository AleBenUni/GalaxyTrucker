package galaxyTrucker;

import componenti.Componente;

public class Cella {
	
	private Posizione posizione;
	private Componente componente;
	private boolean utilizzabile;
	private boolean connesso;

	public Cella(Componente componente, Posizione posizione) {
		this.componente=componente;
		this.posizione=posizione;
		utilizzabile=true;
		connesso=true;
	}
	
	public Cella(Posizione posizione) {
		componente=null;
		this.posizione=posizione;
		utilizzabile=false;
	}
	
	public Posizione getPosizione() {
		return posizione;
	}
	
	public Componente getComponente() {
		return componente;
	}
	
	public void setComponente(Componente componente) {
		this.componente=componente;
	}
	
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
	
	public boolean isUtilizzabile() {
		return utilizzabile;
	}
	
	public void setNotUtilizzabile() {
		utilizzabile=false;
	}
	
	public void setUtilizzabile() {
        this.utilizzabile=true;
    }
	
	public boolean isConnesso() {
		return connesso;
	}
	
	public void setConnesso(Boolean connesso) {
		this.connesso=connesso;
	}
}
