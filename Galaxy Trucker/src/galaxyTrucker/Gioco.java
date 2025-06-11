package galaxyTrucker;
import carte.Livello;
import componenti.Mucchio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Gioco {
	
	private Nave[] nave;
	private Plancia plancia;
	private Livello livello=null;
	private int nGiocatori;
	Mucchio mucchio;
	String colori[]= {"red","green","blue","yellow"};
	public Gioco(int nGiocatori, Livello livello) {
		this.nGiocatori=nGiocatori;
		this.livello=livello;
		mucchio=new Mucchio();
		mucchio.riempiMucchio();
		nave=new Nave[nGiocatori];
		plancia=new Plancia(livello);
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello,colori[i]);
	}
	
	public Livello getLivello() {
		return livello;
	}
	
	public Nave getNave(int nave) {
		if(nave>=0 || nave<nGiocatori)
			return this.nave[nave];
		else
			return null;
	}
	
	public List<Nave> getFlottaNaveOrdinata() {
	    List<Nave> giocatori = new ArrayList<>(this.getNGiocatori());
	    for (int i = 0; i < this.getNGiocatori(); i++) {
	        giocatori.add(this.getNave(i));
	    }
	    giocatori.sort((n1, n2) -> Integer.compare(n2.getGiorniVolo(), n1.getGiorniVolo())); //La precedente non funzionava
	    return giocatori;
	}

	
	public Plancia getPlancia() {
		return plancia;
	}
	
	 public Mucchio getMucchio() {
		 return this.mucchio;
	}
	 
	 public int getNGiocatori() {
		 return this.nGiocatori;
	}
	
}
