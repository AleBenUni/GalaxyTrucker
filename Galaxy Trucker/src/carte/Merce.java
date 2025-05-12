package carte;

public enum Merce {
	rosso (4), 
	giallo (3), 
	verde (2), 
	blu (1);
	
	protected final int valore;
	
	Merce(int valore){
		this.valore = valore;
	}
	
	public int getValore() {
		return valore;
	}
	
}
