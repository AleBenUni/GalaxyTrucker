package componenti;

import java.util.Arrays;

public class Stiva extends Componente{
	private int grandezzaStiva;
	private Merce[] carico;
	boolean stivaSpeciale;
	public Stiva(int grandezzaStiva, boolean stivaSpeciale, Connettore up, Connettore dx, Connettore sx, Connettore dw, String imagePath) {
		super(up,dx,sx,dw,imagePath);
		this.grandezzaStiva=grandezzaStiva;
		this.stivaSpeciale=stivaSpeciale;
		this.carico=new Merce[grandezzaStiva];
	}
	
	public int getGrandezzaStiva() {
		return grandezzaStiva;
	}
	
	public int getStivaUtilizzabile() {
		int cont=0;
		for(int i=0;i<grandezzaStiva;i++)
			if(carico[i]==null)
				cont++;
		return cont;
	}
	
	public boolean isStivaSpeciale() {
		return stivaSpeciale;
	}
	
	public 	Merce[] getCarico() {
		return Arrays.copyOf(this.carico, this.carico.length);
		
	}
	
	public boolean addCarico(Merce merce) {
		for(int i=0;i<carico.length;i++)
			if(carico[i]==null) {
				carico[i]=merce;
				return true;
			}
		return false;	
	}
	
	public boolean minusCarico(Merce merce) {
		for(int i=0;i<carico.length;i++)
			if(carico[i]==merce) {
				carico[i]=null;
				return true;
			}
		return false;	
	}
	
	public boolean minusCarico() {
		for(int i=0;i<carico.length;i++)
			if(carico[i]!=null) {
				carico[i]=null;
				return true;
			}
		return false;	
	}
	
	public boolean isStivaEmpty() {
		for(int i=0;i<carico.length;i++)
			if(carico[i]!=null)
				return false;
		return true;
	}
}
