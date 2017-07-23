package entity;

/**
 * this class is dummy Model which saves the Dummy Model fields data, which extends AbstractModel
 * @author G16
 *
 */
public class Dummy extends AbstractModel{
	
	/** dummy string*/
	private String dummyString ;
	
	/** dummy int*/
	private int dummyInt;
	
	/** dummy username*/
	private String usern;
		
	/** dummy type*/
	private String type;
			
	// ----------------------Contractors begin -------------------//
	
	/** Dummy constructor*/
	public Dummy() {
		
	}

	/** Dummy constructor*/
	public Dummy(String dummyString) {
		this.dummyString = dummyString;
	}

	/** Dummy constructor*/
	public Dummy(int dummyInt) {
		this.dummyInt = dummyInt;
	}

	/** Dummy constructor*/
	public Dummy(String dummyString, int dummyInt) {
		this.dummyString = dummyString;
		this.dummyInt = dummyInt;
	}

	/** Dummy constructor*/
	public Dummy(String usern, String type) {
		this.usern = usern;
		this.type = type;
	}


	// ----------------------Contractors end ---------------------//
	//----------------------- Getters setters --------------------//
	
	public String getDummyString() {
		return dummyString;
	}

	public void setDummyString(String dummyString) {
		this.dummyString = dummyString;
	}


	public int getDummyInt() {
		return dummyInt;
	}


	public void setDummyInt(int dummyInt) {
		this.dummyInt = dummyInt;
	}

	public String getUsern() {
		return usern;
	}

	public void setUsern(String usern) {
		this.usern = usern;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
