package controller;

import gui.*;
import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.Executors;

import client.*;

/**
 * This Class is a Login Controller, which extends AbstractTransfer.
 * this class provides all of the login screen functionality.
 ** @author G16
 *
 */
public class LoginController extends AbstractTransfer implements IHandleMessage
{
	
	/** 
	 * LoginController fields
	 *  @author G16
	 */
	/** login gui*/
	private LoginGUI loginG;
	
	/** login model*/
	private LoginMod loginM;
	
	/** login controller*/
	private LoginController tempL;
	
	/** user model*/
	private User userModel;
	
	/** worker model*/
	private Worker sysManModel;
	
	/** screen flag:
	 * 1-user, 2-system_manager, 0-DUMMY
	 */
	private int screen = 0;
	
	/** flag that control the thread:
	 * 0-flag works, 1-thread doesn't work
	 */
	private int OpenSysFlag = 0;

	
	/**
	 * LoginController constructor
	 * @author G16
	 * @param lC is GUI instance
	 * @param lM Login Model instance - that include user name and password
	 */
	public LoginController(LoginGUI lC,LoginMod lM ) {
		//super(lC,lM);
		loginG = lC;
		loginM = lM;
		tempL = this;
		init();
	}

	/** 
	 * this method used to initiate the login screen
	 * @author G16
	 */
	public void init(){
		loginG.addLoginActionListener(new LoginListener());
		this.screen = 0;
		this.OpenSysFlag = 0;
		Daemon a = new Daemon();
		a.start();
	}
	
	/**
	 * this method opens a new login gui
	 * @author G16
	 */
	public void openNewGui()
	{
		LoginGUI userView = new LoginGUI();
		loginG = userView;
		init();
	}
	
	/**
	 * this method is used to decide what to do after we get user details from DB.
	 * if no user exists/wrong password show warning message.
	 * if screen=1 then it's a customer.
	 * if screen=2 then it's worker.
	 * this method won't allow a logged in user to log in again!
	 */
	@Override // this is the message from the server to know witch interface to open
	public void OnMessageRecived(Envelope envelope) {
		if(envelope.getMessage()==null)
			loginG.setWarningMessageVisibleTrue("Wrong password/user doesn't exist");
		else{
			if (screen==1)
			{
				User temp = (User)envelope.getMessage();
				if (temp.getUserLogStatus() == 0)
				{
					userModel= ((User)envelope.getMessage());
					OpenSysFlag = 1;
				}
				else loginG.setWarningMessageVisibleTrue("Customer \""+temp.getUserName()+"\" already logged in");
			}
			else
				if (screen == 2)
				{
					Worker SMtemp = (Worker)envelope.getMessage();
					if (SMtemp.getUserLogStatus() == 0)
					{
						sysManModel= ((Worker)envelope.getMessage());
						OpenSysFlag = 1;
					}
					else loginG.setWarningMessageVisibleTrue("Worker \""+SMtemp.getUserName()+"\" already logged in");
				}
		}
	}
	
	
	 
	/**
	 * get the login Gui 
	 * @return LoginGui1
	 */
	public LoginGUI getLoginG() {
		return loginG;
	}
	
	/**
	 * set login gui
	 * @param loginG
	 */
	public void setLoginG(LoginGUI loginG) {
		this.loginG = loginG;
	}

	/**
	 * get login model(entity)
	 * @return LoginMod
	 */
	public LoginMod getLoginM() {
		return loginM;
	}

	/**
	 * set log in model(entity)
	 * @param loginM
	 */
	public void setLoginM(LoginMod loginM) {
		this.loginM = loginM;
	}
	
	/**
	 * get instance  of current controller
	 * @return LoginController
	 */
	public LoginController getTempL() {
		return tempL;
	}
	
/**
 * Inner class that handles when Button login Pressed, implements ActiontListener.
 * opens a corresponding GUI to a user's role.
 * also used to implement a logout method.
 * @author G16
 *
 */
	class Daemon extends Thread{
		   @Override
		   public void run()
		   {
				System.out.println("Started daemon");
			   try {
				Thread.sleep(20);
			} catch (InterruptedException e1) 
			   {
					e1.printStackTrace();
			   }
			   	while (OpenSysFlag == 0)
				{
					try 
					{
						Thread.sleep(20);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				if (screen == 1)
				{
					loginG.setVisible(false);
					Envelope en = new Envelope(new Dummy(userModel.getUserName()),"LoginUpdateLogStatus");
   				 	MyFuelApp.clien.SendMsgToServer(en, LoginController.this);        				 
					final UserGUI userView = new UserGUI();
					UserController userController = new UserController(userView,userModel,tempL);
					userView.getBtnLogOut().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Envelope ne = new Envelope(loginM,"logoutUser");
       					 	MyFuelApp.clien.SendMsgToServer(ne, LoginController.this);
							userView.dispose();
							loginG.setVisible(true);
							tempL.init();
						}
					});
				}
				if (screen == 2)
				{
					loginG.setVisible(false);
					Envelope en = new Envelope(new Dummy(sysManModel.getUserName()),"LoginUpdateLogStatus");
   				 	MyFuelApp.clien.SendMsgToServer(en, LoginController.this);
   				 	String name = sysManModel.getRole1();
   				 	switch(name){
   				 	
   				 	case "MarketingManager":
   					 	final MarketingManager sysManView = new MarketingManager();
   						WorkerController sysManController = new WorkerController(sysManView,sysManModel,tempL);
   						sysManView.getBtnLogOut().addActionListener(new ActionListener() {
   							@Override
   							public void actionPerformed(ActionEvent e) {
   								Envelope ne = new Envelope(loginM,"logoutUser");
   	       					 	MyFuelApp.clien.SendMsgToServer(ne, LoginController.this);
   	       					 	sysManView.dispose();
   								loginG.setVisible(true);
   								tempL.init();
   							}
   						});
   				 		break;
   				 	case "MarketingWorker":
   					 	final MarketingWorkerGUI sysManView1 = new MarketingWorkerGUI();
   						WorkerController sysManController1 = new WorkerController(sysManView1,sysManModel,tempL);
   						sysManView1.getBtnLogOut().addActionListener(new ActionListener() {
   							@Override
   							public void actionPerformed(ActionEvent e) {
   								Envelope ne = new Envelope(loginM,"logoutUser");
   	       					 	MyFuelApp.clien.SendMsgToServer(ne, LoginController.this);
   	       					 	sysManView1.dispose();
   								loginG.setVisible(true);
   								tempL.init();
   							}
   						});
   				 		break;
   				 	case "CEO":
   					 	final CEOGUI sysManView2 = new CEOGUI();
   						WorkerController sysManController2 = new WorkerController(sysManView2,sysManModel,tempL);
   						sysManView2.getBtnLogOut().addActionListener(new ActionListener() {
   							@Override
   							public void actionPerformed(ActionEvent e) {
   								Envelope ne = new Envelope(loginM,"logoutUser");
   	       					 	MyFuelApp.clien.SendMsgToServer(ne, LoginController.this);
   	       					 	sysManView2.dispose();
   								loginG.setVisible(true);
   								tempL.init();
   							}
   						});
   				 		break;
   				 	case "StationManager":
   					 	final StationManagerGUI sysManView3 = new StationManagerGUI();
   						WorkerController sysManController3 = new WorkerController(sysManView3,sysManModel,tempL);
   						sysManView3.getBtnLogOut().addActionListener(new ActionListener() {
   							@Override
   							public void actionPerformed(ActionEvent e) {
   								Envelope ne = new Envelope(loginM,"logoutUser");
   	       					 	MyFuelApp.clien.SendMsgToServer(ne, LoginController.this);
   	       					 	sysManView3.dispose();
   								loginG.setVisible(true);
   								tempL.init();
   							}
   						});
   				 		break;
   				 	}
				}
				System.out.println("stopped daemon");
		   }
	}
	
	/**
	 * gets user details to be sent to server when login button pressed.
	 * @author G16
	 *
	 */
	class LoginListener implements ActionListener {
		
		
           public void actionPerformed(ActionEvent ev){
        	  
        		  String pass = loginG.getTextPassword();
        		  String user = loginG.getTextUserName();
        		  int type = 1;
        		  
        		  if(pass.equals("")|| user.equals("") ) {
        			  loginG.setWarningMessageVisibleTrue("Please fill required fields!");
        			  return;
        		  }
        		  else
        		  {
        			//  loginG.undisplayWarningMessage();
        			  try{ //set the user name and password and sent to server
        				 loginM.setPassword(pass);
        				 loginM.setUserName(user);
        				 Envelope en;
        				 if (loginG.getloginCustomerSelect()){
        					 en = new Envelope(loginM,"customerLogin");
        					 screen = 1;
        					 MyFuelApp.clien.SendMsgToServer(en, LoginController.this);
        				 }
        				 else
        				 {
        					 en = new Envelope(loginM,"workerLogin");
        					 screen = 2;
        					 MyFuelApp.clien.SendMsgToServer(en, LoginController.this);
        				 }
        				         				 
        			  }
        			  catch(Exception e){
        				  
        			  }
			
		      }
           }
	}
	
}
