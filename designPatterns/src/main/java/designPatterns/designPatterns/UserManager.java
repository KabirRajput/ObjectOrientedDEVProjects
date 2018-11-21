package designPatterns.designPatterns;

public class UserManager {

	IStorage userStorage = StorageFactory.getStorage();
	userStorage.delete(1001); 

}
 