package galaxyTrucker;

import carte.Livello;
import carte.Mazzo;

public class Plancia {

	private int giorni;
	private int startPos[];
	private Mazzo mazzo[];
	
	public Plancia(Livello livello) {
		
		
		Mazzo mainMazzo=new Mazzo(false);
		mazzo=new Mazzo[4];
		for(int i=0;i<4;i++)
			mazzo[i]=new Mazzo(true);
		startPos=new int[4];
		switch(livello) {
			case I:
				giorni=18;
				startPos[0]=4;
				startPos[1]=2;
				startPos[2]=1;
				startPos[3]=0;
				
				for(int i=0;i<4;i++) {
					mazzo[i].trasferisciCartaDaMazzo(Livello.I, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.I, mainMazzo);
				}
				break;
				
			case II:
				giorni=24;
				startPos[0]=6;
				startPos[1]=3;
				startPos[2]=1;
				startPos[3]=0;
				
				for(int i=0;i<4;i++) {
					mazzo[i].trasferisciCartaDaMazzo(Livello.I, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.II, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.II, mainMazzo);
				}

				break;
				
			case III:
				giorni=34;
				startPos[0]=9;
				startPos[1]=5;
				startPos[2]=2;
				startPos[3]=0;
				
				for(int i=0;i<4;i++) {
					mazzo[i].trasferisciCartaDaMazzo(Livello.I, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.II, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.III, mainMazzo);
					mazzo[i].trasferisciCartaDaMazzo(Livello.III, mainMazzo);
				}
				break;
			
		}
	
	}
	
	public int getGiorni() {
		return giorni;
	}

	public int[] getStartPos() {
		return startPos;
	}
	
	public int getStartPos(int i) {
		return startPos[i];
	}

	public void setStartPos(int startPos[]) {
		this.startPos = startPos;
	}
	
	public Mazzo getMazzo(int nMazzo) {
		if(nMazzo>0&&nMazzo<=4)
			return mazzo[nMazzo];
		return null;
	}
	
	public Mazzo getMazzoCompleto() {
		Mazzo mazzo=new Mazzo(true);
		for(int i=0;i<4;i++)
			mazzo.unisciMazzi(this.mazzo[i]);
		return mazzo;
	}
}
