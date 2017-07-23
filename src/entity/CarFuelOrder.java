package entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;

import client.MyFuelApp;
import controller.LoginController;
import controller.WorkerController;
import gui.MarketingManager;

/**
 * This class is CarFuelOrder entity which saves a CarFuelOrder for customer
 * @author G16
 *
 */
public class CarFuelOrder extends AbstractModel {
	
	
	/** max fuel rate*/
	private int maxFuelRate =  5;
	
	/** customer id*/
	private int customer_id;
	
	/** license plate number*/
	private String licensePlateNumber;
	
	/** quantity*/
	private int quantity;
	
	/** fuel type*/
	private String fuel_type;
	
	/** fuel station id*/
	private int fuel_station_id;
	
	/** order id*/
	private int order_id;
	
	/** message*/
	private String message;
	
	/** order status*/
	private int orderState=0;
	
	/** rate*/
	private Rates rate = null;
	

	/**
	 * CarFuelOrder Constructor
	 * this constructor initialize empty fields 
	 */
	public CarFuelOrder()
	{
		customer_id=0;
		licensePlateNumber=null;
		quantity=0;
		fuel_type = null;
		fuel_station_id=0;
		order_id=0;
	}
	
	/**
	 * CarFuelOrder Constructor
	 * this constructor initialize customerId & licensePlateNumber & quantity & fuelType & fuelStationId & orderId fields 
	 */
	public CarFuelOrder(int customer_id, String licensePlateNumber, int quantity, String fuel_type, int fuel_station_id,int order_id) {
		this.customer_id = customer_id;
		this.licensePlateNumber = licensePlateNumber;
		this.quantity = quantity;
		this.fuel_type = fuel_type;
		this.fuel_station_id = fuel_station_id;
		this.order_id = order_id;
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	public int getFuel_station_id() {
		return fuel_station_id;
	}
	public void setFuel_station_id(int fuel_station_id) {
		this.fuel_station_id = fuel_station_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getMaxFuelRate() {
		return maxFuelRate;
	}

	public void setMaxFuelRate(int maxFuelRate) {
		this.maxFuelRate = maxFuelRate;
	}
	
	/**
	 * Calculation rate 
	 * this func Calculat and return the price customer need to pay by his memberShip type
	 */
	public float culcRates(String type,int quantity,float rate){
		switch(type){
		case "Refueling occur":
				return quantity*rate;
		case "Subscription one car":
			return (float) (quantity*(rate*0.96));
		case "Subscription multiple car":
			return (float) (quantity*((rate*0.96)*0.9));
		case "Full subscription":
			return (float) (quantity*(((rate*0.96)*0.9)*0.97));
			}
		return -1;
		
	}

	public double makeOrder(CarFuelOrder temp, Customer customer, Rates rates) {
		
		DecimalFormat three = new DecimalFormat("#.###");
		DecimalFormat two = new DecimalFormat("#.##");
		DecimalFormat one = new DecimalFormat("#.#");
		three.setRoundingMode(RoundingMode.CEILING);
		two.setRoundingMode(RoundingMode.CEILING);
		one.setRoundingMode(RoundingMode.CEILING);
		
		if (customer == null)
			return Double.valueOf(one.format(temp.getQuantity()*rates.getMaxBenzine95()));
		
		if (customer.getMemberShipType() == "Refueling occur" || temp.getFuel_station_id() != customer.getFuelStationAllowed())
			return Double.valueOf(one.format(temp.getQuantity()*rates.getMaxBenzine95()));
			
		if (customer.getMemberShipType() == "single car")
			return Double.valueOf(three.format(temp.getQuantity()*rates.getMaxBenzine95()*0.96));
		
		if (customer.getMemberShipType() == "Full subscription")
			return temp.getQuantity()*rates.getMaxBenzine95()*0.93;
		
		if (customer.getMemberShipType() == "Subscription multiple car")
			return temp.getQuantity()*rates.getMaxBenzine95()*0.90;
		
		return -1;
		
	}
}
