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
	
	public int gradiToRotazioni(int gradi) {
	    switch (gradi) {
	        case 90:
	            return 1;
	        case 180:
	            return 2;
	        case 270:
	            return 3;
	        default:
	            return -1; // Valore di default per gradi non validi
	    }
	}
	
	public boolean ruotaComponenteOrario(int gradi) {
		Connettori temp;
		int nRotazioni=gradiToRotazioni(gradi);
		if(nRotazioni==-1)
			return false;

		for(int i=0;i<nRotazioni;i++) {
			temp=up;
			up=sx;
			sx=dw;
			dw=dx;
			dx=temp;
		}
		return true;
	}
}
