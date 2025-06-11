package galaxyTrucker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import carte.Livello;
import componenti.*;

public class Nave {
	
	private Cella[][] celle;
	private int nRighe;
	private int nColonne;
	private int centroRighe;
	private int centroCol;
	private int giorniVolo;
	private String colore;
	private String imagePath;
	
	//
	private int creditoVolo;
	private int equipaggioABordo;
	//
	
	
	

	public Nave(Livello livello, String colore) {
		
		this.giorniVolo=-1;
		
		switch(colore) {
			case "red":
				this.colore=colore;
				imagePath="/images/Cabina/133.jpg";
				break;
			case "green":
				this.colore=colore;
				imagePath="/images/Cabina/135.jpg";
				break;
			case "yellow":
				this.colore=colore;
				imagePath="/images/Cabina/136.jpg";
				break;
			case "blue":
				this.colore=colore;
				imagePath="/images/Cabina/134.jpg";
				break;
		}
		
		switch(livello) {
			case I:
				this.nRighe=5;
				this.nColonne=5;
				this.centroCol=2;
				this.centroRighe=2;
				this.celle=new Cella[nRighe][nColonne];
				for(int i=0;i<nRighe;i++)
					for(int j=0;j<nColonne;j++)
						if((j==0 || j==1 || j==3 || j==4)&&i==0 || (j==0 || j==4)&&i==1 || j==2&&i==4)
							celle[i][j]=new Cella(new Posizione(i,j));
						else
							celle[i][j]=new Cella(null, new Posizione(i,j));
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,imagePath));
						
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
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,imagePath));
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
				celle[centroRighe][centroCol].setComponente(new Cabina(2,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,Connettore.multiplo,imagePath));
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
	
	public String getColor() {
		return colore;
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
	
	public boolean isLatoProtetto(Lato lato) {
		Componente tmp;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Scudo)
					if(((Scudo) tmp).getLatoProtetto(1)==lato||((Scudo) tmp).getLatoProtetto(2)==lato)
						return true;
			}
		return false;
	}
	
	public boolean isLatoProtetto(Lato lato, int pos) {
		Componente tmp;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Cannone)
					if(((Cannone) tmp).getPosCannoni()==lato) {
						if(lato==Lato.up) {
							if(j==pos)
								return true;
						}else if(lato==Lato.dw) {
							if(j-1<=pos&&j+1>=pos)
								return true;
						}else if(i-1<=pos&&i+1>=pos)
							return true;

					}
			}
		return false;
	}
	
	public int getSpazioStiva() {
		Componente tmp;
		int cont=0;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Stiva)
					if(!((Stiva) tmp).isStivaSpeciale())
						cont+=((Stiva) tmp).getStivaUtilizzabile();
			}

		return cont;
	}
	
	public int getSpazioStivaSpeciale() {
		Componente tmp;
		int cont=0;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Stiva)
					if(((Stiva) tmp).isStivaSpeciale())
						cont+=((Stiva) tmp).getStivaUtilizzabile();
			}

		return cont;
	}
	
	public boolean setStiva(Merce merce) {
		Componente tmp;
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				tmp=celle[i][j].getComponente();
				if(tmp instanceof Stiva)
					if(merce==Merce.rosso)
						if(((Stiva) tmp).isStivaSpeciale() && ((Stiva) tmp).getStivaUtilizzabile()>0)
							return ((Stiva) tmp).addCarico(merce);
							
						else
							return false;
					else
						if(((Stiva) tmp).getStivaUtilizzabile()>0)
							return ((Stiva) tmp).addCarico(merce);
						else
							return false;
						
			}
		return false;
	}
	
	public Cella getCella(Posizione posizione) {
		return celle[posizione.getRiga()][posizione.getColonna()];
	}
	
	public boolean setCella(Posizione pos, Componente componente) {
		int riga=pos.getRiga();
		int col=pos.getColonna();
		if(celle[riga][col].isUtilizzabile()&&celle[riga][col].getComponente()==null) {
			celle[riga][col].setComponente(componente);
			
			celle[riga][col].setConnesso(verificaConnessioni(pos));
				
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

		if(col-1>=0&&celle[riga][col-1].getComponente()!=null) {
			if(celle[riga][col-1].isUtilizzabile() && !componente.getConnettori(Lato.sx).connection(celle[riga][col-1].getComponente().getConnettori(Lato.dx))) {
				if(riga!=centroRighe||col-1!=centroCol)
					celle[riga][col-1].setConnesso(false);
				connesso=false;
				System.out.println("A");
			}
			if(!celle[riga][col-1].isConnesso())
				connesso=false;
		}
			
			
				
		if(col+1<nColonne&&celle[riga][col+1].getComponente()!=null) {
			if(celle[riga][col+1].isUtilizzabile() && !componente.getConnettori(Lato.dx).connection(celle[riga][col+1].getComponente().getConnettori(Lato.sx))) {
				if(riga!=centroRighe||col+1!=centroCol)
					celle[riga][col+1].setConnesso(false);
				connesso=false;
				System.out.println("B");
			}
			if(!celle[riga][col+1].isConnesso())
				connesso=false;
		}
			

		if(riga-1>=0&&celle[riga-1][col].getComponente()!=null) {
			if(celle[riga-1][col].isUtilizzabile() && !componente.getConnettori(Lato.up).connection(celle[riga-1][col].getComponente().getConnettori(Lato.dw))) {
				if(riga-1!=centroRighe||col!=centroCol)
					celle[riga-1][col].setConnesso(false);
				connesso=false;
				System.out.println("C");
			}
			if(!celle[riga-1][col].isConnesso())
				connesso=false;
		}
			


		if(riga+1<nRighe&&celle[riga+1][col].getComponente()!=null) {
			if(celle[riga+1][col].isUtilizzabile() && !componente.getConnettori(Lato.dw).connection(celle[riga+1][col].getComponente().getConnettori(Lato.up))) {
				if(riga+1!=centroRighe||col!=centroCol)
					celle[riga+1][col].setConnesso(false);
				connesso=false;
				System.out.println("D");
			}
			if(!celle[riga+1][col].isConnesso())
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
				celle[riga+1][col].setConnesso(false);
			if(riga-1>=0)
				if(verificaConnessioni(new Posizione(riga-1,col))==false)
					celle[riga-1][col].setConnesso(false);
			if(col+1<nColonne)
				if(verificaConnessioni(new Posizione(riga,col+1))==false)
					celle[riga][col+1].setConnesso(false);
			if(col-1>=0)
				if(verificaConnessioni(new Posizione(riga,col-1))==false)
					celle[riga][col-1].setConnesso(false);
			
		}
	}
	
	/*public void aggiornaStatoConnessioni() {
	    // 1. Resetta correttamente tutti i componenti tranne la cabina
	    for (int riga = 0; riga < nRighe; riga++) {
	        for (int col = 0; col < nColonne; col++) {
	            Componente comp = celle[riga][col].getComponente();
	            if (comp != null && !(comp instanceof Cabina)) {
	                celle[riga][col].setNotUtilizzabile();
	            } else if (comp instanceof Cabina) {
	                celle[riga][col].setUtilizzabile();
	            }
	        }
	    }

	    // 2. Avvia una ricerca a partire dalla cabina (BFS)
	    Queue<Posizione> daVisitare = new LinkedList<>();
	    Set<Posizione> giaVisitati = new HashSet<>(); // <-- QUESTO ORA FUNZIONERÀ CORRETTAMENTE

	    Posizione posCabina = new Posizione(centroRighe, centroCol);
	    daVisitare.add(posCabina);
	    giaVisitati.add(posCabina);

	    while (!daVisitare.isEmpty()) {
	        Posizione posCorrente = daVisitare.poll();
	        Componente compCorrente = getCella(posCorrente).getComponente();
	        if (compCorrente == null) continue;

	        getCella(posCorrente).setUtilizzabile();

	        // 3. Controlla i vicini
	        Posizione[] posizioniVicini = {
	            new Posizione(posCorrente.getRiga(), posCorrente.getColonna() - 1), new Posizione(posCorrente.getRiga(), posCorrente.getColonna() + 1),
	            new Posizione(posCorrente.getRiga() - 1, posCorrente.getColonna()), new Posizione(posCorrente.getRiga() + 1, posCorrente.getColonna())
	        };
	        Lato[] latiComponenteCorrente = {Lato.sx, Lato.dx, Lato.up, Lato.dw};
	        Lato[] latiOppostiVicini = {Lato.dx, Lato.sx, Lato.dw, Lato.up};

	        for (int i = 0; i < 4; i++) {
	            Posizione posVicino = posizioniVicini[i];
	            if (posVicino.getRiga() >= 0 && posVicino.getRiga() < nRighe && 
	                posVicino.getColonna() >= 0 && posVicino.getColonna() < nColonne) {
	                
	                Componente compVicino = getCella(posVicino).getComponente();
	                
	                // Ora giaVisitati.contains() funziona correttamente!
	                if (compVicino != null && !giaVisitati.contains(posVicino) && 
	                    compCorrente.getConnettori(latiComponenteCorrente[i]).connection(compVicino.getConnettori(latiOppostiVicini[i]))) {
	                    
	                    daVisitare.add(posVicino);
	                    giaVisitati.add(posVicino);
	                }
	            }
	        }
	    }
	}

    //Metodo setCella AGGIORNATO
    public boolean setCella(Posizione pos, Componente componente) {
        int riga = pos.getRiga();
        int col = pos.getColonna();
        if (riga<0 || riga>=nRighe || col<0 || col>=nColonne)
            return false;

        Cella cellaTarget=celle[riga][col];
        if(cellaTarget.getComponente()==null) { // Permetti di piazzare anche su celle non utilizzabili per "agganciarsi" 
            cellaTarget.setComponente(componente); // Piazza il componente
            aggiornaStatoConnessioni(); // Ricalcola lo stato di tutta la nave
            return cellaTarget.isUtilizzabile();
        }else {
            // Cella già occupata
            return false;
        }
    }
    

     //Metodo eliminaComponente AGGIORNATO

    public void eliminaComponente(int riga, int col) {
        if (riga<0 || riga>=nRighe || col<0 || col>=nColonne)
            return;

        Cella cellaTarget=celle[riga][col];
        if(cellaTarget.getComponente()!=null && (riga!=centroRighe&&col!=centroCol)) {
            cellaTarget.setComponente(null); // Rimuovi il componente
            
            // La cella ora è vuota. Se prima era bloccata/rossa, ora dovrebbe tornare
            // ad essere uno spazio vuoto utilizzabile (azzurrino).
            // Potremmo voler resettare il suo stato a "utilizzabile" (ma vuota).
            // Questo dipende dalle tue regole (se una cella invalidata può essere riutilizzata).
            // Per ora, assumiamo che diventi una cella vuota normale.
            cellaTarget.setUtilizzabile();
            
            aggiornaStatoConnessioni(); // Ricalcola lo stato di tutta la nave
        }
    }*/
	

	
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
		System.out.print("\n");	
		for(int i=0;i<nRighe;i++) {
			for(int j=0;j<nColonne;j++)
				if(celle[i][j].isConnesso()) {
					System.out.print("1");
				}
				else
					System.out.print("0");
			System.out.print("\n");	
		}
	}
}
