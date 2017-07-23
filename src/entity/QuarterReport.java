package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is QuarterReport entity which helps to  create  quarter report
 * @author G16
 *
 */
public class QuarterReport {
	
	/** report quarter id*/
	private int reportQuarterId;
	
	/** invoice id*/
	private int invoicesIds;
	private double totalIncome;
	private String Quarter ;
	private int year;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	
	/**
	 * QuarterReport Constructor
	 * this constructor initialize empty fields 
	 */
	public QuarterReport(){
		this.invoicesIds = 1234;
		this.totalIncome = 1000;
		this.year= Calendar.getInstance().get(Calendar.YEAR);;

		
	}
	
	/**
	 * QuarterReport Constructor
	 * this constructor initialize reportId, invoicesId 
	 */
	public QuarterReport(int reportId,int invoicesId){
		this.reportQuarterId = reportId;
		this.invoicesIds = invoicesId;
		this.totalIncome = 1000;

		
	}
	
	/**
	 * QuarterReport Constructor
	 * this constructor initialize reportId, invoicesId ,income
	 */
	public QuarterReport(int reportId,int invoicesId,int income){
		this.reportQuarterId = reportId;
		this.invoicesIds = invoicesId;
		this.totalIncome = income;

		
	}
	
	/**
	 * QuarterReport Constructor
	 * this constructor initialize reportId, invoicesId ,income ,startDate
	 */
	public QuarterReport(int reportId,int invoicesId,int income,Date startDate){
		this.reportQuarterId = reportId;
		this.invoicesIds = invoicesId;
		this.totalIncome = income;

		
	}
	
	/**
	 * QuarterReport Constructor
	 * this constructor initialize reportId, invoicesId ,income ,startDate ,endDate
	 */
	public QuarterReport(int reportId,int invoicesId,int income,Date startDate,Date endDate){
		this.reportQuarterId = reportId;
		this.invoicesIds = invoicesId;
		this.totalIncome = income;

		
	}
	
	/*****************************************Getters and Setters of fields *****************************/

	public void setReportQuarterId (int reportId){
		this.reportQuarterId = reportId;
	}
	public void setInvoicesIds (int invoicesId){
		this.invoicesIds = invoicesId;
	}
	public void setTotalIncome (double income){
		this.totalIncome = income;
	}

	
	public int getReportQuarterId (){
		return this.reportQuarterId;
	}
	public int getInvoicesIds (){
		return this.invoicesIds;
	}
	public double getTotalIncome (){
		return this.totalIncome;
	}


	public String getQuarter() {
		return Quarter;
	}

	public void setQuarter(String quarter) {
		Quarter = quarter;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	
}
