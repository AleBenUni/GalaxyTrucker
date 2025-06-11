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
	
	public List<Nave> getFlottaNaveOrdinata(){
		Integer[] giorniNavi = new Integer [this.getNGiocatori()];
    	List <Nave> giocatori = new ArrayList<>();
    	for (int i=0; i<this.getNGiocatori(); i++) {
    		giorniNavi[i]=this.getNave(i).getGiorniVolo();
    	}
    	Arrays.sort(giorniNavi, Collections.reverseOrder());
    	for (int i=0; i<this.getNGiocatori(); i++) {
    		for (int j=0; j<this.getNGiocatori(); j++) {
    			if (giorniNavi[j]==this.getNave(i).getGiorniVolo()) {
    				giocatori.add(this.getNave(i));
    			}
    		}
    	}
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
