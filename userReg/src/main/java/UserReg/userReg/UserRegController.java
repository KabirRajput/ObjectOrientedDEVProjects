package UserReg.userReg;

public class UserRegController {

	UserPersistance userPersistance;
	
	public UserRegController(UserPersistance userPersistance) {
		this.userPersistance = userPersistance;
	}

	public void addUser(User user) {
		userPersistance.writeUser(user); 
	}

	public User getUser(int id) {
		return userPersistance.readUser(id);
	}

	

}
