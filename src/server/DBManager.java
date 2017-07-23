package server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

import entity.*;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import entity.Dummy;
import entity.FuelStation;
import entity.LoginMod;
import entity.OrderHomeFuel;
import entity.Worker;
import entity.User;
import entity.CarFuelOrder;
import entity.Customer;
import gui.*;
import controller.*;

/**
 * This class handles all DB different function (adding,updating,pull data...)
 * this class simply uses sql queries to manipulate DB data and return required data
 * @author G16
 */
public class DBManager {
	
	/** 
	 * DBManager fields
	 * @author G16
	 */
	private static DBManager m_instance;
	private Connection conn;

/**
 * this method sets connection
 * @author G16
 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Single Tone for the DB-manager getInstance
	 * @author G16
	*/
	public static DBManager getInstance(){
		if(m_instance==null)
			m_instance=new DBManager();
		return m_instance;
		
	}
	
	/**
	 * Methods to send to DB
	 * @author G16
	*/
	
	//---------------------------SERVER START------------------------------//
	
	/**
	 * each time the server starts it needs to zero the status for all users
	 * so they will be shown as logged off
	 * @throws SQLException
	 * @author G16
	 */
	public void initOnServerStart() throws SQLException{
		  Statement stmt = conn.createStatement();  
		  String re ="UPDATE user u SET u.userlogstatus=0 WHERE u.userlogstatus=1;";
		  stmt.executeUpdate(re);
	}
	
	//---------------------------LOGIN------------------------------------------
	
	/**
	 * this method returns a user to be logged in
	 * it return null in case no such user exists
	 * or password is incorrect.
	 * will be used to login a user or return a bad
	 * username/password msg to screen.
	 * @author G16
	 * @param p_login
	 * @param userType
	 * @return User -> either customer or worker
	 * @throws SQLException
	 */
	public User CheckLoginUser(LoginMod p_login, String userType) throws SQLException{
		
		  Statement stmt = conn.createStatement();  
		  String re ="SELECT * FROM user WHERE username='" + p_login.getUserId() +
				  "' AND userpw='"+ p_login.getPassword() + 
				  "' AND usertype='"+ userType + "';";
		  ResultSet rs = stmt.executeQuery(re);
		  if (userType == "customer")
		  {
			while(rs.next()){
			return new Customer(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
			}
			return null;
		  }
		  else if (userType == "worker")
		  {
			while(rs.next()){
			return new Worker(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
			}
			return null;
		  }
		 return null;
	}

	/**
	 * changes log status for a user after loggin in
	 * @author G16
	 * @param ds
	 * @throws SQLException
	 */
	public void LoginUpdateLogStatus(Dummy ds) throws SQLException{
		  Statement stmt = conn.createStatement();  
		  String re ="UPDATE user SET userlogstatus=1 WHERE user.username='" + ds.getDummyString() + "';";
		  stmt.executeUpdate(re);
	}

	/**
	 * changes log status for a user after loggin out
	 * @author G16
	 * @param ds
	 * @throws SQLException
	 */
	public void LogoutUpdateLogStatus(LoginMod ds) throws SQLException{
		  Statement stmt = conn.createStatement();
		  String re ="UPDATE user SET userlogstatus=0 WHERE user.username='" + ds.getUserId() + "';";
		  stmt.executeUpdate(re);
	}

	  /**************************************** start of our methods **********************************************/
	
	/**
	 * this method adds a new car fuel order to DB.
	 * returns boolean to state if method worked.
	 * @author G16
	 * @param object
	 * @return BOOLEAN
	 * @throws SQLException
	 */
	public void AddNewCarFuelOrder(CarFuelOrder object) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = new Date();
		Statement stmt = conn.createStatement(); 
		String re8 = "SELECT myfuel.customer.customeId FROM myfuel.customer WHERE customeId ="+ object.getCustomer_id()+";";
		ResultSet rs8 = stmt.executeQuery(re8);
		  String re ="INSERT INTO myfuel.ordercarfuel (buyerId, licensePlateNumber, FuelStationId, quantity, fuelType) "
		  		+ "VALUES ("+object.getCustomer_id()+",'"+object.getLicensePlateNumber()+"',"+object.getFuel_station_id()+","+object.getQuantity()+",'"+object.getFuel_type()+"');";
		  stmt.executeUpdate(re);
		  String re1 = "SELECT * FROM myfuel.rates WHERE RatesId=1;";
		  ResultSet rs1 = stmt.executeQuery(re1);
		  rs1.last();
		  float rate = rs1.getFloat(object.getFuel_type());
		 String re2 =("SELECT myfuel.customer.customeId,myfuel.customer.membershiptype FROM myfuel.ordercarfuel,myfuel.customer WHERE ordercarfuel.buyerId = customer.customeId ;");
		 ResultSet rs = stmt.executeQuery(re2);
		 rs.last();
		 String tempFuelType = rs.getString(2);
		 float price = object.culcRates(tempFuelType,object.getQuantity(),rate);
		 String re3 ="INSERT INTO myfuel.invoice (stationId, price, customerId, quantity,fuelType,orderDate,paymentDate) "
			  		+ "VALUES ("+object.getFuel_station_id()+",'"+price+"','"+object.getCustomer_id()+"','"+object.getQuantity()+"','"+object.getFuel_type()+"','"+dateFormat.format(date)+"','"+dateFormat.format(date)+"');";
		stmt.executeUpdate(re3);
		String re4 =("SELECT  " + object.getFuel_type() + " FROM myfuel.fuelstation WHERE  fuelStationId = '" +object.getFuel_station_id()+ "';");
		ResultSet rs2 = stmt.executeQuery(re4);
		rs2.last();
		int quantity = rs2.getInt(1) - object.getQuantity();
		String re5 ="UPDATE myfuel.fuelstation SET " + object.getFuel_type() + " = "+quantity+" WHERE fuelStationId ='" + object.getFuel_station_id() + "';";
		stmt.executeUpdate(re5);
	}
	
	/**
	 * this method register a new user in the DB
	 * @author G16
	 * @param object
	 * @throws SQLException
	 */
	public void AddNewUser(User object)throws SQLException{
		Statement stmt = conn.createStatement(); 
		String re ="INSERT INTO myfuel.user(username,userpw,userlogstatus,usertype,userid,role)"
				+ "VALUES ('"+object.getUserName()+"','"+object.getUserPw()+"','"+object.getUserLogStatus()+"','"+object.getUserType()+"','"+object.getUserId()+"','"+object.getRole()+"');";
		stmt.executeUpdate(re);
	}
	
	/**
	 * this method adds a new home fuel order to DB
	 * @author G16
	 * @param object
	 * @throws SQLException
	 */
	public void AddNewHomeFuelOrder (OrderHomeFuel object) throws SQLException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   //get current date time with Date()
		   Date date = new Date();
		Statement stmt = conn.createStatement(); 
		String re ="INSERT INTO myfuel.orderhomefuel (invitedById, quantity, addressToSupply, supplyDate, supplyTime, orderType) "
		  		+ "VALUES ("+object.getInvitedById()+","+object.getQuantity()+
		  		",'"+object.getAddressToSupply()+"','"+object.getSupplyDate()+"','"+object.getSupplyTime()+"','"+object.getOrderType()+"');";
		stmt.executeUpdate(re);
		  String re1 = "SELECT * FROM myfuel.rates WHERE RatesId=1;";
		  ResultSet rs1 = stmt.executeQuery(re1);
		  rs1.last();
		  float rate = rs1.getFloat("HomeFuel");
		  float price = object.getQuantity() * rate;
		String re3 ="INSERT INTO myfuel.invoice (stationId,price, customerId, quantity,fuelType,orderDate,paymentDate) "
		  		+ "VALUES ("+999+",'"+price+"','"+object.getInvitedById()+"','"+object.getQuantity()+"','"+"HomeFuel"+"','"+object.getSupplyDate()+"','"+object.getSupplyDate()+"');";
	stmt.executeUpdate(re3);
	}
	
	/**
	 * this method register a new customer in the DB
	 * @author G16
	 * @param object
	 * @throws SQLException
	 */
	public void AddNewCustomer (Customer object) throws SQLException{
		Statement stmt = conn.createStatement(); 
		if(object.getPayment().equals("Cash")||object.getPayment().equals("")){
		String re ="INSERT INTO myfuel.customer(customeId,customerFirstName,customerLastName,customerEmail,customerAddress,membershiptype,payment,customerPhone)"
				+ "VALUES ("+object.getCustomerId()+",'"+object.getCustomerFirstName()+"','"+object.getCustomerLastName()+"','"+object.getCustomerEmail()+"','"+object.getCustomerAddress()
				+"','"+object.getMemberShipType()+"','"+object.getPayment()+"','"+object.getCustomerPhone()+"');";
		stmt.executeUpdate(re);
		}
		else{
			String re ="INSERT INTO myfuel.customer(customeId,customerFirstName,customerLastName,customerEmail,customerAddress,membershiptype,payment,customerPhone,creditCardNumber)"
					+ "VALUES ("+object.getCustomerId()+",'"+object.getCustomerFirstName()+"','"+object.getCustomerLastName()+"','"+object.getCustomerEmail()+"','"+object.getCustomerAddress()
					+"','"+object.getMemberShipType()+"','"+object.getPayment()+"','"+object.getCustomerPhone()+"','"+object.getCreditCardNumber()+"');";
			stmt.executeUpdate(re);
		}
	}
	
	/**
	 * this method adds a new car to DB
	 * @author G16
	 * @param object
	 * @throws SQLException
	 */
	public void AddNewCar(Car object) throws SQLException{
		Statement stmt = conn.createStatement(); 
		String re ="INSERT INTO myfuel.car (ownerid, licenseplatenumber, carytype, fueltype,carNFC) "
		  		+ "VALUES ("+object.getownerid()+",'"+object.getlicenseplatenumber()+
		  		"','"+object.getCarType()+"','"+object.getFuelType()+"','"+object.getCarNFC()+"');";
		stmt.executeUpdate(re);
	}
	
	/**
	 * this method return all of a single user home fuel orders
	 * @author G16
	 * @param Object
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] getUserOrders (User Object) throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re ="SELECT * FROM myfuel.orderhomefuel WHERE invitedById="+Object.getUserId()+";";
		ResultSet rs = stmt.executeQuery(re);
		rs.last();
		if (rs.getRow()>0){
		Object TempRows[][] = new Object[rs.getRow()][7];
		rs.beforeFirst();
		int i=0;
		int j=0;
		while(rs.next())
		{
			TempRows[i][j] = rs.getInt(1);
			j++;
			TempRows[i][j] = rs.getInt(2);
			j++;
			TempRows[i][j] = rs.getInt(3);
			j++;
			TempRows[i][j] = rs.getString(4);
			j++;
			TempRows[i][j] = rs.getString(5);
			j++;
			TempRows[i][j] = rs.getString(6);
			j++;
			TempRows[i][j] = rs.getString(7);
			j=0;
			i++;
		}
		return TempRows;
		}
		else return null;
	}
	
	/**
	 * this method returns all rates update request with status
	 * @author G16
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] getRequeststatus () throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re ="SELECT * FROM myfuel.rates ;";
		ResultSet rs = stmt.executeQuery(re);
		Object TempRows[][] = new Object[4][4];
		int i=0;
		int j=1;
		int z=1;
		TempRows[0][0] = "Motors Fuel";
		TempRows[1][0] = "Diesel";
		TempRows[2][0] = "Benzine 95";
		TempRows[3][0] = "Home Fuel";
		rs.next();
		while(i<4)
		{
			if ((rs.getFloat(z))==(rs.getFloat(z+1))) TempRows[i][3] = "Approved";
			else TempRows[i][3] = "Not Approved";
			
			TempRows[i][j] = rs.getFloat(z);
			j++;
			z++;
			TempRows[i][j] = rs.getFloat(z);
			z++;
			j=1;
			i++;
		}
		return TempRows;
		}
	
	/**
	 * this method adds a new campaign to the DB
	 * @author G16
	 * @param Object
	 * @throws SQLException
	 */
	public void AddNewCampaign(Campaign Object) throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		SimpleDateFormat format  = new SimpleDateFormat ("hh:mm:ss");
		String re = "INSERT INTO myfuel.campaign (campaigName,discountPercent,startDay,endDay,startHour,endtHour)"+ 
		"VALUES ('"+Object.getcampaigName()+"',"+Object.getdiscountPercent()+",'"+String.format("%1$tY/%1$tm/%1$td",Object.getstartDay())+ "','"+String.format("%1$tY/%1$tm/%1$td",Object.getendDay())+"','"+format.format(Object.getstartHour())+"','"+format.format(Object.getendtHour())+"');";
		stmt.executeUpdate(re);
	}
	
	/**
	 * this method adds fuel to specific station inventory
	 * @author G16
	 * @param temp
	 * @throws SQLException
	 */
	public void addFuel(int[] temp) throws SQLException
	{
		String fuelType = "Benzine95";
		Statement stmt = conn.createStatement();
		switch(temp[2]){
			case 0:
				fuelType = "Benzine95";
				break;
			case 1:
				fuelType = "Diesel";
				break;
			case 2:
				fuelType = "Motors";
				break;
		}
		String re = "UPDATE fuelstation SET "+ fuelType + "=" + temp[1] + " WHERE stationMenagerID=" + temp[0] + ";";
		stmt.executeUpdate(re);
	}
	
	/**
	 * this method adds a new acquire.
	 * when a station manager orders fuel to fill the inventory
	 * this method saves the order details in the DB. 
	 * @author G16
	 * @param acq
	 * @throws SQLException
	 */
	public void addAcquire(Object acq) throws SQLException
	{
		Acquires tempAcq = (Acquires)acq;
		Statement stmt = conn.createStatement();
		String re = "INSERT INTO myfuel.acquires (day,month,year,amount,stationID,fuelType) VALUES (" 
				+ tempAcq.getDay() + "," + tempAcq.getMonth() + "," + tempAcq.getYear() + ","
				+ tempAcq.getAmount() + "," + tempAcq.getStationID() + ",'" + tempAcq.getFuelType() + "');";
		stmt.executeUpdate(re);
	}
	
	/**
	 * this method returns all acquires of a single station
	 * from the DB.
	 * @author G16
	 * @param stationID
	 * @return Object -> Acquires[]
	 * @throws SQLException
	 */
	public Object produceAcquires(Object stationID) throws SQLException
	{
		int i = 0;
		Acquires[] tempAcq;
		Statement stmt = conn.createStatement();
		String re = "SELECT * FROM myfuel.acquires WHERE stationID="+(int)stationID+";";
		ResultSet rs = stmt.executeQuery(re);
		int rowcount = 0;
		rs.last();
		rowcount = rs.getRow();
		rs.beforeFirst();
		if (rowcount > 0) {
			tempAcq = new Acquires[rowcount];
			while(rs.next())
			{
				tempAcq[i++] = new Acquires(rs.getInt(5), rs.getInt(6),rs.getInt(7),rs.getInt(2),rs.getString(4),rs.getInt(1));
			}
			return (Object)tempAcq;
		}
		return null;
	}
	
	/**
	 * this method returns all incomes of a single station
	 * from the DB.
	 * @author G16
	 * @param stationID
	 * @return Object -> Invoice[]
	 * @throws SQLException
	 */
	public Object produceIncomes(Object stationID) throws SQLException
	{

		int i = 0;
		Invoice[] tempInv;
		Statement stmt = conn.createStatement();
		String re = "SELECT * FROM myfuel.invoice WHERE stationID="+(int)stationID+";";
		ResultSet rs = stmt.executeQuery(re);
		int rowcount = 0;
		rs.last();
		rowcount = rs.getRow();
		rs.beforeFirst();
		if (rowcount > 0) {
			tempInv = new Invoice[rowcount];
			while(rs.next())
			{
				tempInv[i++] = new Invoice(rs.getDate(4), rs.getInt(5));
			}
			return (Object)tempInv;
		}
		return null;
	}
	
	/**
	 * this method return all of the details of a single
	 * fuel station (using the station manager ID).
	 * @author G16
	 * @param userId
	 * @return FuelStation
	 * @throws SQLException
	 */
	public FuelStation getFuelStationDetails(int userId) throws SQLException
	{
		Statement stmt = conn.createStatement();
		String re = "SELECT * FROM myfuel.fuelstation WHERE stationMenagerID="+userId+";";
		ResultSet rs = stmt.executeQuery(re);
		
		if (rs.next())
		{
			return new FuelStation(rs.getInt(1),rs.getInt(2),
					rs.getInt(3),rs.getString(4),rs.getInt(5),
					rs.getInt(6),rs.getInt(7),rs.getInt(8));
		}
		
		return null;

	}
	
	/**
	 * this method sets minimum fuel levels for a specific
	 * fuel station (that belongs to a single station manager)
	 * using station managerID (temp[0]) and fuel level to be set (temp[1]).
	 * will be used to notify the station manager of low fuel.
	 * @author G16
	 * @param temp
	 * @throws SQLException
	 */
	public void setFuelLevel(int[] temp) throws SQLException
	{
		Statement stmt = conn.createStatement();
		String re = "UPDATE fuelstation SET minLevelAmount="+ temp[1] + " WHERE stationMenagerID=" + temp[0] + ";";
		stmt.executeUpdate(re);
		}

	/**
	 * this method returns all sale reaction reports
	 * @author G16
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] getSaleReactionReport () throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re ="SELECT * FROM myfuel.campaign;";
		ResultSet rs = stmt.executeQuery(re);
		rs.last();
		if (rs.getRow()>0){
		Object TempRows[][] = new Object[rs.getRow()][7];
		rs.beforeFirst();
		int i=0;
		int j=0;
		while(rs.next())
		{
			TempRows[i][j] = rs.getInt(1);
			j++;
			TempRows[i][j] = rs.getString(2);
			j++;
			TempRows[i][j] = rs.getInt(3);
			j++;
			TempRows[i][j] = rs.getDate(4);
			j++;
			TempRows[i][j] = rs.getDate(5);
			j++;
			TempRows[i][j] = rs.getTime(6);
			j++;
			TempRows[i][j] = rs.getTime(7);
			j=0;
			i++;
		}
		return TempRows;
		}else return null;
	}
	
	/**
	 * this method returns all of the campaigns that are not approved in table form 
	 * @author G16
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] checkCampaign() throws SQLException {
		  Statement stmt = conn.createStatement();  
		  String re =("SELECT * FROM myfuel.campaign WHERE campaignStatus = 'Not approved';");
		  ResultSet rs = stmt.executeQuery(re);
			rs.last();
			if (rs.getRow()>0){
		  Object TempRows[][] = new Object[rs.getRow()][8];
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j] = rs.getInt(1);
				j++;
				TempRows[i][j] = rs.getString(2);
				j++;
				TempRows[i][j] = rs.getInt(3);
				j++;
				TempRows[i][j] = rs.getDate(4);
				j++;
				TempRows[i][j] = rs.getDate(5);
				j++;
				TempRows[i][j] = rs.getTime(6);
				j++;
				TempRows[i][j] = rs.getTime(7);
				j++;
				TempRows[i][j] = rs.getString(8);
				j=0;
				i++;
			}
			return TempRows;
			}
			else return null;
	}


	//query that updating  campaign status approvRate
	/**
	 * this method updates a single campaign status
	 * @author G16
	 * @param ID
	 * @throws SQLException
	 */
	public boolean aproveCampaign(int ID ) throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re = "SELECT CampaignId FROM myfuel.campaign WHERE CampaignId ="+ID+";" ;
		ResultSet rs = stmt.executeQuery(re);
		if(rs.last())
		{
			
			String re1 = "UPDATE myfuel.campaign  SET campaignStatus='Approved' WHERE CampaignId  ="+ID+ ";" ;
			stmt.executeUpdate(re1);
			return true;
		}
		return false;
	}



	/**
	 * this method produce a quarter report and saves it in the DB
	 * @author G16
	 * @param report
	 * @throws SQLException
	 */
	public boolean ProduceQuarterReport(String report ) throws SQLException
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QuarterReport temp = new QuarterReport();
		Statement stmt = conn.createStatement(); 
		String re1 = "SELECT ReportQuarterId FROM myfuel.quarterreport WHERE year = "+temp.getYear()+" AND Quarter ='"+report+"';" ;
		ResultSet rs1 = stmt.executeQuery(re1);
		if(rs1.last()==true)
			return false;
		if(report.equals("FirstQuarter"))
		{
			temp.setQuarter("FirstQuarter");
			String sYear=Integer.toString(temp.getYear())+"-"+"01"+"-"+"01";
			String eYear=Integer.toString(temp.getYear())+"-"+"03"+"-"+"31";
		    String re = "SELECT SUM(price*quantity) FROM  myfuel.invoice WHERE paymentDate BETWEEN '"+sYear+"' AND '"+eYear+"';";
		    ResultSet rs = stmt.executeQuery(re);
			if (rs.next())
			{
				temp.setTotalIncome(rs.getInt(1));
			}
		}
		else if(report.equals("SecondQuarter"))
		{
			temp.setQuarter("SecondQuarter");
			String sYear=Integer.toString(temp.getYear())+"-"+"03"+"-"+"01";
			String eYear=Integer.toString(temp.getYear())+"-"+"05"+"-"+"30";
		    String re = "SELECT SUM(price*quantity) FROM  myfuel.invoice WHERE paymentDate BETWEEN '"+sYear+"' AND '"+eYear+"';";
		    ResultSet rs = stmt.executeQuery(re);
			if (rs.next())
			{
				temp.setTotalIncome(rs.getInt(1));
			}
		}
		else if(report.equals("ThirdQuarter"))
		{
			temp.setQuarter("ThirdQuarter");
			String sYear=Integer.toString(temp.getYear())+"-"+"06"+"-"+"01";
			String eYear=Integer.toString(temp.getYear())+"-"+"08"+"-"+"31";
		    String re = "SELECT SUM(price*quantity) FROM  myfuel.invoice WHERE paymentDate BETWEEN '"+sYear+"' AND '"+eYear+"';";
		    ResultSet rs = stmt.executeQuery(re);
			if (rs.next())
			{
				temp.setTotalIncome(rs.getInt(1));
			}
		}
		else if(report.equals("FourthQuarter"))
		{
			temp.setQuarter("FourthQuarter");
			String sYear=Integer.toString(temp.getYear())+"-"+"09"+"-"+"01";//2016-01-01
			String eYear=Integer.toString(temp.getYear())+"-"+"12"+"-"+"31";;//2016-01-01
		    String re = "SELECT SUM(price*quantity) FROM  myfuel.invoice WHERE paymentDate BETWEEN '"+sYear+"' AND '"+eYear+"';";
		    ResultSet rs = stmt.executeQuery(re);
		    rs.last();
			rs.beforeFirst();
			if (rs.next())
			{
				temp.setTotalIncome(rs.getInt(1));
			}	
		}
		String re5 = "SELECT sum(Benzine95),sum(Diesel),sum(Motors) FROM myfuel.fuelstation;";
		ResultSet rs5 = stmt.executeQuery(re5);
		rs5.last();
		String re2 = "INSERT INTO myfuel.quarterreport "
				+ "( `totalIncome`, `Benzine95`, `Diesel`, `Motors`,`Quarter`,`year`) "
				+ "VALUES ("+temp.getTotalIncome()+","+rs5.getInt(1)+","
				+ rs5.getInt(2)+","+rs5.getInt(3)+",'"+report+"',"+temp.getYear()
				+")"+";";
		stmt.executeUpdate(re2);
		return true;
	}

	public Object[][] checkQuarter(String obj[]) throws SQLException
	{
		if(obj[0]==null || obj[1]==null)
			return null;
		  Object TempRows[][] = new Object[1][7];
		  String type = (String)obj[0];
		  String year  = (String)obj[1];
		  int yer = Integer.parseInt(year);
		  Statement stmt = conn.createStatement();  
		  String re ="SELECT * FROM myfuel.quarterreport WHERE year="+yer+" AND Quarter = '"+type+"';";
		  ResultSet rs = stmt.executeQuery(re);
		  if(rs.next()){
				TempRows[0][0]= rs.getInt(1);
				TempRows[0][1]= rs.getDouble(2);
				TempRows[0][2]= rs.getString(3);
				TempRows[0][3]= rs.getString(4);
				TempRows[0][4]= rs.getString(5);
				TempRows[0][5]= rs.getString(6);
				TempRows[0][6]= rs.getInt(7);

				return TempRows;
		  }
		  return null;

	}
	
	public Object[][] checkRate() throws SQLException
	{
		 Statement stmt = conn.createStatement();  
		  String re =("SELECT * FROM myfuel.rates;");
		  ResultSet rs = stmt.executeQuery(re);
			rs.last();
		//  rs.last(); //what this func is doing 
			if (rs.getRow()>0){
			Object TempRows[][] = new Object[rs.getRow()][9];
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j] = rs.getInt(1);
				j++;
				TempRows[i][j] = rs.getFloat(2);
				j++;
				TempRows[i][j] = rs.getFloat(3);
				j++;
				TempRows[i][j] = rs.getFloat(4);
				j++;
				TempRows[i][j] = rs.getFloat(5);
				j++;
				TempRows[i][j] = rs.getFloat(6);
				j++;
				TempRows[i][j] = rs.getFloat(7);
				j++;
				TempRows[i][j] = rs.getFloat(8);
				j++;
				TempRows[i][j] = rs.getFloat(9);
				j=0;
				i++;
			}
			return TempRows;
			}

			else return null;
		
	}

	public boolean approvRate(String  type ) throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re = "SELECT * FROM myfuel.rates  ;" ;
		ResultSet rs = stmt.executeQuery(re);
		if(rs.last())
		{
			float MotorsFuel = rs.getFloat("Motors");
			float benzine  = rs.getFloat("Benzine95");
			float Diesel = rs.getFloat("Diesel");
			float Homefuel = rs.getFloat("Homefuel");
			if(type.equals("Motors"))
			{
				String re1 = "UPDATE myfuel.rates  SET maxMotorsFuel ="+MotorsFuel+" WHERE   RatesId = 1 ;" ;
				stmt.executeUpdate(re1);
				return true;
			}
			if(type.equals("Benzine95"))
			{
				String re1 = "UPDATE myfuel.rates  SET maxbenzine95 ="+benzine+" WHERE   RatesId = 1 ;" ;
				stmt.executeUpdate(re1);
				return true;
			}
			if(type.equals("Diesel"))
			{
				String re1 = "UPDATE myfuel.rates  SET maxDiesel ="+Diesel+" WHERE   RatesId = 1 ;" ;
				stmt.executeUpdate(re1);
				return true;
			}
			if(type.equals("Homefuel"))
			{
				String re1 = "UPDATE myfuel.rates  SET maxHomefuel ="+Homefuel+"   WHERE RatesId = 1 ;" ;
				stmt.executeUpdate(re1);
				return true;
			}

			
			
		}
		return false;
	}
	
	/**
	 * this method changes a rate update request status using a Rates instance
	 * @author G16
	 * @param Object
	 * @throws SQLException
	 */
	public void UpdateRates(Rates Object) throws SQLException
	{
		Statement stmt = conn.createStatement(); 
		String re;
		if (Object.getBenzine95_update()>0)
		{
		re ="UPDATE myfuel.rates SET Benzine95="+Object.getBenzine95_update()+" WHERE RatesId=1";
		stmt.executeUpdate(re);
		}
		
		if (Object.getDiesel_update()>0)
		{
		re ="UPDATE myfuel.rates SET Diesel="+Object.getDiesel_update()+" WHERE RatesId=1";
		stmt.executeUpdate(re);
		}
		
		if (Object.getMotorcycle_fuel_update()>0)
		{
		re ="UPDATE myfuel.rates SET Motors="+Object.getMotorcycle_fuel_update()+" WHERE RatesId=1";
		stmt.executeUpdate(re);
		}
			
		
		if (Object.getHome_fuel_update()>0)
		{
		re ="UPDATE myfuel.rates SET HomeFuel="+Object.getHome_fuel_update()+" WHERE RatesId=1";
		stmt.executeUpdate(re);
		}
	}
	
	/**
	 * this method updates every week the customer rank for each customer
	 * @author G16
	 * @throws SQLException
	 */
	public void setWeekRate()throws SQLException{
		String[] re1 = new String [20];
		Statement stmt = conn.createStatement(); 
		String re =("SELECT myfuel.customer.customeId,myfuel.customer.rank,customer.membershiptype,SUM(quantity) FROM myfuel.invoice,myfuel.customer WHERE invoice.customerId = customer.customeId GROUP BY customer.customeId ;");
		 ResultSet rs = stmt.executeQuery(re);
		 rs.last(); 
		 Object TempRows[][] = new Object[rs.getRow()][4];
			if (rs.getRow()>0){
		  
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j] = rs.getInt(1);
				j++;
				TempRows[i][j] = rs.getInt(2);
				j++;
				TempRows[i][j] = rs.getString(3);;
				j++;
				TempRows[i][j] = rs.getInt(4);;
				j = 0;
				i++;
			}
			}
			rs.last(); 
			rs.beforeFirst();
			int k = 0;
			while(rs.next()){
			 int rank = (int) TempRows[k][1];
			 int quantity = (int) TempRows[k][3];
			 int customerId = (int) TempRows[k][0];
			 String type = (String) TempRows[k][2];
		 if((quantity > 500) && (rank < 9) ){
			 switch(type){
				case "Refueling occur":
					rank += 1;
					break;
				case "Subscription one car":
					rank += 1;
					break;
				case "Subscription multiple car":
					rank += 2;
					break;
				case "Full subscription":
					rank += 2;
					break;
					}
		 }
		 if((quantity == 500) && (rank < 9)){
			 switch(type){
				case "Refueling occur":
					break;
				case "Subscription one car":
					break;
				case "Subscription multiple car":
					rank += 1;
					break;
				case "Full subscription":
					rank += 1;
					break;
					}
		 }
		 if((quantity < 500) && rank > 2){
			 switch(type){
				case "Refueling occur":
					rank -= 2;
					break;
				case "Subscription one car":
					rank -= 2;
					break;
				case "Subscription multiple car":
					rank -= 1;
					break;
				case "Full subscription":
					rank -= 1;
					break;
					}
		 }
		 	re1[k++] ="UPDATE customer SET rank = "+rank+" WHERE customer.customeId="+customerId+";";
			}
			rs.last(); 
			rs.beforeFirst();
			for(int l = 0 ; l < k; l ++)
			stmt.executeUpdate(re1[l]);
	}
	
	/**
	 * this method provides a report which helps with modifying customers ranks
	 * @author G16
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] checkWeeklyReportCustomerType() throws SQLException {
		  Statement stmt = conn.createStatement();  
		  String re =("SELECT DISTINCT myfuel.customer.customeId,myfuel.customer.membershiptype,customer.rank FROM myfuel.ordercarfuel,myfuel.customer WHERE ordercarfuel.buyerId = customer.customeId ;");
		  ResultSet rs = stmt.executeQuery(re);
			rs.last(); 
			if (rs.getRow()>0){
		  Object TempRows[][] = new Object[rs.getRow()][3];
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j] = rs.getInt(1);
				j++;
				TempRows[i][j] = rs.getString(2);
				j++;
				TempRows[i][j] = rs.getInt(3);;
				j = 0;
				i++;
			}
			return TempRows;
			}

			else return null;
			
	}

	/**
	 * this method produce a sale reaction report
	 * @author G16
	 * @param object
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] SaleReactionReportProduce(int object) throws SQLException
	{
		  Statement stmt = conn.createStatement(); 
		  Object TempRows[][] = new Object[1][3];
		  String re1 ="SELECT * FROM campaign WHERE CampaignId="+object+";";
		  ResultSet rs1= stmt.executeQuery(re1); 
		  rs1.next();
		  String re  ="SELECT COUNT(DISTINCT customerId) FROM invoice WHERE orderDate BETWEEN '"+rs1.getDate(4) +"' AND '"+rs1.getDate(5)+"';";
		  ResultSet rs = stmt.executeQuery(re);
		  rs.next();
		  TempRows[0][1] = rs.getInt(1);
		  re1 ="SELECT * FROM campaign WHERE CampaignId="+object+";";
		  rs1= stmt.executeQuery(re1); 
		  rs1.next();
				TempRows[0][0] = rs1.getString(2);
				TempRows[0][2] = "***";
			return TempRows;
	}
	
	/**
	 * this method calculates quantity for a fuel type
	 * @author G16
	 * @return Object[][]
	 * @throws SQLException
	 */
	public Object[][] checkFuelType() throws SQLException {
		Statement stmt = conn.createStatement();  
		  String re =("SELECT fuelType,SUM(quantity) FROM myfuel.invoice GROUP BY fuelType ;");
		  ResultSet rs = stmt.executeQuery(re);
			rs.last(); 

			if (rs.getRow()>0){
		  Object TempRows[][] = new Object[rs.getRow()][2];
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j++] = rs.getString(1);
				TempRows[i][j] = rs.getInt(2);
				j = 0;
				i++;
			}
			return TempRows;
			}

			else return null;
	}
	
	public Object[][] GetCustomerReport() throws SQLException
	{
		  Statement stmt = conn.createStatement(); 
		  String re ="SELECT myfuel.customer.customeId,customer.rank,myfuel.invoice.stationId FROM myfuel.invoice,myfuel.customer WHERE invoice.customerId = customer.customeId ORDER BY rank DESC;";
		  ResultSet rs = stmt.executeQuery(re);
			rs.last();
			if (rs.getRow()>0){
		  Object TempRows[][] = new Object[rs.getRow()][3];
			rs.beforeFirst();
			int i=0;
			int j=0;
			while(rs.next())
			{
				TempRows[i][j] = rs.getInt(1);
				j++;
				TempRows[i][j] = rs.getInt(2);
				j++;
				TempRows[i][j] = rs.getInt(3);
				j=0;
				i++;
			}
			return TempRows;
			}
			else return null;	
	}

}

