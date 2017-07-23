package entity;

import java.io.Serializable;

/**
 * this class is Login Model  which saves the Login Model fields data ,which extends AbstractModel
 * @author G16
 *
 */
public class LoginMod extends AbstractModel {
	
	/**user ID of user*/
	private String userId;
	
	/**password of user */
	private String userPw;
	
	/**
	 * constructor
	 */
	public LoginMod(){
		
		userId = null;
		userPw = null;
	}
	
	/**
	 * constructor
	 */
	public LoginMod(String userId){
		
		this.userId = userId;
		userPw = null;
	}
	
public LoginMod(String userId ,String userpass){
		
		this.userId = userId;
		this.userPw = userpass;
	}
	
/**
 * get User ID
 * @return String
 */
	
	public String getUserId() {
		return userId;
	}
	
/**
 * set new User ID
 * @param userName
 */
	
	public void setUserName(String userName) {
		this.userId = userName;
	}
	
/**
 * get Password
 * @return String
 */
	
	public String getPassword() {
		return userPw;
	}
	
/**
 * set new Password
 * @param password
 */
	
	public void setPassword(String password) {
		userPw = password;
	}
	
}


