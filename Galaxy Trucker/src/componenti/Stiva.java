package componenti;

public class Stiva extends Componente{
	private int grandezzaStiva;
	private Merce[] carico;
	boolean stivaSpeciale;
	public Stiva(int grandezzaStiva, boolean stivaSpeciale, Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		super(up,dx,sx,dw);
		this.grandezzaStiva=grandezzaStiva;
		this.stivaSpeciale=stivaSpeciale;
		this.carico=new Merce[grandezzaStiva];
	}
	
	public int getGrandezzaStiva() {
		return grandezzaStiva;
	}
	
	public boolean isStivaSpeciale() {
		return stivaSpeciale;
	}
	
	public int getCarico()
}
