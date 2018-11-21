package Collections.collectionsExercise;

import java.util.HashSet;
import java.util.Set;

public class People {
	
	private Set<String> hash_Set = new HashSet<String>();
	
	public Set<String> addNames(String...name) {

		for(String n: name)
		{
			hash_Set.add(n);
		}
		
		return hash_Set;
	}

}
