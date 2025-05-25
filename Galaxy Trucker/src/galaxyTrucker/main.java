package galaxyTrucker;
import carte.Carta;
import carte.Mazzo;

public class main {

	public static void main(String[] args) {
        
		//questo main è solo una prova del file csv. Può essere modificato senza preavviso
		
		Mazzo mazzo = new Mazzo(false);
        
       Carta pescata = mazzo.pescadalMazzo();
       // System.out.printf("\nHa pescato--> " + pescata);
        mazzo.rivelaCarteMazzo();
        mazzo.svuotaMazzo();
        mazzo.rivelaCarteMazzo();
}

}