package componenti;

public class Componenti {

	private Connettori up;
	private Connettori dx;
	private Connettori sx;
	private Connettori dw;
	
	public Componenti(Connettori up, Connettori dx, Connettori sx, Connettori dw) {
		this.up=up;
		this.dx=dx;
		this.sx=sx;
		this.dw=dw;
	}
	
	public Connettori getConnettori(String pos) {
		switch(pos){
		case "up":
			return up;
		case "dx":
			return dx;
		case "sx":
			return sx;
		case "dw":
			return dw;
			
		}
		return null;
	}
}
