package carte;

import java.util.HashMap;

public class MazzoVuoto extends Mazzo{
	
	public MazzoVuoto() {
		
        mazzo = new HashMap<>();
        System.out.printf(", questo in particolare e' vuoto.");
    }
}
