package componenti;

public class Cannone extends Componente{
	private int nCannoni;
	private Lato posCannoni;
	
	public Cannone(int nCannoni, Connettore up, Connettore dx, Connettore sx, Connettore dw, String imagePath) {
		super(up,dx,sx,dw,imagePath);
		this.nCannoni=nCannoni;
		posCannoni=Lato.up;	//Da controllare
	}
	
	public int getNCannoni() {
		return nCannoni;
	}
	
	public boolean ruotaComponenteOrario(int gradi) {
		if(super.ruotaComponenteOrario(gradi)) {
			int nRotazioni=gradiToRotazioni(gradi);

			for(int i=0;i<nRotazioni;i++) {
				posCannoni=posCannoni.ruotaLatoOrario();
			}
			return true;
		}
		return false;
	}
	
	public float getPotenzaFuoco() {
		if(posCannoni==Lato.up)
			return nCannoni;
		else
			return nCannoni/2;
	}
	
	public Lato getPosCannoni() {
		return posCannoni;
	}
}
