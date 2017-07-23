// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import server.*;
import gui.*;
import entity.Dummy;
import entity.Envelope;
import entity.User;
import ocsf.ocsfClient.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

import controller.*;


/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class MyFuelClient extends ObservableClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  ArrayList<String> Ar;
  private Object currController;
  private User currUser = null;
  private Dummy currString = null;
 // private LoginGui1
  private IHandleMessage m_handler;

  
  //Constructors ****************************************************
  
/**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  public MyFuelClient(String host, int port) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    Ar = new ArrayList<>();
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public synchronized void handleMessageFromServer(Object message)  
  {
	  if (m_handler!=null) {
		  m_handler.OnMessageRecived((Envelope) message);
		  m_handler=null;
	}

	notify();   
  }
  
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
    
  }
  
  /**
   * send msg to server from client
   * @param message
   * @param handler
   * @author G16
   */
  public void SendMsgToServer(Envelope message,IHandleMessage handler){
	  m_handler=handler;
	  try {
		this.sendToServer(message);
	} catch (IOException e) {
		
	}
	  
  }
  
  public Object getCurrObj() {
	return currController;
}
  
  public User getCurrUser() {
		return currUser;
	}
  
  public Dummy getCurrString() {
	return currString;
}

public void setCurrString(Dummy currString) {
	this.currString = currString;
}

	public  void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


public void setCurrObj(Object currObj) {
	this.currController = currObj;
}
/**
 * This method terminates the client.
 */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
