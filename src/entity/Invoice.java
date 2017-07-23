package entity;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is Invoice entity which saves a Invoice from a homeFuelorder & carFuelOrder
 * @author G16
 *
 */
public class Invoice extends AbstractModel {
	
	/** invoice id*/
	private int invoiceId;
	
	private int Urgent;
	
	/** station id*/
	private int stationId;
	
	/** payment date*/
	private Date paymentDate = new Date(); //change in DB to String
	
	/** order date*/
	private Date orderDate = new Date(); //same
	
	/** calculate price*/
	private float price;
	
	/** customer id*/
	private int customerId;
	
	/** quantity of fuel*/
	private int quantity;
	
	/** fuel type ordered*/
	private String fuelType;
	
	/** time*/
	private String time;
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Invoice Constructor
	 * this constructor initialize orderDate & price fields 
	 */
	public Invoice(Date orderDate, int price) {
		this.orderDate = orderDate;
		this.price = price;
	}

	/**
	 * Invoice Constructor
	 * this constructor initialize all fields 
	 */
	public Invoice(int invoiceId, int stationId, Date paymentDate,
			Date orderDate, float price, int customerId, int quantity,
			String fuelType, String time, SimpleDateFormat dateFormat) {
		this.invoiceId = invoiceId;
		this.stationId = stationId;
		this.paymentDate = paymentDate;
		this.orderDate = orderDate;
		this.price = price;
		this.customerId = customerId;
		this.quantity = quantity;
		this.fuelType = fuelType;
		this.time = time;
		this.dateFormat = dateFormat;
	}

	/**
	 * Invoice Constructor
	 * this constructor initialize empty fields 
	 */
	public Invoice(){
		this.invoiceId = 0;
		this.stationId = 0;
		this.paymentDate = null;
		this.orderDate = null;
		this.price = (float) 0.0;
		this.customerId = 0;
	    this.quantity = 0;
		this.fuelType = null;
		this.time = "";
	}
		
	/*****************************************Getters and Setters of fields *****************************/

	public void setInvoiceId(int invoiceId){
		this.invoiceId = invoiceId;
	}
	
	public void setStationId (int stationId){
		this.stationId = stationId;
	}
	public void setPaymentDate (Date paymentDay){
		this.paymentDate = paymentDay;
	}
	public void setOrderDate (Date orderDate){
		this.orderDate = orderDate;
	}
	public void setPrice (float price){
		this.price = price;
	}
	public void SetCustomerId (int customerId){
		this.customerId = customerId;
	}
	public void setQuantity (int quantity){
		this.quantity = quantity;
	}
	public void setFuelType (String fuelType){
		this.fuelType =fuelType;
	}
	public void setTime (String time){
		this.time = time;
	}
	
	public int getInvoiceId(){
		return invoiceId;
	}
	
	public int getStationId (){
		return this.stationId ;
	}
	public Date getPaymentDate (){
		return this.paymentDate ;
	}
	public Date getOrderDate (){
		return this.orderDate ;
	}
	public float getPrice (){
		return this.price;
	}
	public int getCustomerId (){
		return this.customerId ;
	}
	public int getQuantity (){
		return this.quantity;
	}
	public String getFuelType (){
		return this.fuelType ;
	}
	public String getTime (){
		return this.time ;
	}	
	public int getUrgent() {
		return Urgent;
	}

	public void setUrgent(int urgent) {
		Urgent = urgent;
	}
	
	
}
