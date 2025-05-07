package galaxyTrucker;

import carte.Livello;

public class Nave {
	
	private Cella[][] celle;
	private int nRighe;		//Controllarne utilità
	private int nColonne;
	
	
	public Nave(Livello livello) {
		switch(livello) {
			case I:
				this.nRighe=5;
				this.nColonne=5;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==3 || j==4)&&i==0 || (j==0 || j==4)&&i==1 || j==2&&i==4)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
						
				break;
			case II:
				this.nRighe=5;
				this.nColonne=7;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==3 || j==5 || j==6)&&i==0 || (j==0 || j==6)&&i==1 || j==3&&i==4)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				break;
			case III:
				this.nRighe=6;
				this.nColonne=9;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==2 || j==3 || j==5 || j==6 || j==7 || j==8)&&i==0 || (j==0 || j==1 || j==2|| j==6 || j==7 || j==8)&&i==1 || (j==1 || j==7)&&i==2 || (j==2 || j==6)&&i==5)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				break;
		}
		
		
	}
	
	public int getNRighe() {
		return nRighe;
	}
	
	public int getNColonne() {
		return nColonne;
	}
	
	public Cella getCella(Posizione posizione) {
		return celle[posizione.getRiga()][posizione.getColonna()];
	}
	
	public void visualizzaNave() {
		for(int i=0;i<nRighe;i++) {
			for(int j=0;j<nColonne;j++)
				if(celle[i][j].isUtilizzabile())
					System.out.print("X");
				else
					System.out.print(" ");
			System.out.print("\n");	
		}
	}
}
