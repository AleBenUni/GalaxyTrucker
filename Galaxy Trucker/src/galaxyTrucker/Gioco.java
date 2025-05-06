package galaxyTrucker;
import carte.Livello;

public class Gioco {
	
	private Nave[] nave;
	private Livello livello;
	
	public Gioco(int nGiocatori, Livello livello) {
		this.livello=livello;
		nave=new Nave[nGiocatori];
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello);
	}
	
	public Livello getLivello() {
		return livello;
		
	}
	
}
