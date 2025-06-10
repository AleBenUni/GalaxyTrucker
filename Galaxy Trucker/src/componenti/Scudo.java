package componenti; 

public class Scudo extends Componente {
	
	private Lato latoProtettoUno, latoProtettoDue;
	
	public Scudo(Connettore up, Connettore dx, Connettore sx, Connettore dw, String imagePath) {
		super(up,dx,sx,dw,imagePath);
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
	
	public Lato getLatoProtetto(int nLato) {
		if(nLato==1)
			return latoProtettoUno;
		else if(nLato==2)
			return latoProtettoDue;
		else
			return null;
	}
	
}
