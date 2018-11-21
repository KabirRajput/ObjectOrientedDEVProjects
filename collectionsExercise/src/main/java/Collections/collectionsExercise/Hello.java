package Collections.collectionsExercise;

import java.util.HashMap;

public class Hello {

	private HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	public void addString(String s) {
		
		for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
		    Integer val = map.get(c);
		    if (val != null) {
		        map.put(c, new Integer(val + 1));
		    }
		    else {
		       map.put(c, 1);
		   }
		}
		
	}

}
