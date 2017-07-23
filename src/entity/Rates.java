package entity;

/**
 * This class is Rates entity saves the rates 
 * @author G16
 *
 */
public class Rates extends AbstractModel {
	
	
	/** max/base motor fuel rate*/
	private float maxmotorcycleFuel;
	
	/** max/base benzine95 fuel rate*/
	private float maxBenzine95;
	
	/** max/base diesel fuel rate*/
	private float maxDiesel;
	
	/** max/base home fuel rate*/
	private float maxHomeFuel;
	
	/** motorcycle fuel update status*/
	private float motorcycle_fuel_update=-1;
	
	/** benzine95 fuel update status*/
	private float benzine95_update=-1;
	
	/** diesel fuel update status*/
	private float diesel_update=-1;
	
	/** homefuel fuel update status*/
	private float home_fuel_update=-1;
	
	
	/*****************************************Getters and Setters of fields *****************************/

	public float getMotorcycle_fuel_update() {
		return motorcycle_fuel_update;
	}
	public void setMotorcycle_fuel_update(float motorcycle_fuel_update) {
		this.motorcycle_fuel_update = motorcycle_fuel_update;
	}
	public float getBenzine95_update() {
		return benzine95_update;
	}
	public void setBenzine95_update(float benzine95_update) {
		this.benzine95_update = benzine95_update;
	}
	public float getDiesel_update() {
		return diesel_update;
	}
	public void setDiesel_update(float diesel_update) {
		this.diesel_update = diesel_update;
	}
	public float getHome_fuel_update() {
		return home_fuel_update;
	}
	public void setHome_fuel_update(float home_fuel_update) {
		this.home_fuel_update = home_fuel_update;
	}
	public float getMaxmotorcycleFuel() {
		return maxmotorcycleFuel;
	}
	public void setMaxmotorcycleFuel(float maxmotorcycleFuel) {
		this.maxmotorcycleFuel = maxmotorcycleFuel;
	}
	public float getMaxBenzine95() {
		return maxBenzine95;
	}
	public void setMaxBenzine95(float maxBenzine95) {
		this.maxBenzine95 = maxBenzine95;
	}
	public float getMaxDiesel() {
		return maxDiesel;
	}
	public void setMaxDiesel(float maxDiesel) {
		this.maxDiesel = maxDiesel;
	}
	public float getMaxHomeFuel() {
		return maxHomeFuel;
	}
	public void setMaxHomeFuel(float maxHomeFuel) {
		this.maxHomeFuel = maxHomeFuel;
	}
	/**
	 * Car Constructor
	 * this constructor initialize  motorcycle_fuel_update,  benzine95_update,  diesel_update,  home_fuel_update fields 
	 */
	public Rates(float motorcycle_fuel_update, float benzine95_update, float diesel_update, float home_fuel_update) {
		this.motorcycle_fuel_update = motorcycle_fuel_update;
		this.benzine95_update = benzine95_update;
		this.diesel_update = diesel_update;
		this.home_fuel_update = home_fuel_update;
	}
	/**
	 * Car Constructor
	 * this constructor initialize empty fields 
	 */
	public Rates()
	{
		this.motorcycle_fuel_update = 0;
		this.benzine95_update = 0;
		this.diesel_update = 0;
		this.home_fuel_update = 0;
		this.maxmotorcycleFuel = 0;
		this.maxBenzine95 = 0;
		this.maxDiesel = 0;
		this.maxHomeFuel = 0;
	}
	
	
}
