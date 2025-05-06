package galaxyTrucker;
import carte.Livello;

public class Gioco {
	
	private Nave[] nave;
	
	public Gioco(int nGiocatori, Livello livello) {
		nave=new Nave[nGiocatori];
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello);
	}
	
}
