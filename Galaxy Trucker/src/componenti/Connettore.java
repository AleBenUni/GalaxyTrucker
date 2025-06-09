package componenti;

public enum Connettore {
	assente,singolo, doppio, multiplo;
	
	public boolean connection(Connettore connettore) {
		if(this==multiplo&&connettore!=assente || this!=assente&&connettore==multiplo)
			return true;
		else if(this==assente&&connettore==assente)
			return true;
		else if(this==connettore)
			return true;
		else
			return false;
	}
}
