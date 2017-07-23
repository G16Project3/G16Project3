package entity;

/**
 * This class is FuelCompany entity which saves a FuelCompanys 
 * @author G16
 *
 */
public class FuelCompany extends AbstractModel {

	/** fuel Company Id */
	private int fuelCompanyId;
	
	/** fuel Company name */
	private String fuelCompanyName;
	
	
	/**
	 * Car Constructor
	 * this constructor initialize empty fields 
	 */
	public FuelCompany(){
		this.fuelCompanyId = 222;
		this.fuelCompanyName = "first company";

	}
	
	/**
	 * Car Constructor
	 * this constructor initialize companyId and fuelCompanyName
	 */
	public FuelCompany(int companyId){
		this.fuelCompanyId = companyId;
		this.fuelCompanyName = "first company";
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize companyName ,fuelCompanyName and fuelCompanyId  
	 */
	public FuelCompany(String companyName){
		this.fuelCompanyId = 222;
		this.fuelCompanyName = companyName;
		
	}
	
	/**
	 * Car Constructor
	 * this constructor initialize companyName and companyId 
	 */
	public FuelCompany(int companyId, String companyName){
		this.fuelCompanyId = companyId;
		this.fuelCompanyName = companyName;
		
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	
	public void setFuelCompanyId(int companyId){
		this.fuelCompanyId = companyId;
	}
	
	public void setFuelCompanyName(String companyName){
		this.fuelCompanyName = companyName;
	}
	
	public int getFuelCompanyId(){
		return this.fuelCompanyId;
	}
	
	public String getFuelCompanyName(){
		return this.fuelCompanyName;
	}
}
