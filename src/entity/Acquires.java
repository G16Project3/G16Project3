package entity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is Acquires entity which saves a Acquire from a specific fuel station
 * @author G16
 *
 */
public class Acquires extends AbstractModel {
	
	/**acquire Unique ID*/
	private int acquireID;
	
	/**acquire day*/
	private int day;
	
	/**acquire month*/
	private int month;

	/**acquire year*/
	private int year;
	
	/**acquired amount*/
	private int amount;
	
	/** station ID*/
	private int stationID;
	
	/**acquired fuel type*/
	private String fuelType;
	
	/**
	 * Acquires Constructor
	 * this constructor initialize fields 
	 */
	public Acquires(int day, int month, int year, int amount, int sID, String ft){
		this.day = day;
		this.month = month;
		this.year = year;
		this.amount = amount;
		this.stationID = sID;
		this.fuelType = ft;
	}
	
	/**
	 * Acquires Constructor
	 * this constructor initialize fields 
	 */
	public Acquires(int day, int month, int year, int amount, String ft, int acquireID){
		this.day = day;
		this.month = month;
		this.year = year;
		this.amount = amount;
		this.acquireID = acquireID;
		this.fuelType = ft;
	}
	
	/*****************************************Getters and Setters of fields *****************************/
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getAcquireID() {
		return acquireID;
	}
	
	public void setAcquireID(int acquireID) {
		this.acquireID = acquireID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	
} // acquires


