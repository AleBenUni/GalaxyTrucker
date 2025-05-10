package galaxyTrucker;
import carte.Carta;
import carte.Livello;
import java.util.Map;
import carte.CaricaCSV;

public class main {

	public static void main(String[] args) {
        
		//questo main è solo una prova del file csv. Può essere modificato senza preavviso
		
		try {
		String CSV = "src\\carte\\carte.csv";
		Map<Integer, Carta> mazzo = CaricaCSV.loadMap(CSV);
        System.out.printf("Caricate " + mazzo.size() + " carte\n\n");
        
        for (Map.Entry<Integer, Carta> i : mazzo.entrySet()) {
            System.out.printf("ID: " + i.getKey() + " " + i.getValue() + "\n");
        }
	} catch (Exception e) {
        e.printStackTrace();
    }
        
}

}