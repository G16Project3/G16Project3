package entity;

/**
 * this class is Customer Model which saves the Customer Model fields data, which extends User
 * @author G16
 *
 */
public class Customer extends User {
	
	/** customer number*/
	private int customerNumber;
	
	/** customer id*/
	private int customerId;
	
	/** customer first name*/
	private String customerFirstName;
	
	/** customer last name*/
	private String customerLastName;
	
	/** customer email*/
	private String customerEmail;
	
	/** customer address*/
	private String customerAddress;
	
	/** customer phone number*/
	private String customerPhone;
	
	/** customer credit card number*/
	private String creditCardNumber;
	
	/** customer membership type*/
	private String memberShipType;
	
	/** customer payment method*/
	private String payment;
	
	private int fuelStationAllowed;

	/**
	 * Customer Constructor
	 * this constructor initialize empty fields 
	 */
	public Customer(){
		super();
		this.customerNumber = 0;
		this.customerId = 0;
		this.customerFirstName = null;
		this.customerLastName = null;
		this.customerEmail = null;
		this.customerAddress = null;
		this.customerPhone = null;
		creditCardNumber = null;
		this.memberShipType = null;
		this.payment = null;
	}
	
	/**
	 * Customer Constructor
	 * this constructor heir data from user 
	 */
	public Customer(String userName ,String userPass, int userLogStatus, String userType, int userId)
	{
		super(userName,userPass,userLogStatus,userType,userId);

	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public int getFuelStationAllowed() {
		return fuelStationAllowed;
	}

	public void setFuelStationAllowed(int fuelStationAllowed) {
		this.fuelStationAllowed = fuelStationAllowed;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getMemberShipType() {
		return memberShipType;
	}

	public void setMemberShipType(String memberShipType) {
		this.memberShipType = memberShipType;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	
	
	
	
}



