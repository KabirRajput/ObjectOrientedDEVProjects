package UserReg.userReg;

public interface UserPersistance {

	void writeUser(User bob);

	User readUser(int i);

}
