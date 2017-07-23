package entity;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is Campaign entity which saves & create a Campaign 
 * @author G16
 *
 */
public class Campaign extends AbstractModel {
	
	
	/** campaign id */
	private int campaignId;
	
	/** campaign name */
	private String campaigName;
	
	/** discount percent */
	private int discountPercent; 
	
	/** campaign status */
	private String campaignStatus;
	
	/** start day */
	private Date startDay = new Date();
	
	/** end day */
	private Date endDay= new Date();
	
	/** start hour */
	private Date startHour;
	
	/** end hour */
	private Date endtHour;
	
	/**
	 * Campaign Constructor
	 * this constructor initialize empty fields 
	 */
	public Campaign()
	{
		this.campaignId=0;
		this.campaigName="";
		this.discountPercent=0;
		this.startDay=null;
		this.endDay=null;
		this.setStatus("Not approved");
	
	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaign id fields 
	 */
	public Campaign(int campaignId)
	{
		this.campaignId=campaignId;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName fields 
	 */
	public Campaign(int campaignId ,String campaigName)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName & discountPrecent fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName & discountPrecent & startDay fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent ,Date startDay )
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;
		this.startDay=startDay;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName & discountPrecent & startDay & endDay fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent ,Date startDay , Date endDay)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;
		this.startDay=startDay;
		this.endDay=endDay;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName & discountPrecent & startDay & endDay & startHour fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent ,Date startDay , Date endDay,Date startHour)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;
		this.startDay=startDay;
		this.endDay=endDay;
		this.startHour=startHour;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize campaignid & campaignName & discountPrecent & startDay & endDay & startHour & endHour fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent ,Date startDay , Date endDay,Date startHour ,Date endtHour)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;
		this.startDay=startDay;
		this.endDay=endDay;
		this.startHour=startHour;
		this.endtHour=endtHour;

	}
	
	/**
	 * Campaign Constructor
	 * this constructor initialize all fields 
	 */
	public Campaign(int campaignId ,String campaigName ,int discountPercent ,Date startDay , Date endDay,Date startHour ,Date endtHour,String status)
	{
		this.campaignId=campaignId;
		this.campaigName=campaigName;
		this.discountPercent=discountPercent;
		this.startDay=startDay;
		this.endDay=endDay;
		this.startHour=startHour;
		this.endtHour=endtHour;
		this.setStatus("Not approved");

	}
	
	/*****************************************Getters and Setters of fields *****************************/
	public void setCampaignId(int campaignId)
	{
		this.campaignId=campaignId;
	}

	public void setcampaigName(String campaigName)
	{
		this.campaigName=campaigName;
	}
	
	public void setdiscountPercent(int discountPercent)
	{
		this.discountPercent=discountPercent;
	}
	
	public void setstartDay( Date startDay)
	{
		this.startDay=startDay;
	}

	public void setendDay( Date endDay)
	{
		this.endDay=endDay;
	}
	
	public void setstartHour(Date start_hour)
	{
		this.startHour=start_hour;
	}
	
	public void setendHour(Date endtHour)
	{
		this.endtHour=endtHour;
	}
	
	/*
	public void setstartHour(String start_hour)
	{
		this.startHour=start_hour;
	}
	
	public void setendHour(String endtHour)
	{
		this.endtHour=endtHour;
	}
	*/
	public int getcampaignId()
	{
		return this.campaignId;
	}

	public String getcampaigName()
	{
		return this.campaigName;
	}
	
	public int getdiscountPercent()
	{
		return this.discountPercent;
	}
	
	public Date getstartDay()
	{
		return this.startDay;
	}
	
	public Date getendDay()
	{
		return this.endDay;
	}
	
	public Date getstartHour()
	{
		return this.startHour;
	}
	
	public Date getendtHour()
	{
		return this.endtHour;
	}
	/*
	public String getstartHour()
	{
		return this.startHour;
	}
	
	public String getendtHour()
	{
		return this.endtHour;
	}
	*/
	public String getStatus() {
		return this.campaignStatus;
	}

	public void setStatus(String status) {
		this.campaignStatus = status;
	}

}
