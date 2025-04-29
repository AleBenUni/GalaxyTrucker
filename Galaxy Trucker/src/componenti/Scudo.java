package componenti; 

class Scudo extends Componenti {
	
	private Lato latoProtettoUno, latoProtettoDue;
	
	public Scudo(Connettori up, Connettori dx, Connettori sx, Connettori dw) {
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
