package server;

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.*;
import ocsf.ocsfServer.*;
import gui.*;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.*;

import controller.*;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class ServerConnectionManager extends AbstractServer 
{
	
 private Connection conn;
 private ServerController controller;

 ResultSet rs;
 
 private serverLogGui m_serverLogView;
 
  //Class variables *************************************************

  /**
   * The default port to listen on.
   * @author G16
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * @param serverLogView 
   * @author G16
   */
  public ServerConnectionManager(int port, serverLogGui serverLogView) 
  {
    super(port);
    m_serverLogView = serverLogView;
  }

  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   * mainly this method handles data from client to be sent over to DBManager
   * and data returned from DB to be sent over to client.
   * @author G16
   * @param msg is the message we received from the client.
   * @param client The connection from which the message originated.
   */
  @SuppressWarnings("unchecked")
  public void handleMessageFromClient
  
/**
 * This method handles any messages received from the client.
 * @author G16
 */
    (Object msg, ConnectionToClient client)
  {

	  Envelope ne = (Envelope) msg;
	
	  try {
//---------------------------LOGIN------------------------------------------
		  if(ne.getTask().equals("customerLogin")) //search Login as customer
		  {
			client.setName(((LoginMod) ne.getObject()).getUserId());
			ne.setMessage(DBManager.getInstance().CheckLoginUser((LoginMod) ne.getObject(), "customer"));
			User temp = (User) ne.getMessage();
			if(ne.getMessage()!= null && temp.getUserLogStatus() == 0)
				m_serverLogView.WriteToLog("Customer "
						+((LoginMod) ne.getObject()).getUserId()+ " is now connected");
			else if (ne.getMessage()!= null && temp.getUserLogStatus() != 0)
				m_serverLogView.WriteToLog("Customer "
						+((LoginMod) ne.getObject()).getUserId()+ " already connected!");
			
			client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("workerLogin"))	//search Login as worker
		  {
			  client.setName(((LoginMod) ne.getObject()).getUserId());
			  ne.setMessage(DBManager.getInstance().CheckLoginUser((LoginMod) ne.getObject(), "worker"));
			  User temp = (User)ne.getMessage();
				if(ne.getMessage()!=null && temp.getUserLogStatus() == 0)
					m_serverLogView.WriteToLog("Worker "
							+((LoginMod) ne.getObject()).getUserId()+ " is now connected");
				else if (ne.getMessage()!= null && temp.getUserLogStatus() != 0)
					m_serverLogView.WriteToLog("Worker "
							+((LoginMod) ne.getObject()).getUserId()+ " already connected!");
				
				client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("LoginUpdateLogStatus"))	//update user log status 
		  {
			  DBManager.getInstance().LoginUpdateLogStatus((Dummy) ne.getObject());
		  }
		  
		  else if(ne.getTask().equals("logoutUser"))
		  {
			  DBManager.getInstance().LogoutUpdateLogStatus((LoginMod) ne.getObject());
			  m_serverLogView.WriteToLog("User "+((LoginMod) ne.getObject()).getUserId()+ " is now disconnected");
		  }

		//---------------------------END OF LOGIN----------------------------//
		  
		//---------------------------Start functions-------------------------//
		  
	    // start of our functions here we use the function that is in DBManager class here we just identify the type of massage that we receive from client//
		  else if(ne.getTask().equals("AddNewCarFuelOrder"))
		  {	
			  DBManager.getInstance().AddNewCarFuelOrder((CarFuelOrder) ne.getObject());  
			  ne.setMessage("AddNewCarFuelOrder");
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("AddNewHomeFuelOrder"))
		  {	
			
			  DBManager.getInstance().AddNewHomeFuelOrder((OrderHomeFuel) ne.getObject());  
			  ne.setMessage("AddNewHomeFuelOrder");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("UserOrders"))
		  {
			  
			  ne.setObject(DBManager.getInstance().getUserOrders((User) ne.getObject()));
			  ne.setMessage("UserOrders");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("AddNewSale"))
		  {
			  DBManager.getInstance().AddNewCampaign((Campaign) ne.getObject());  
			  ne.setMessage("AddNewSale");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("RatesUpdate"))
		  {
			  DBManager.getInstance().UpdateRates((Rates) ne.getObject()); 
			  ne.setMessage("RatesUpdate");
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("AddNewCustomer")){
			  DBManager.getInstance().AddNewCustomer((Customer) ne.getObject());
			  ne.setMessage("AddNewCustomer");
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("AddNewUser")){
			  DBManager.getInstance().AddNewUser((User) ne.getObject());
			  ne.setMessage("AddNewCustomer");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("AddNewCar")){
			  DBManager.getInstance().AddNewCar((Car) ne.getObject());
			  ne.setMessage("AddNewCar");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("getFuelStationDetails")){
			  ne.setObject((Object)(DBManager.getInstance().getFuelStationDetails((int) ne.getObject())));
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("AddFuel")){
			  DBManager.getInstance().addFuel((int[]) ne.getObject());
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("AddAcquire")) {
			  DBManager.getInstance().addAcquire(ne.getObject());
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("ProduceIncomes")) {
			  ne.setObject(DBManager.getInstance().produceIncomes(ne.getObject()));
			  client.sendToClient(ne);
		  }
		  
		  else if(ne.getTask().equals("ProduceAcquires")) {
			  ne.setObject(DBManager.getInstance().produceAcquires(ne.getObject()));
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("SetFuelLevel")){
			  DBManager.getInstance().setFuelLevel((int[]) ne.getObject());
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("checkCampaign")){

			Object[][] campRes=  DBManager.getInstance().checkCampaign();
			  ne.setObject(campRes);
			  ne.setMessage("checkCampaign");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("checkRate")){
			  Object[][] campRes = DBManager.getInstance().checkRate();
			  ne.setObject(campRes);
			  ne.setMessage("checkRate");
			  client.sendToClient(ne);
		  } 
		  
		  else if (ne.getTask().equals("aproveCampaign")){
			 boolean bool = DBManager.getInstance().aproveCampaign((int) ne.getObject());
			 if(bool)
				 ne.setMessage("aproveCampaign");
			 else
				 ne.setMessage("NotAproveCampaign");

			  client.sendToClient(ne);

		  }
		  
		  else if (ne.getTask().equals("ProduceQuarterReport")){
			boolean bool =  DBManager.getInstance().ProduceQuarterReport((String)ne.getObject());
			if(bool)

			  ne.setMessage("ProduceQuarterReport");
			else
				ne.setMessage("ProduceQuarterExist");
			  client.sendToClient(ne);

		  }
		  
		  else if (ne.getTask().equals("checkWeeklyReportCustomerType")){
			  Object[][] campRes = DBManager.getInstance().checkWeeklyReportCustomerType();
			  ne.setObject(campRes);
			  ne.setMessage("FuelTypeReturn");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("CheckRatesRequest"))
		  {
			  ne.setObject(DBManager.getInstance().getRequeststatus());
			  ne.setMessage("CheckRatesRequest");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("SaleReactionReport"))
		  {
			  ne.setObject(DBManager.getInstance().getSaleReactionReport());
			  ne.setMessage("SaleReactionReport");
			  client.sendToClient(ne); 
		  }
		  
		  else if (ne.getTask().equals("SaleReactionReportProduce"))
		  {
			  ne.setObject(DBManager.getInstance().SaleReactionReportProduce((int)ne.getObject()));
			  ne.setMessage("SaleReactionReportProduce");
			  client.sendToClient(ne); 
		  }
		  
		  else if (ne.getTask().equals("checkFuelType")){
			  Object[][] campRes = DBManager.getInstance().checkFuelType();
			  ne.setObject((Object)campRes);
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("setWeekRate")){
			   DBManager.getInstance().setWeekRate();
			   ne.setMessage("setWeekRate");
			  client.sendToClient(ne);
		  }
		  else if (ne.getTask().equals("checkQuarter")){
			   ne.setObject(DBManager.getInstance().checkQuarter((String[])ne.getObject()));			   
			   ne.setMessage("setWeekRate");
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("CustomerReport")){
			   ne.setObject(DBManager.getInstance().GetCustomerReport());			   
			  client.sendToClient(ne);
		  }
		  
		  else if (ne.getTask().equals("approvRate")){
			  boolean bool =DBManager.getInstance().approvRate((String)ne.getObject());
			  if(bool)
				  ne.setMessage("approvRate");
			  else
				  ne.setMessage("notApprovRate");
			  client.sendToClient(ne);
		  }
		  

		  else System.out.println(ne.getTask());

		//---------------------------End functions-------------------------//  
		  
	  }//end try
	  
          catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  
  public Connection getConn() {
	return conn;
}
  
 
 public void setConn(Connection conn) {
	this.conn = conn;
}
 
 public void setController(ServerController controller) {
		this.controller = controller;
	}

  
 /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
 
  protected void serverStarted()
  {
	 System.out.println("Server listening for connections on port " + getPort());
	 m_serverLogView.WriteToLog("Server is up and running");
	 try{
		 DBManager.getInstance().initOnServerStart();
	 }
	 catch (SQLException e) {
		 
	}
	 
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  
  protected void serverStopped()
  {
    System.out.println("Server has stopped listening for connections.");
  }
  
 @Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
	  try {
		DBManager.getInstance().LogoutUpdateLogStatus(new LoginMod(client.getName()));
	} catch (SQLException e) {
		 
	}
	 m_serverLogView.WriteToLog("client Disconnected: "+client.getInetAddress().getHostAddress());
		super.clientDisconnected(client);
	}
 @Override
	protected void clientConnected(ConnectionToClient client) {
	 m_serverLogView.WriteToLog("client Connected: "+client.getInetAddress().getHostAddress());
		super.clientConnected(client);
	}

}

//End of EchoServer class   
