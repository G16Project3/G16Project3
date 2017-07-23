package entity;
/**
 * This class is SaleReactionReport entity which saves a Car for customer
 * @author G16
 *
 */
public class SaleReactionReport {
	
	/** sale reaction report ID */
	private int saleReactionReportID;
	
	/** number of customers on sale */
	private int numOfCutomersOnSale;
	
	/** TBD */
	private String TBD; 
	
	
	/**
	 * SaleReactionReport Constructor
	 * this constructor initialize empty fields 
	 */
	public SaleReactionReport(){
		this.saleReactionReportID = 0000;
		this.numOfCutomersOnSale = 8;
		this.TBD = "12";
	}
	
	/**
	 * SaleReactionReport Constructor
	 * this constructor initialize saleReportId, numOfcustomerSale and empty fields 
	 */
	public SaleReactionReport(int saleReportId,int numOfcustomerSale){
		this.saleReactionReportID = saleReportId;
		this.numOfCutomersOnSale = numOfcustomerSale;
		this.TBD = "12";
	}
	
	/**
	 * SaleReactionReport Constructor
	 * this constructor initialize saleReportId, numOfcustomerSale and TBD fields 
	 */
	public SaleReactionReport(int saleReportId,int numOfcustomerSale,String TBD){
		this.saleReactionReportID = saleReportId;
		this.numOfCutomersOnSale = numOfcustomerSale;
		this.TBD = TBD;
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public void setSaleReactionReportID(int saleReportId){
		this.saleReactionReportID = saleReportId;		
	}
	public void setNumOfCutomersOnSale(int numOfcustomerSale){
		this.numOfCutomersOnSale = numOfcustomerSale;
	}
	public void setTBD(String TBD){
		this.TBD = TBD;
	}
	
	public int getSaleReactionReportID(){
		return this.saleReactionReportID;		
	}
	public int getNumOfCutomersOnSale(){
		return this.numOfCutomersOnSale;
	}
	public String getTBD(){
		return this.TBD ;
	}
	
}
