package galaxyTrucker;
import carte.Carte;
import carte.Livello;

public class main {

	public static void main(String[] args) {
        
        Carte Uno = new Carte(Livello.I, 5, 0, 100);
        
        System.out.println("Oggetto Carte creato con successo.");
        
        System.out.println(Uno);       
}

}