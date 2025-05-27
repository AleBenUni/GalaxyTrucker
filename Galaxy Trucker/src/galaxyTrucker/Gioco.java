package galaxyTrucker;
import carte.Livello;
import java.util.Scanner;


public class Gioco {
	
	private Nave[] nave;
	private Plancia plancia;
	private Livello livello=null;
	private int nGiocatori;
	
	private Gioco(int nGiocatori, Livello livello) {
		nave=new Nave[nGiocatori];
		plancia=new Plancia(livello);
		for(int i=0;i<nGiocatori;i++)
			nave[i]=new Nave(livello);
	}
	
	public void gioca() {
		int difesa=0;
		Scanner scanner=new Scanner(System.in);
		do {
			scanner.nextLine();
			System.out.println("Inserire numero giocatori");
			nGiocatori = scanner.nextInt();
		}while(nGiocatori<=1||nGiocatori>4);
		
		do {
			scanner.nextLine();
			System.out.println("Inserire Livello");
			livello=Livello.toLivello(scanner.nextLine());
		}while(livello==null);
		
		Gioco gioco=new Gioco(nGiocatori,livello);		//Revisionare
		
		while(true) {	//Gestisce turni costruzione navi
			
			
			
			break;
			
		}
		
		while(true) {	//Gestisce pescaggio carte
			
			//Viene pescata una carta dalla quale posso difendermi e  mi dice dove mi colpirebbe
			int costoDifesa=0;
			for(int i=0;i<nGiocatori;i++) {
				do {
					scanner.nextLine();
					System.out.println("Vuoi difenderti? (0 || 1)");
					difesa = scanner.nextInt();
				}while(difesa<0||difesa>1);
				if(difesa==1) {
					int energiaDisponibile=nave[i].getEnergiaNave();
					if(energiaDisponibile>costoDifesa) {
						//Componente distrutto
						System.out.println("Non hai abbastanza energia per difenderti");
					}
					else
						nave[i].minusEnergiaNave(costoDifesa);
				}
			}
				
			
			break;
		}
		
		
		
		
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
	
	public Plancia getPlancia() {
		return plancia;
	}
	
}
