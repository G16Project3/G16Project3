package server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import entity.User;

/**
 * This Class is a server Controller 
 * @author G16
 */
public class ServerController  {
	
	/**
	 * ServerController fields
	 * @author G16
	 */
	private ServerGui ServerView;
	private serverLogGui serverLogView;
	private ServerController temp;
	private Connection conn; 
	private  ArrayList<User> userLog;
	private String userName = "root";
	private String password1 = "12345";
	private String Defport = "5555";
	private int port = 0;
	private String Scheme = "localhost:4444/myfuel";
	private ServerConnectionManager sv;
	
	/**
	 * ServerController constructor
	 * @author G16
	 * @param SerGui is the start gui that open first when we open 
	 * the server - we need the fields : port, user name and password to send to workbench
	 * @param servLog show when client connect or disconnect to server
	 */
	public ServerController(ServerGui SerGui,serverLogGui servLog){
		ServerView = SerGui;
		serverLogView = servLog;
		temp = this;
		ServerView.setTextFieldPass(password1);
		ServerView.setTextFieldUser(userName);
		ServerView.setTextFieldPort(Defport);
		ServerView.setTextFieldscheme(Scheme);
		userLog = new ArrayList<User>();
		ServerView.addLoginActionListener(new LoginListener());
		serverLogView.addDisconnectedBottonActionListener(new DisconnectedListener());
	}
	
	/**
	 *  Inner class that handles when Button Login Pressed, implements ActiontListener
	 * @author G16
	 *
	 */
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			userName = ServerView.getTextUserName();
			password1 = ServerView.getTextPassword();
			Defport = ServerView.getTextPort();
			port = Integer.parseInt(Defport);
			Scheme = "jdbc:mysql://" + ServerView.getTextScheme();
			
			if(openConnectionDB()){
				 sv = new ServerConnectionManager(port,serverLogView);
				 DBManager.getInstance().setConn(conn);
				 
				   try 
				    {
				      sv.listen(); //Start listening for connections
				      sv.setController(temp);
				      ServerView.dispose();
				      serverLogView.setVisible(true);
				    } 
				    catch (Exception ex) 
				    {
				    	 ServerView.setWarningMessageVisibleTrue("ERROR - Could not listen for clients!");
				    }
				
			}
			
		}
	
	}
	
	/**
	 * openConnectionDB is method that check if the open Connection to DB
	 * @return boolean
	 */
	  public boolean openConnectionDB(){
			try 
			{
	          Class.forName("com.mysql.jdbc.Driver").newInstance();
	      } catch (Exception ex) {/* handle the error*/}
	      
	      try 
	      {
	          conn = DriverManager.getConnection(Scheme,userName,password1);
	          System.out.println("SQL connection succeed");
	          return true;
	          
	   	} catch (SQLException ex) 
	   	    {/* handle any errors*/
	          ServerView.setWarningMessageVisibleTrue(ex.getMessage());
	          System.out.println("SQLException: " + ex.getMessage());
	          System.out.println("SQLState: " + ex.getSQLState());
	          System.out.println("VendorError: " + ex.getErrorCode());
	          return false;
	          }
		  
	}
	  
	  /**
	   * Inner class that handles when Button Logout Pressed, implements ActiontListener
	   * @author G16
	   *
	   */
	class DisconnectedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			System.out.println("Server is offline");
		System.exit(0);
			
		}
	
	}
	
	/**
	 * set the new log of Employee that connect or disconnect to server
	 * @param e1
	 * @param Task
	 */
	public void SetLog(User e1, String Task){

        if(Task.equals("login")){
        	userLog.add(e1);
        	serverLogView.getTextArea().setForeground(Color.green);
        	serverLogView.getTextArea().append(" id: " + e1.getUserId() + ",  is connected\n");	
        }
        
        if(Task.equals("logout")){
        	serverLogView.getTextArea().setForeground(Color.red);
        	serverLogView.getTextArea().append(" id: " + e1.getUserId()  +  ",  Disconnected\n");	
        	userLog.remove(e1);
        
        }	
		
	}
	
	/******************Getters and setters*****************/
	public ServerGui getServerView() {
		return ServerView;
	}

	public void setServerView(ServerGui serverView) {
		ServerView = serverView;
	}

	public serverLogGui getServerLogView() {
		return serverLogView;
	}

	public void setServerLogView(serverLogGui serverLogView) {
		this.serverLogView = serverLogView;
	}
	
	 public void setPassword1(String password1) {
			this.password1 = password1;
		}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
