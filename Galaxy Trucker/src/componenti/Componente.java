package componenti;

public class Componente {

	private Connettore up;
	private Connettore dx;
	private Connettore sx;
	private Connettore dw;
	private String imagePath;
	private int rotations=0;
	
	public Componente(Connettore up, Connettore dx, Connettore sx, Connettore dw, String imagePath) {
		this.up=up;
		this.dx=dx;
		this.sx=sx;
		this.dw=dw;
		this.imagePath=imagePath;
		this.rotations=0;
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
		rotations=(rotations+nRotazioni)%4;
		for(int i=0;i<nRotazioni;i++) {
			temp=up;
			up=sx;
			sx=dw;
			dw=dx;
			dx=temp;
		}
		return true;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public int getRotations() {
		return rotations*90;
	}
}
