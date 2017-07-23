package controller;


import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import client.IHandleMessage;
import client.MyFuelApp;
import entity.CarFuelOrder;
import entity.Dummy;
import entity.Envelope;
import entity.OrderHomeFuel;
import entity.User;
import gui.UserGUI;
import gui.CEOGUI;
import gui.UserGUI.*;
import gui.*;


/**
 * This Class is a Login Controller, which extends AbstractTransfer
 * @author G16
 *
 **/
public class UserController extends AbstractTransfer implements IHandleMessage{

	/** user's GUI */
	private UserGUI userG;
		
	/** user's details */
	private User user;
	
	/** tab indicator */
	public static int currTab = 0;  //1 = Car fuel order, 2 = Home fuel order , 3 = tracking orders //

	/** Daemon to run thread and wait for action*/
	private Daemon b;
	
	/** previous controller */
	LoginController prevController;


	/**
	 * UserController constructor
	 * @author G16
	 * @param UG
	 * @param U
	 * @param lastCon
	 */
	public UserController(UserGUI UG,User U,LoginController lastCon ) 
	{
		userG = UG;
		user = U;
		prevController = lastCon;
		init();
	}
	
	/**
	 * this method initiate the user controller
	 */
	public void init(){
		currTab = 1;
		userG.setLblWelcome(user.getUserName());
		b = new Daemon();
		b.start();
		
		
		
		
	}
/**
 * this method opens a new GUI
 * @author G16
 */
	public void openNewGui()
	{
		UserGUI userView = new UserGUI();
		userG = userView;
		init();
	}
	
	
/****************************On Message Received Start*****************************/		
	
	// then u get the massage from the server with an answer //
	
	/**
	 * this method decides what to do when returning from DB
	 * with data. mainly used to notify the user and fill the orders table.
	 * @author G16
	 */
	@Override
	 public void OnMessageRecived(Envelope message) {

		if (message.getTask().equals("AddNewCarFuelOrder")) {	
			if(message.getMessage().equals("AddNewCarFuelOrder"))
				JOptionPane.showMessageDialog(null,"New car fuel order succeeded");
				if(message.getMessage().equals("notInSystem"))
					JOptionPane.showMessageDialog(null,"customer id dose not in the system");
		}else if (message.getTask().equals("AddNewHomeFuelOrder"))
		{
			JOptionPane.showMessageDialog(null, "New home fuel order succeeded");
		}else if (message.getTask().equals("UserOrders"))
		{
			if ((Object[][])message.getObject()==null) 
				JOptionPane.showMessageDialog(null, "No orders!");
			else{
				userG.setTableRows((Object[][])message.getObject());
				userG.VecToTable.setDataVector(userG.getTableRows(), new String[] {"order Id", "invited by id", "quantity", "supply address", "supply date", "supply time", "order type"});
				}
		}
		}
/****************************On Message Received End*****************************/		
	
	/** 
	 * daemon thread: 
	 * checking what is the current tab
	 * and checks if DB changes for the current tab
	 * MultiThreading method
	 */
	public class Daemon extends Thread{

		@Override
		public void run(){
			userG.setTempOrder(new CarFuelOrder()) ;
			userG.setTempHomeOrder(new OrderHomeFuel());
			while (true){
				try {
				    Thread.sleep(300);
					if ((currTab == 1) && (userG.carAcceptClick == 1))
					{
						userG.getTempOrder().setCustomer_id(user.getUserId());
						Envelope en2 = new Envelope(userG.getTempOrder(),"AddNewCarFuelOrder");
						MyFuelApp.clien.SendMsgToServer(en2, UserController.this);
						Thread.sleep(500);
						userG.carAcceptClick =0;
						}
				
					if (currTab == 2 && userG.homeAcceptClick == 1){
						userG.getTempHomeOrder().setInvitedById(user.getUserId());
						Envelope en3 = new Envelope(userG.getTempHomeOrder(),"AddNewHomeFuelOrder");
						MyFuelApp.clien.SendMsgToServer(en3, UserController.this);
						Thread.sleep(500);
						userG.homeAcceptClick=0;
						}
					if (currTab == 3 && userG.Tab3Ckick == 1)
					{
						Envelope en4 = new Envelope(user,"UserOrders");
						MyFuelApp.clien.SendMsgToServer(en4, UserController.this);
						Thread.sleep(500);
						userG.Tab3Ckick=0;
					}
					}
				
				catch (InterruptedException e) {
					System.out.println("err daemon");
					e.printStackTrace();
				}
				
					}
			
				}
			}
	
	
}


