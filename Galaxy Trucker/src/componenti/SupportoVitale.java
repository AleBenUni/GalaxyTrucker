package componenti;

public class SupportoVitale extends Componente{

	private final Equipaggio tipo;
	
	public SupportoVitale(Connettore up, Connettore dx, Connettore sx, Connettore dw, Equipaggio tipo, String imagePath) {
		super(up,dx,sx,dw,imagePath);
		this.tipo=tipo;
	}
	
	public Equipaggio getTipo() {
		return tipo;
	}
}
