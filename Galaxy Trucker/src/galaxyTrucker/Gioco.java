package galaxyTrucker;
import carte.Livello;

public class Gioco {
	
	private Nave[] nave;
	private Livello livello;
	private int nGiocatori;
	
	public Gioco(int nGiocatori, Livello livello) {
		this.livello=livello;
		this.nGiocatori=nGiocatori;
		nave=new Nave[nGiocatori];
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello);
	}
	
	public Livello getLivello() {
		return livello;
	}
	
	public Nave getNave(int nave) {
		if(nave>=0||nave<nGiocatori)
			return this.nave[nave];
		else
			return null;
	}
	
	
}
