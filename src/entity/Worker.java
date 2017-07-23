package entity;

/**
 * this class is Worker Model which saves the Worker Model fields data, which extends User
 * @author G16
 *
 */
public class Worker extends User {
	
	/** employee number*/
	private int employeeNumber;
	
	/** worker id*/
	private int  workerId;
	
	/** worker first name*/
	private String workerFirstName;
	
	/** worker last name*/
	private String workerLastName;
	
	/** worker email*/
	private String workerEmail;
	
	/** worker affiliation*/
	private String Affiliation;
	
	/** worker role*/
	private String role;	
	
	/**
	 * Invoice Constructor
	 * this constructor initialize userName,userPass,userLogStatus,userType, userId fields 
	 */
	public Worker(String userName ,String userPass, int userLogStatus, String userType,int userId)
	{
		super(userName,userPass,userLogStatus,userType, userId);
	}
	/**
	 * Invoice Constructor
	 * this constructor initialize userName,userPass,userLogStatus,userType, userId fields role 
	 */
	public Worker(String userName ,String userPass, int userLogStatus, String userType,int userId, String role)
	{
		super(userName,userPass,userLogStatus,userType,userId);
		this.role = role;
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public int getEmployeeNumber() {
		return employeeNumber;
	}


	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}


	public int getWorkerId() {
		return workerId;
	}


	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}


	public String getWorkerFirstName() {
		return workerFirstName;
	}


	public void setWorkerFirstName(String workerFirstName) {
		this.workerFirstName = workerFirstName;
	}


	public String getWorkerLastName() {
		return workerLastName;
	}


	public void setWorkerLastName(String workerLastName) {
		this.workerLastName = workerLastName;
	}


	public String getWorkerEmail() {
		return workerEmail;
	}


	public void setWorkerEmail(String workerEmail) {
		this.workerEmail = workerEmail;
	}


	public String getAffiliation() {
		return Affiliation;
	}


	public void setAffiliation(String affiliation) {
		Affiliation = affiliation;
	}


	public String getRole1() {
		return role;
	}


	public void setRole1(String role) {
		this.role = role;
	}



	
}



