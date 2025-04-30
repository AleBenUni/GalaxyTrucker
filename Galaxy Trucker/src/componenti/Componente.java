package componenti;

public class Componente {

	private Connettore up;
	private Connettore dx;
	private Connettore sx;
	private Connettore dw;
	
	public Componente(Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		this.up=up;
		this.dx=dx;
		this.sx=sx;
		this.dw=dw;
	}
	
	public Connettore getConnettori(Lato pos) {
		switch(pos){
		case up:
			return up;
		case dx:
			return dx;
		case sx:
			return sx;
		case dw:
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
		Connettore temp;
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
