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
}
