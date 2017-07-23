package entity;

/**
 * This class is Car entity which saves a Car for customer
 * @author G16
 *
 */
public class Car extends AbstractModel {
	
	/** license plate number */
	private String licenseplatenumber;
	
	/** fuel type */
	private String FuelType;
	
	/** owner id */
	private int ownerid;
	
	/** car type */
	private String CarType;
	
	/** car NFC */
	private String carNFC;
	
	/**
	 * Car Constructor
	 * this constructor initialize empty fields 
	 */
	public Car()
	{
		this.licenseplatenumber=null;
		this.ownerid=0;
		this.FuelType=null;
		this.CarType=null;
		this.carNFC = null;
		
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize ownerId fields 
	 */
	public Car(int ownerid)
	{
		this.ownerid=ownerid;
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize ownerId & fuelType fields 
	 */
	public Car(int ownerid , String FuelType)
	{
		this.ownerid=ownerid;
		this.FuelType=FuelType;
		
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize ownerId & fuelType &carType fields 
	 */
	public Car(int ownerid , String FuelType , String CarType)
	{
		this.ownerid=ownerid;
		this.FuelType=FuelType;
		this.CarType=CarType;
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize ownerId & fuelType & carType & licenseplatenumber fields 
	 */
	public Car(int ownerid , String FuelType , String CarType , String licenseplatenumber)
	{
		this.ownerid=ownerid;
		this.FuelType=FuelType;
		this.CarType=CarType;
		this.licenseplatenumber=licenseplatenumber;

	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public String getCarNFC() {
		return carNFC;
	}
	public void setCarNFC(String carNFC) {
		this.carNFC = carNFC;
	}
	public int getownerid()
	{
		return this.ownerid;
	}

	public String getFuelType()
	{
		return this.FuelType;
	}
	
	public String getCarType()
	{
		return this.CarType;
	}
	
	public String getlicenseplatenumber()
	{
		return this.licenseplatenumber;
	}
	
	public void setFuelType(String FuelType){
		this.FuelType=FuelType;
	}
	
	public void setownerid(int ownerid){
		this.ownerid=ownerid;
	}
	
	public void setCarType(String CarType){
		this.CarType=CarType;
	}
	

	public void setlicenseplatenumber(String licenseplatenumber)
	{
		this.licenseplatenumber=licenseplatenumber;
	}

}
