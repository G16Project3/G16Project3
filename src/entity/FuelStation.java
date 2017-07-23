package entity;

/**
 * this class is Fuel Station Model which saves the FuelStation fields data
 * @author G16
 *
 */

public class FuelStation extends FuelCompany{
	
	/** fuel station id*/
	private int fuelStationId;
	
	/** fuel station manager id*/
	private int stationMenagerID;
	
	/** fuel station name*/
	private String stsationName;
	
	/** fuel station benzine95 amount*/
	private int fuel95Amount;
	
	/** fuel station motors fuel amount*/
	private int motorsFuelAmount;
	
	/** fuel station diesel amount*/
	private int dieselAmount;
	
	/** fuel station minimum level of fuel amount before notification*/
	private int minLevelAmount;
	
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize empty fields 
	 */
	public FuelStation(){
		super();
		this.fuelStationId = 111;
		this.stsationName = "firstStation";
		this.fuel95Amount = 1000;
		this.motorsFuelAmount = 1000;
		this.dieselAmount = 1000;
		this.minLevelAmount = 250;
	}
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize stationId ,  stationManger,  stationCompny, stationName
	 */
	public FuelStation(int stationId, int stationManger, int stationCompny,String stationName){
		super(stationCompny);
		this.fuelStationId = stationId;
		this.stationMenagerID = stationManger;
		this.stsationName = stationName;
		this.fuel95Amount = 1000;
		this.motorsFuelAmount = 1000;
		this.dieselAmount = 1000;
		this.minLevelAmount = 250;
	}
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize stationId ,  stationManger,  stationCompny, stationName fuel95Amount
	 */
	public FuelStation(int stationId, int stationManager,int stationCompany,String stationName,
			int fuel95Amount){
		super(stationCompany);
		this.fuelStationId = stationId;
		this.stationMenagerID = stationManager;
		this.stsationName = stationName;
		this.fuel95Amount = fuel95Amount;
		this.motorsFuelAmount = 1000;
		this.dieselAmount = 1000;
		this.minLevelAmount = 250;
	}
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize stationId ,  stationManger,  stationCompny, stationName , fuel95Amountfuel95Amount, motorsAmount
	 */
	public FuelStation(int stationId, int stationManager,int stationCompany,String stationName,
			int fuel95Amount,int motorsAmount){
		super(stationCompany);
		this.fuelStationId = stationId;
		this.stationMenagerID = stationManager;
		this.stsationName = stationName;
		this.fuel95Amount = fuel95Amount;
		this.motorsFuelAmount = motorsAmount;
		this.dieselAmount = 1000;
		this.minLevelAmount = 250; 
	}
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize stationId ,  stationManger,  stationCompny, stationName , fuel95Amountfuel95Amount, motorsAmount , dieselAmount
	 */
	public FuelStation(int stationId, int stationManager,int stationCompany,String stationName,
			int fuel95Amount,int motorsAmount,int dieselAmount){
		super(stationCompany);
		this.fuelStationId = stationId;
		this.stationMenagerID = stationManager;
		this.stsationName = stationName;
		this.fuel95Amount = fuel95Amount;
		this.motorsFuelAmount = motorsAmount;
		this.dieselAmount = dieselAmount;
		this.minLevelAmount = 250;
	}
	
	/**
	 * FuelStation Constructor
	 * this constructor initialize stationId ,  stationManger,  stationCompny, stationName , fuel95Amountfuel95Amount, motorsAmount , dieselAmount
	 */
	public FuelStation(int stationId, int stationManager,int stationCompany,String stationName,
			int fuel95Amount,int motorsAmount,int dieselAmount,int minLevel){
		super(stationCompany);
		this.fuelStationId = stationId;
		this.stationMenagerID = stationManager;
		this.stsationName = stationName;
		this.fuel95Amount = fuel95Amount;
		this.motorsFuelAmount = motorsAmount;
		this.dieselAmount = dieselAmount;
		this.minLevelAmount = minLevel;
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	
	public void setFuelStationId(int stationName){
		this.fuelStationId = stationName;
	}
	public void setStationMenagerID(int stationManager){
		this.stationMenagerID = stationManager;
	}
	public void setStationOfCompanyId(int stationCompanyId){
		super.setFuelCompanyId(stationCompanyId);
	}
	public void setStsationName(String stationName){
		this.stsationName = stationName;
	}
	public void setFuel95Amount(int fuel95Amount){
		this.fuel95Amount = fuel95Amount;
	}
	public void setMotorsFuelAmount(int motorAmount){
		this.motorsFuelAmount = motorAmount;
	}
	public void setDieselAmount(int dieselAmount){
		this.dieselAmount = dieselAmount;
	}
	public void setMinLevelAmount(int minLevel){
		this.minLevelAmount = minLevel;
	}
	
	public int getFuelStationId(){
		return this.fuelStationId;
	}
	public int getStationMenagerID(){
		return this.stationMenagerID ;
	}
	public int getStationOfCompanyId(){
		return super.getFuelCompanyId();
	}
	public String getStsationName(){
		return this.stsationName;
	}
	public int getFuel95Amount(){
		return this.fuel95Amount;
	}
	public int getMotorsFuelAmount(){
		return this.motorsFuelAmount;
	}
	public int getDieselAmount(){
		return this.dieselAmount;
	}
	public int getMinLevelAmount(){
		return this.minLevelAmount;
	}
	
	public boolean checkFuelOrder(FuelStation temp,int amount,String fueltype){
		
	
		if(amount < 0)
			return false;
		if(temp.getStationMenagerID() == -1)
			return false;
		switch(fueltype){
		case "Benzine95":
			break;
		case "Diesel":
			break;
		case "Motors":
			break;
		default:
			 return false;
	}
		return true;
		
	}

	public int checkAdded(FuelStation station1, int amount,String fueltype) {
		if(amount < 0)
			return -1;
		switch(fueltype){
		case "Benzine95":
			 station1.setFuel95Amount( station1.getFuel95Amount() + amount);
			 return station1.getFuel95Amount();
		case "Diesel":
			 station1.setDieselAmount(station1.getDieselAmount() + amount);
			 return station1.getDieselAmount();
		case "Motors":
			 station1.setMotorsFuelAmount( station1.getMotorsFuelAmount() + amount);
			 return station1.getMotorsFuelAmount();
		default:
			 return -1;
	}
	}

}
