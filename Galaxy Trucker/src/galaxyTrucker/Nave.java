package galaxyTrucker;

public class Nave {
	
	private Cella[][] cella;
	private int sizeX;		//Controllarne utilità
	private int sizeY;
	
	public Nave(int sizeX, int sizeY) {
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.cella=new Cella[sizeX][sizeY];
		for(int i=0;i<sizeX;i++)
			for(int j=0;j<sizeY;j++)
				cella[i][j]=new Cella(null, new Posizione(i,j));
	}
}
