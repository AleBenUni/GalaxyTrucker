package galaxyTrucker;

import carte.Livello;
import componenti.*;

public class Nave {
	
	private Cella[][] celle;
	private int nRighe;
	private int nColonne;
	private int centroRighe;
	private int centroCol;
	private int giorniVolo;
	
	//
	private int creditoVolo;
	private int equipaggioABordo;
	//
	
	

	public Nave(Livello livello) {
		switch(livello) {
			case I:
				this.nRighe=5;
				this.nColonne=5;
				this.giorniVolo=0;
				this.centroCol=2;
				this.centroRighe=2;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==3 || j==4)&&i==0 || (j==0 || j==4)&&i==1 || j==2&&i==4)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo," "));
						
				break;
			case II:
				this.nRighe=5;
				this.nColonne=7;
				this.centroCol=3;
				this.centroRighe=2;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==3 || j==5 || j==6)&&i==0 || (j==0 || j==6)&&i==1 || j==3&&i==4)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo," "));
				break;
			case III:
				this.nRighe=6;
				this.nColonne=9;
				this.centroCol=4;
				this.centroRighe=3;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==2 || j==3 || j==5 || j==6 || j==7 || j==8)&&i==0 || (j==0 || j==1 || j==2|| j==6 || j==7 || j==8)&&i==1 || (j==1 || j==7)&&i==2 || (j==2 || j==6)&&i==5)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo," "));
				break;
		}
		
		
	}
	
	//
	public int getCreditoVolo() {
		return creditoVolo;
	}

	public void setCreditoVolo(int creditoVolo) {
		this.creditoVolo = creditoVolo;
	}
	
	public int getEquipaggioABordo() {
		return equipaggioABordo;
	}

	public void setEquipaggioABordo(int equipaggioABordo) {
		//bisognerebbe impedire di settare un equipaggio che superi il numero di cabine
		this.equipaggioABordo = equipaggioABordo;
	}

	//
	
	
	public int getNRighe() {
		return nRighe;
	}
	
	public int getNColonne() {
		return nColonne;
	}
	
	public int getGiorniVolo() {
		return giorniVolo;
	}
	
	public void setGiorniVolo(int giorni) {
		giorniVolo=giorni;
	}
	
	public void addGiorniVolo(int giorni) {
		if(giorni>=0)
			giorniVolo+=giorni;
	}
	
	public void minusGiorniVolo(int giorni) {
		if(giorni<=0)
			giorniVolo-=giorni;
	}
	
	public Cella getCella(Posizione posizione) {
		return celle[posizione.getRiga()][posizione.getColonna()];
	}
	
	public boolean setCella(Posizione pos, Componente componente) {
		int riga=pos.getRiga();
		int col=pos.getColonna();
		if(celle[riga][col].isUtilizzabile()&&celle[riga][col].getComponente()==null) {
			celle[riga][col].setComponente(componente);
			if(!verificaConnessioni(pos)) {
				celle[riga][col].setNotUtilizzabile();
				
			}
			return true;
		}else
			return false;
			
		
			
	}
	
	private boolean verificaConnessioni(Posizione pos) {
		int riga=pos.getRiga();
		int col=pos.getColonna();
		if(riga>=nRighe||riga<0||col>=nColonne||col<0)
			return false;
		boolean connesso=true;
		Componente componente=celle[riga][col].getComponente();
		if(componente==null)
			return false;

		if(col-1>=0&&celle[riga][col-1].getComponente()!=null)
			if(celle[riga][col-1].isUtilizzabile() && componente.getConnettori(Lato.sx).connection(celle[riga][col-1].getComponente().getConnettori(Lato.dx))) {
				if(riga!=centroRighe&&col-1!=centroCol)
					celle[riga][col-1].setNotUtilizzabile();
				connesso=false;
			}
			
				
		if(col+1<nColonne&&celle[riga][col+1].getComponente()!=null)
			if(celle[riga][col+1].isUtilizzabile() && componente.getConnettori(Lato.dx).connection(celle[riga][col+1].getComponente().getConnettori(Lato.sx))) {
				if(riga!=centroRighe&&col+1!=centroCol)
					celle[riga][col+1].setNotUtilizzabile();
				connesso=false;
			}

		if(riga-1>=0&&celle[riga-1][col].getComponente()!=null)
			if(celle[riga-1][col].isUtilizzabile() && componente.getConnettori(Lato.up).connection(celle[riga-1][col].getComponente().getConnettori(Lato.dw))) {
				if(riga-1!=centroRighe&&col!=centroCol)
					celle[riga-1][col].setNotUtilizzabile();
				connesso=false;
			}


		if(riga+1<nRighe&&celle[riga+1][col].getComponente()!=null)
			if(celle[riga+1][col].isUtilizzabile() && componente.getConnettori(Lato.dw).connection(celle[riga+1][col].getComponente().getConnettori(Lato.up))) {
				if(riga+1!=centroRighe&&col!=centroCol)
					celle[riga+1][col].setNotUtilizzabile();
				connesso=false;
			}
		
		return connesso;
			
	}
	
	public void eliminaComponente(int riga, int col) {
		if(celle[riga][col].isUtilizzabile()&&celle[riga][col].getComponente()!=null) {
			celle[riga][col].setNotUtilizzabile();
			celle[riga][col].setComponente(null);
			if(riga+1<nRighe)
				if(verificaConnessioni(new Posizione(riga+1,col))==false)
				celle[riga+1][col].setNotUtilizzabile();
			if(riga-1>=0)
				if(verificaConnessioni(new Posizione(riga-1,col))==false)
					celle[riga-1][col].setNotUtilizzabile();
			if(col+1<nColonne)
				if(verificaConnessioni(new Posizione(riga,col+1))==false)
					celle[riga][col+1].setNotUtilizzabile();
			if(col-1>=0)
				if(verificaConnessioni(new Posizione(riga,col-1))==false)
					celle[riga][col-1].setNotUtilizzabile();
			
		}
	}
	
	public int getEnergiaNave() {
		int nEnergia=0;
		Componente tmp;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Batteria)
					nEnergia+=((Batteria) tmp).getNEnergie();
			}
				
		return nEnergia;
	}
	
	public void minusEnergiaNave(int nEnergia) {
		Componente tmp;
		int energia;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Batteria) {
					energia=((Batteria) tmp).getNEnergie();
					if(energia>0) {
						energia=nEnergia-energia>0 ? energia : nEnergia;
						((Batteria) tmp).minusEnergia(energia);
						nEnergia-=energia;
					}	
				}
			}	
	}
	
	public void visualizzaNave() {
		for(int i=0;i<nRighe;i++) {
			for(int j=0;j<nColonne;j++)
				if(celle[i][j].isUtilizzabile()) {
					if(celle[i][j].getComponente() instanceof Cabina)
						System.out.print("C");
					else if(celle[i][j].getComponente()!=null)
						System.out.print("X");
					else
						System.out.print("O");
				}
					
				else
					System.out.print(" ");
			System.out.print("\n");	
		}
	}
	
	public void visualizzaUtilizzabileNave() {
		for(int i=0;i<nRighe;i++) {
			for(int j=0;j<nColonne;j++)
				if(celle[i][j].isUtilizzabile()) {
					System.out.print("O");
				}
				else
					System.out.print(" ");
			System.out.print("\n");	
		}
	}
}
