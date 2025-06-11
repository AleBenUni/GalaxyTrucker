package componenti;

public enum Lato {
	up, dx, sx, dw;
	
	public Lato ruotaLatoOrario() {
        switch (this) {
            case up:
                return Lato.dx;
            case dx:
                return Lato.dw;
            case dw:
                return Lato.sx;
            case sx:
                return Lato.up;
            default:
                throw new IllegalStateException("Lato non valido");
        }
    }
	
	public String stampaLato() {
		switch (this) {
        case up:
            return "sopra";
        case dx:
        	return "destra";
        case dw:
        	return "sotto";
        case sx:
        	return "sinistra";
        default:
            throw new IllegalStateException("Lato non valido");
    }
	}
	
	public Lato opposto() {
        switch (this) {
            case up:  return dw;
            case dw:  return up;
            case sx:  return dx;
            case dx:  return sx;
            default:  throw new IllegalStateException("Lato sconosciuto: " + this);
        }
    }
}
