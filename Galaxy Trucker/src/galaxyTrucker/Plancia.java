package galaxyTrucker;

import carte.Carta;
import carte.Livello;

public class Plancia {

	private int giorni;
	private int startPos[];
	private Carta mazzo[]
	
	public Plancia(Livello livello) {
		
		
		startPos=new int[4];
		switch(livello) {
			case I:
				giorni=18;
				startPos[0]=4;
				startPos[1]=2;
				startPos[2]=1;
				startPos[3]=0;
				break;
				
			case II:
				giorni=24;
				startPos[0]=6;
				startPos[1]=3;
				startPos[2]=1;
				startPos[3]=0;
				break;
				
			case III:
				giorni=34;
				startPos[0]=9;
				startPos[1]=5;
				startPos[2]=2;
				startPos[3]=0;
				break;
			
		}
	
	}
	
	public int getGiorni() {
		return giorni;
	}

	public int[] getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos[]) {
		this.startPos = startPos;
	}
}
