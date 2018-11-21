package generics.generic;

import java.util.ArrayList;
import java.util.List;

public class CatalogueGeneric<T extends Catalogueable> {
	
	List<T> items = new ArrayList<T>();

	public void add(T t) {
		items.add(t);
	}

	public T getT() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public T findT(int id) {
		for(T i : items) {
			if(i.getId()==id)
				return i;
		}
		return null;
	}
	

}
