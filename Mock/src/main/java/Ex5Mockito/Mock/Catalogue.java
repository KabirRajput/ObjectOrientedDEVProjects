package Ex5Mockito.Mock;

public class Catalogue {
	
	private ReadItemCommand readItemCommand;

	public Catalogue(ReadItemCommand readItemCommand) {
		// TODO Auto-generated constructor stub
	}
	public Catalogue() {
		
	}

	int getAllBooks() {
		return readItemCommand.readAll();
	}

}
