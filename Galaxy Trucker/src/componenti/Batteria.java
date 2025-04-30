package componenti;

public class Batteria extends Componente{
	int nEnergie;
	int grandezzaBatteria;
	public Batteria(int grandezzaBatteria, Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		super(up,dx,sx,dw);
		this.grandezzaBatteria=grandezzaBatteria;
		this.nEnergie=grandezzaBatteria;
	}
	
	public int getGrandezzaBatteria() {
		return grandezzaBatteria;
	}
	
	public int getNEnergie() {
		return nEnergie;
	}
	
	public boolean setNEnergie(int nEnergie) {
		if(nEnergie<=grandezzaBatteria&&nEnergie>=0) {
			this.nEnergie=nEnergie;
			return true;
		}
		return false;	
	}
	
	public boolean addEnergia(int nEnergie) {
		if((this.nEnergie+nEnergie)<=grandezzaBatteria&&nEnergie>=0) {
			this.nEnergie+=nEnergie;
			return true;
		}
		return false;
	}
	
	public boolean minusEnergia(int nEnergie) {
		if((this.nEnergie-nEnergie)>=0&&nEnergie>=0) {
			this.nEnergie-=nEnergie;
			return true;
		}
		return false;
	}
	
}
