package componenti;

import componenti.Equipaggio;

public class Suppvita {

	private Equipaggio tipo; //umano o alieno (giallo o viola)
	private int puntiVita; 
	
	public Suppvita(String id, Equipaggio tipo) {
		
		this.tipo = tipo;
		this.puntiVita = inizializzaPuntiVita(tipo);
		
	}
	
	private int inizializzaPuntiVita(Equipaggio tipo) {
		
		switch (tipo) {
		
			case umano:
				return 100;
				
			case alienoViola:
				return 120;
				
			case alienoGiallo:
				return 110;
				
			default: 
				return 100;
		
		}
		
	}
	
	public Equipaggio getTipo() {
			
			return tipo;
			
		}
	
	public int getPuntiVita() {
			
			return puntiVita;
		
	}
	
	public void subisciDanno(int danno) {
		
		puntiVita -= danno;
		if (puntiVita < 0) {
			
			puntiVita = 0;
			
		}
		
	}
	
	public void stampaInfo() {
		
		System.out.println("ID: " + id + ", tipo: " + tipo + ", Vita: " + puntiVita);
		
	}

	
}

