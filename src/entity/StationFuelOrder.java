package entity;
/**
 * This class is StationFuelOrder entity which saves a Car for customer
 * @author G16
 *
 */
public class StationFuelOrder {
	
	/** stations fuel order id*/
	private int stationFuelOrderId;
	
	/** stations id*/
	private int stationId;
	
	/** stations fuel order amount*/
	private int orderAmount;
	
	/** stations fuel order supplier*/
	private String supplyer;
	
	/** stations fuel order fuel type*/
	private String fuelType;
	
	
	/**
	 * StationFuelOrder Constructor
	 * this constructor initialize empty fields 
	 */
	
	public StationFuelOrder(){
		this.stationFuelOrderId = 0;
		this.stationId =0;
		this.orderAmount = 0;
		this.supplyer = "supplyer";
		this.fuelType = null;
	}
	
	/**
	 * StationFuelOrder Constructor
	 * this constructor initialize fuleOrderId, stationId and empty fields 
	 */
	public StationFuelOrder(int fuleOrderId,int stationId){
		this.stationFuelOrderId = fuleOrderId;
		this.stationId = stationId;
		this.orderAmount = 1000;
		this.supplyer = "supplyer";
		this.fuelType = "95";
	}
	
	/**
	 * StationFuelOrder Constructor
	 * this constructor initialize fuleOrderId, stationId amount and empty fields 
	 */
	public StationFuelOrder(int fuelOrderId,int stationId, int amount){
		this.stationFuelOrderId = fuelOrderId;
		this.stationId = stationId;
		this.orderAmount = amount;
		this.supplyer = "supplyer";
		this.fuelType = "95";
	}
	
	/**
	 * StationFuelOrder Constructor
	 * this constructor initialize fuleOrderId, stationId amount ,supplyer and empty fields 
	 */
	public StationFuelOrder(int fuelOrderId,int stationId, int amount,String supplyer){
		this.stationFuelOrderId = fuelOrderId;
		this.stationId = stationId;
		this.orderAmount = amount;
		this.supplyer = supplyer;
		this.fuelType = "95";
	}
	
	/**
	 * StationFuelOrder Constructor
	 * this constructor initialize fuleOrderId, stationId amount ,supplyer fuleType fields 
	 */
	public StationFuelOrder(int fuelOrderId,int stationId, int amount,String supplyer,String fuleType){
		this.stationFuelOrderId = fuelOrderId;
		this.stationId = stationId;
		this.orderAmount = amount;
		this.supplyer = supplyer;
		this.fuelType = fuleType;
	}
	
	/***************************************** Setters of fields *****************************/

	public void setStationFuelOrderId(int fuelOrderId){
		this.stationFuelOrderId = fuelOrderId;
	}
	public void setStationId(int stationId){
		this.stationId = stationId;
	}
	public void setOrderAmount(int amount){
		this.orderAmount = amount;
	}
	public void setSupplyer (String supplyer){
		this.supplyer = supplyer;
	}
	public void setFuelType(String fuelType){
		this.fuelType = fuelType;
	}
	
	public int getStationFuelOrderId(){
		return this.stationFuelOrderId;
	}
	public int getStationId(){
		return this.stationId;
	}
	public int getOrderAmount(){
		return this.orderAmount;
	}
	public String getSupplyer (){
		return this.supplyer;
	}
	public String getFuelType(){
		return this.fuelType;
	}
	
}
