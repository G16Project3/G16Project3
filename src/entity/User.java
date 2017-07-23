package entity;

/**
 * this class is User Model  which saves the User Model fields data ,which extends AbstractModel
 * @author G16
 *
 */
public class User extends AbstractModel {
	
	/** user name*/
	private String userName;
	
	/** user password*/
	private String userPw;
	
	/** user log status*/
	private int userLogStatus;
	
	/** user type (either worker or customer)*/
	private String userType;
	
	/** user id*/
	private int userId;
	
	/** user role*/
	private String role;
	

	/**
	 * User Constructor
	 * this constructor initialize empty fields 
	 */
	public User()
	{
		this.userLogStatus = 0;
	}
	/**
	 * User Constructor
	 * this constructor initialize userName, userPass,  userType fields 
	 */
	public User(String userName,String userPass, String userType)
	{
		this.userName=userName;
		this.userPw=userPass;
		this.userLogStatus = 0;
		this.userType = userType;
	}
	/**
	 * User Constructor
	 * this constructor initialize userName, userPass,  userType ,userId fields 
	 */
	public User(String userName,String userPass, int userLogStatus, String userType , int userId) {
		this.userName=userName;
		this.userPw=userPass;
		this.userLogStatus = userLogStatus;
		this.userType = userType;
		this.userId = userId;
	}
	/**
	 * User Constructor
	 * this constructor initialize userName, userPass,  userType ,userId fields 
	 */
	public User(String userName,String userPass, int userLogStatus, String userType , int userId,String role) {
		this.userName=userName;
		this.userPw=userPass;
		this.userLogStatus = userLogStatus;
		this.userType = userType;
		this.userId = userId;
		this.role = role;
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public int getUserLogStatus() {
		return userLogStatus;
	}

	public void setUserLogStatus(int userLogStatus) {
		this.userLogStatus = userLogStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}