package carte;

import java.util.Map;

public class Mazzo {

	try {
		String CSV = "src\\carte\\carte.csv";
		Map<Integer, Carta> mazzo = CaricaCSV.loadMap(CSV);
	}catch (Exception e) {
        e.printStackTrace();
	}
	
	//Il try‐catch ha bisogno di trovarsi dentro una funzione o almeno dentro un main. Ripensa alla gestione dei mazzi
	
}

