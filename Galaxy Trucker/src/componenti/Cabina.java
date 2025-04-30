package componenti;

class Cabina extends Componente{
	private int nEquipaggio;
	private Equipaggio tipoEquipaggio;
	private int postiEquipaggio;
	
	public Cabina(int postiEquipaggio, Connettore up, Connettore dx, Connettore sx, Connettore dw) {
		super(up,dx,sx,dw);
		this.postiEquipaggio=postiEquipaggio;
		this.nEquipaggio=nEquipaggio;
		this.tipoEquipaggio=Equipaggio.umano;
	}
	
	public void setTipoEquipaggio(Equipaggio tipoEquipaggio) {
		this.tipoEquipaggio=tipoEquipaggio;
	}
	
	public int getNEquipaggio() {
		return nEquipaggio;
	}
	
	public boolean removeEquipaggio(int nEquipaggio) {
		if(this.nEquipaggio-nEquipaggio>=0&&nEquipaggio>0)
			return true;
		return false;
	}
	
	public boolean addEquipaggio(Equipaggio tipoEquipaggio) {
		if(this.tipoEquipaggio==tipoEquipaggio&&nEquipaggio+1<=postiEquipaggio) {
			nEquipaggio++;
			return true;
		}
		return false;	
	}
}
