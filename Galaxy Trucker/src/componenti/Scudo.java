package componenti; 

class Scudo extends Componente {
	
	private Lato latoProtettoUno, latoProtettoDue;
	
	public Scudo(Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		super(up,dx,sx,dw);
		latoProtettoUno=Lato.up;
		latoProtettoDue=Lato.sx;
	}
	
	public boolean ruotaComponenteOrario(int gradi) {
		if(super.ruotaComponenteOrario(gradi)) {
			int nRotazioni=gradiToRotazioni(gradi);

			for(int i=0;i<nRotazioni;i++) {
				latoProtettoUno=latoProtettoUno.ruotaLatoOrario();
				latoProtettoDue=latoProtettoDue.ruotaLatoOrario();
			}
			return true;
		}
		return false;
	}
	
}
