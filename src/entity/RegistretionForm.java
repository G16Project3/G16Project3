package entity;

/**
 * This class is QuarterReport entity which helps to  create  new  
 * @author G16
 *
 */

public class RegistretionForm {
	
	/** reg. form id*/
	private int registretionFormId;
	
	/** worker who reg. id*/
	private int addedByWorkerId;
	
	/** customer type*/
	private String customerType;
	
	/** customer first name*/
	private String firstName;
	
	/** customer last name*/
	private String lastName;
	
	/** customer id*/
	private int customerId;
	
	/** customer address*/
	private String address;
	
	/** customer email*/
	private String email;
	
	/** customer credit card*/
	private String creditCard;
	
	/** customer payment method*/
	private int cashPaymentOption;
	
	/** customer deal type*/
	private String purchasePlan;
	
	
	/** RegistretionForm constructor */
	public RegistretionForm() {
		this.registretionFormId = 9876;
		this.addedByWorkerId = 1111;
		this.customerId = 2222;
		this.customerType = "2";
		this.firstName = "firstName";
		this.lastName = "lastName";
		this.address = "address";
		this.email = "email@email.com";
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";
	}	
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = "2";
		this.firstName = "firstName";
		this.lastName = "lastName";
		this.address = "address";
		this.email = "email@email.com";
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = "firstName";
		this.lastName = "lastName";
		this.address = "address";
		this.email = "email@email.com";
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = "address";
		this.email = "email@email.com";
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName,String email) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = "address";
		this.email = email;
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName,String email,String address) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.creditCard = "12345678890";
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";	
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName,String email,String address,String creditCard) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.creditCard = creditCard;
		this.cashPaymentOption = 1;
		this.purchasePlan = "1";	
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName,String email,String address,String creditCard,int paymentOption) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.creditCard = creditCard;
		this.cashPaymentOption = paymentOption;
		this.purchasePlan = "1";	
	}
	
	/** RegistretionForm constructor */
	public RegistretionForm(int formId,int addBy,int customerId,String customerType,String firstName,
			String lastName,String email,String address,String creditCard,int paymentOption
			,String plan) {
		this.registretionFormId = formId;
		this.addedByWorkerId = addBy;
		this.customerId = customerId;
		this.customerType = customerType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.creditCard = creditCard;
		this.cashPaymentOption = paymentOption;
		this.purchasePlan = plan;	
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public void setRegistretionFormId(int formId){
		this.registretionFormId = formId;
	}
	public void setAddedByWorkerId (int addBy){
		this.addedByWorkerId = addBy;
	}
	public void setCustomerId (int customerId){
		this.customerId = customerId;
	}
	public void setCustomerType (String customerType){
		this.customerType = customerType;
	}
	public void setFirstName (String firstName){
		this.firstName = firstName;
	}
	public void setLastName (String lastName){
		this.lastName = lastName;
	}
	public void setAddress (String address){
		this.address = address;
	}
	public void setEmail (String email){
		this.email = email;
	}
	public void setCreditCard(String creditCard){
		this.creditCard = creditCard;
	}
	public void setCashPaymentOption(int paymentOption){
		this.cashPaymentOption = paymentOption;
	}
	public void setPurchasePlan(String plan){
		this.purchasePlan = plan;
	}
	
	public int getRegistretionFormId(){
		return this.registretionFormId;
	}
	public int getAddedByWorkerId (){
		return this.addedByWorkerId;
	}
	public int getCustomerId (){
		return this.customerId;
	}
	public String getCustomerType (){
		return this.customerType;
	}
	public String getFirstName (){
		return this.firstName;
	}
	public String getLastName (){
		return this.lastName;
	}
	public String getAddress (){
		return this.address;
	}
	public String getEmail (){
		return this.email;
	}
	public String getCreditCard(){
		return this.creditCard;
	}
	public int getCashPaymentOption(){
		return this.cashPaymentOption;
	}
	public String getPurchasePlan(){
		return this.purchasePlan ;
	}	

}
