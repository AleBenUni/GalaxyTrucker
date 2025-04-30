package componenti;

public class Motore extends Componente{

	private int nTubiScappamento;
	private Lato posTubiScappamento;
	
	public Motore(int nTubiScappamento, Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		super(up,dx,sx,dw);
		this.nTubiScappamento=nTubiScappamento;
		posTubiScappamento=Lato.dw;
	}
	
	public int getNTubiScappamento() {
		return nTubiScappamento;
	}
	
	public boolean ruotaComponenteOrario(int gradi) {
		if(super.ruotaComponenteOrario(gradi)) {
			int nRotazioni=gradiToRotazioni(gradi);

			for(int i=0;i<nRotazioni;i++) {
				posTubiScappamento=posTubiScappamento.ruotaLatoOrario();
			}
			return true;
		}
		return false;
	}
}
