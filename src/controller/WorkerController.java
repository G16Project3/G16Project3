
package controller;

import gui.*;
import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.IHandleMessage;
import client.MyFuelApp;
import client.CliMessage;
import client.IObserve;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This Class is a System manager Controller, which extends AbstractTransfer 
 * class purpose is to take care of all system manager roles functions on the system
 * and implements IHandleMessage for messages that received from server
 * @author G16
 */
public class WorkerController extends AbstractTransfer implements IHandleMessage{
	
	/** Worker's GUI */
	private WorkerGUI sysManG;
	
	/** Marketing Manager's GUI */
	private MarketingManager markManG;
	
	/** Station Manager's GUI */
	private StationManagerGUI staManG;
	
	/** Marketing Worker's GUI */
	private MarketingWorkerGUI markWorG;
	
	/** CEO's GUI */
	private CEOGUI ceoG;
	
	/** a System Manager details */
	private Worker sysMan;
	
	/** indicates current tab we see */
	public static int currTab = 0;
	
	/** previous controller */
	LoginController prevController;
	
	/** station manager gui -
	 * a new minimum fuel level to be set */
	public int setFuelLevel = 0;
	
	/** station manager gui -
	 * flag: 0-nothing, 1-set minimum level been clicked */
	public int setFuelClicked = 0;
	
	/** station manager gui -  
	 *  flag: 0-nothing, 1-show clicked */
	private int showClicked = 0;
	
	/** station manager gui -  
	 *  flag: 0-nothing, 1-order fuel clicked */
	private int orderClicked = 0;
	
	/** station manager gui -  
	 *  flag: 0-nothing, 1-produce clicked */
	private int produceClicked = 0;
	
	/** station manager gui -  
	 *  helps deciding what to show */
	private int whatToShow = 0;
	
	/** station manager gui -  
	 *  helps deciding what to add */
	private int whatToAdd = 0;
	
	/** station manager gui -  
	 *  helps deciding to whom to show */
	private int whoToAdd = 0;
	
	/** station manager gui -  
	 *  helps deciding what to produce */
	private int whatToProduce = 0;
	
	/** station manager gui -  
	 *  stores a quarter selected */
	private int selectedQuarter = 0;
	
	/** station manager gui -  
	 *  stores a year selected */
	private int selectedYear = 0;
	
	/** a global index :) */
	int globalIndex;
	
	/** Station Manager's fuel station */
	FuelStation fuelStation;
	
	/** fuel acquires from a fuel station*/
	public Acquires tempAcq = null;

	
	/**
	 * WorkerController constructor - Marketing Manager
	 * @param SMG
	 * @param SM
	 * @param lastCon
	 * @author G16
	 */
	public WorkerController(MarketingManager SMG,Worker SM,LoginController lastCon ) 
	{
		markManG = SMG;
		sysMan = SM;
 		prevController = lastCon;
		initMarketingManager();
	}

	/**
	 * WorkerController constructor - Station Manager
	 * @param SMG
	 * @param SM
	 * @param lastCon
	 * @author G16
	 */
	public WorkerController(StationManagerGUI SMG,Worker SM,LoginController lastCon ) 
	{
		staManG = SMG;
		sysMan = SM;
		prevController = lastCon;
		initStationManager();
	}
	
	/**
	 * WorkerController constructor - Marketing Worker
	 * @param SMG
	 * @param SM
	 * @param lastCon
	 * @author G16
	 */
	public WorkerController(MarketingWorkerGUI SMG,Worker SM,LoginController lastCon ) 
	{
		markWorG = SMG;
		sysMan = SM;
		prevController = lastCon;
		initMarketingWorker();
	}

	/**
	 * WorkerController constructor - CEO 
	 * @param SMG
	 * @param SM
	 * @param lastCon
	 * @author G16
	 */
	public WorkerController(CEOGUI SMG,Worker SM,LoginController lastCon ) 
	{
		ceoG = SMG;
		sysMan = SM;
		prevController = lastCon;
		initCeo();
	}
		
	/**
	 * initialize CEO GUI and functionality
	 * @author Omri
	 */
	public void initCeo(){
		currTab =1;
		ceoG.createTabActionListener(new CEOTabListener());
		CEODaemon a = new CEODaemon();
		a.start();
	}
	
	/**
	 * initialize Marketing Worker GUI and functionality
	 * @author Tal
	 */
	public void initMarketingWorker(){
		currTab = 1;
		markWorG.createTabActionListener(new marketingWorkerTabListener());
		MarketingWorkerDaemon a = new MarketingWorkerDaemon();
		a.start();
	}
	
	/**
	 * initialize Station Manager GUI and functionality
	 * @author Maor
	 */
	public void initStationManager(){
		currTab = 1;
		staManG.createTabActionListener(new stationManagerTabListener());
		staManG.setLblNoStationInVisible();
		Envelope ne = new Envelope(sysMan.getUserId(),"getFuelStationDetails");
		MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);
		staManG.btnSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFuelLevel = ((Number)staManG.SPFuelLevel.getValue()).intValue();
				if (setFuelLevel > 0)
				{
					setFuelClicked = 1;
					staManG.setWarningInVisible();
				}
				else staManG.setWarningVisible();
			}
		});
		staManG.btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				whatToShow = staManG.fuelAut.getSelectedIndex();
				showClicked = 1;
			}
		});
		staManG.btnManOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				whatToAdd = ((Number)staManG.SPOrderAmount.getValue()).intValue();
				if ( whatToAdd > 0 )
				{
					whoToAdd = staManG.fuelMan.getSelectedIndex();
					staManG.setFuelAmountWarningInVisible();
					orderClicked = 1;
				}
				else staManG.setFuelAmountWarningVisible();
			}
		});
		staManG.btnAutOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				whatToAdd = 2000;
				whoToAdd = staManG.fuelAut.getSelectedIndex();
				orderClicked = 1;
			}
		});
		staManG.btnProduce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (staManG.yearBox.getSelectedIndex() == 0)
					selectedYear = 2015;
				else selectedYear = 2016;
				selectedQuarter = staManG.quarterBox.getSelectedIndex();;
				whatToProduce = staManG.CBReportType.getSelectedIndex();
				produceClicked = 1;
			}
		});
		staManG.btnProduceInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				staManG.setTextPane("Inventory:\n\nBenzine95: " + fuelStation.getFuel95Amount() + 
									"\nDiesel: " + fuelStation.getDieselAmount() + "\nMotors: " + 
									fuelStation.getMotorsFuelAmount() );	
			}
		});
		StatioManagerDaemon a = new StatioManagerDaemon();
		a.start();
	}
	
	/**
	 * initialize Marketing Manager GUI and functionality
	 * @author Arthur
	 */
	public void initMarketingManager(){
		currTab = 1;
		marketingManagerDaemon a = new marketingManagerDaemon();
		a.start();
	}
	
	/** 
	 * method that deleting request from DB by request id
	 */
	public void deleteRequest(Dummy dm){

		Envelope en1 = new Envelope(dm,"DeleteRequest");
		MyFuelApp.clien.SendMsgToServer(en1, WorkerController.this);
	}
	
	
	/** Handles messages returned from DB. 
	 * mainly manages changes at GUIs and adds notification for different types of
	 * workers. gets details from DB and change stuff on GUI or notify workers instantly
	 * @author G16
	 */
	 @Override
	 public void OnMessageRecived(Envelope message) {

		if (message.getTask().equals("AddNewSale"))
		{
			JOptionPane.showMessageDialog(null, "New sale is done and waiting for CEO approve");
		}
		
		else if (message.getTask().equals("RatesUpdate"))
		{
			JOptionPane.showMessageDialog(null, "Rates to update send successfully");
		}
		
		else if (message.getTask().equals("AddNewCar")){
			JOptionPane.showMessageDialog(null, "New car added successfully");
		}
		
		else if(message.getTask().equals("AddNewCustomer")){
			JOptionPane.showMessageDialog(null, "New customer added successfully");
		}
		
		else if(message.getTask().equals("getFuelStationDetails")){
			fuelStation = (FuelStation)message.getObject();
			if (fuelStation != null){
				if (Math.min(Math.min(fuelStation.getDieselAmount(), fuelStation.getMotorsFuelAmount()), fuelStation.getFuel95Amount()) < fuelStation.getMinLevelAmount() )
					JOptionPane.showMessageDialog(null, "Your fuel is running low!");
				staManG.setLblCurrentFuelLevel(fuelStation.getMinLevelAmount());
			}
			else staManG.setLblNoStationVisible();
		}
		
		else if (message.getTask().equals("AddFuel"))
		{
			JOptionPane.showMessageDialog(null, "Fuel been added!");
		}		
		else if (message.getTask().equals("ProduceAcquires"))
		{

			int dieAmount = 0;
			int benAmount = 0;
			int motAmount = 0;
			int totalAmount = 0;
			
			Acquires[] temp = (Acquires[])message.getObject();
			for(int i = 0 ; i < temp.length ; i++)
			{

				if ( ( temp[i].getYear() == selectedYear ) && 
					( ( Math.ceil(temp[i].getMonth()/3.0)-1) == selectedQuarter ) ) {
					
					switch(temp[i].getFuelType()){
						case "Benzine95":
							benAmount += temp[i].getAmount();
							break;
						case "Diesel":
							dieAmount += temp[i].getAmount();
							break;
						case "Motors":
							motAmount += temp[i].getAmount();
							break;
					}
				}

			}
			totalAmount = benAmount + dieAmount + motAmount;
			if (totalAmount == 0)
				staManG.setTextPane("No acquires in this quarter!");	
			else staManG.setTextPane("Quarter " + selectedQuarter + " ,Year " + selectedYear + ":\n" +
									"Benzine95: "+ benAmount + "\nDiesel: " + dieAmount + "\nMotors: " + 
									motAmount + "\nTotal: " + totalAmount);

		}
		
		else if (message.getTask().equals("ProduceIncomes"))
		{
			int total = 0;
			Date dateTemp;
			SimpleDateFormat month,year;
			Invoice[] temp = (Invoice[])message.getObject();
			for (int i = 0 ; i < temp.length ; i++)
			{
				dateTemp = temp[i].getOrderDate();
				month = new SimpleDateFormat("MM");
				year = new SimpleDateFormat("yyyy");
				if ( ( Integer.parseInt(year.format(dateTemp)) == selectedYear ) && 
						( ( Math.ceil(Integer.parseInt(month.format(dateTemp))/3.0)-1) == selectedQuarter ) )
					total += temp[i].getPrice();
			}
			if (total == 0)
				staManG.setTextPane("No incomes in this quarter!");	
			else staManG.setTextPane("Quarter " + selectedQuarter + " ,Year " + selectedYear + ":\n" +
									"Total income: "+ total);
		}
		
		else if (message.getTask().equals("checkCampaign"))
		{
			ceoG.setTableRows((Object[][])message.getObject());
			ceoG.VecToTable.setDataVector(ceoG.getTableRows(), new String [] {"Campaign Id", "Campaig name", "Discount percent", "start date", "End date", "Start hour", "End hour","Status"});
		}
		else if (message.getTask().equals("checkRate")){
			ceoG.setTableRows((Object[][])message.getObject());
			ceoG.VecToTable2.setDataVector(ceoG.getTableRows(),new String [] {"maxMotorsFuel", "Motors", "maxbenzine95", "Benzine95", "maxDiesel", "Diesel", "maxHomefuel", "HomeFuel"," RatesId"});
		}
		else if (message.getTask().equals("checkQuarter"))
		{
			ceoG.setTableRows((Object[][])message.getObject());
			ceoG.VecToTable1.setDataVector(ceoG.getTableRows(), new String [] {"ReportQuarterId", "totalIncome", "Benzine95", "Diesel", "Motors", "Quarter", "year"});
		}
		else if (message.getMessage().equals("aproveCampaign"))
		{
			JOptionPane.showMessageDialog(null, "Campaign approved");		
		}
		else if (message.getMessage().equals("NotAproveCampaign"))		{
			JOptionPane.showMessageDialog(null, "Campaign id doesn't exist");		
		}
		else if(message.getTask().equals("checkWeeklyReportCustomerType")){
			markWorG.setTableRows((Object[][])message.getObject());
			markWorG.VecToTable.setDataVector(markWorG.getTableRows(),new String [] {"customer Id","Type","rank"});
			JOptionPane.showMessageDialog(null,"check");
		}	else if(message.getTask().equals("checkFuelType")){
			markWorG.setTableRows((Object[][])message.getObject());
			markWorG.VecToTable2.setDataVector(markWorG.getTableRows(),new String [] {"Fuel type","quantity","Rank"});
			JOptionPane.showMessageDialog(null,"check2");
		}
		else if(message.getMessage().equals("ProduceQuarterReport")){

			JOptionPane.showMessageDialog(null,"Quarter report has been created");
		
		}
		else if(message.getMessage().equals("ProduceQuarterExist")){

			JOptionPane.showMessageDialog(null,"Quarter report is all ready exist");
		
		}
		else if (message.getTask().equals("CheckRatesRequest"))
		{
			if ((Object[][])message.getObject()==null) 
				JOptionPane.showMessageDialog(null, "null");
			else{
				markManG.setTableRowsfuelrates((Object[][])message.getObject());
				markManG.VecToTable.setDataVector(markManG.getTableRowsfuelrates(), new String[] {"Fuel type", "Current price", "Price requested", "Status"});
				}
		}
		
		else if (message.getTask().equals("SaleReactionReport"))
		{
			if ((Object[][])message.getObject()==null) 
				JOptionPane.showMessageDialog(null, "null");
			else{
				markManG.setListItems((Object[][])message.getObject());
				markManG.VecToTable2.setDataVector(markManG.getListItems(), new String[] {"Sale Id", "Campaig Name", "Discount Percent", "Start Day", "End Day", "Start Hour", "End Hour"});
				}
		}
		
		else if (message.getTask().equals("SaleReactionReportProduce"))
		{
			markManG.setSaleReactionReportProduce((Object[][])message.getObject());
			markManG.VecToTable3.setDataVector(markManG.getSaleReactionReportProduce(), new String[] {"Sale name", "Customer amount", "TBD"});
		}
		
		else if (message.getTask().equals("setWeekRate"))
		{
			JOptionPane.showMessageDialog(null,"new rank are setted");
		}
		else if(message.getTask().equals("approvRate"))
		{
			if(message.getMessage().equals("approvRate"))
				JOptionPane.showMessageDialog(null,"rate has been changed");
			else
				JOptionPane.showMessageDialog(null,"rate has not been changed");

		}
		else if (message.getTask().equals("CustomerReport"))
		{
			markManG.setCustomerReport((Object[][])message.getObject());
			markManG.VecToTable4.setDataVector(markManG.getCustomerReport(), new String[] {"Customer Id", "Rank", "Station ID"});
		}
	}
	 
	 /** 
		 * daemon thread - Marketing Worker
		 * checking what is the current tab
		 * and checks if button been pressed, in case
		 * a button was pressed connect to DB and make 
		 * changes/gather info.
		 * MultiThreading method.
		 * @author Tal
		 */
	 public class MarketingWorkerDaemon extends Thread{

		 @Override
			public void run(){
			 markWorG.setTempCustomer(new Customer());
			 markWorG.setTempCar(new Car());
			 markWorG.setTempUser(new User());
				while (true){
					try {
						Thread.sleep(1000);
						if ((currTab == 1) && (markWorG.newCustomerAdd == 1) ){
							Envelope en1 = new Envelope(markWorG.getTempCustomer(),"AddNewCustomer");
							MyFuelApp.clien.SendMsgToServer(en1, WorkerController.this);
							Thread.sleep(200);
							markWorG.newCustomerAdd =0;
						}if ((currTab == 1) && (markWorG.newCustomerAdd == 0)&& (markWorG.newUserAdd == 1)){
							Envelope en = new Envelope(markWorG.getTempUser(),"AddNewUser");
							MyFuelApp.clien.SendMsgToServer(en, WorkerController.this);
							markWorG.newUserAdd = 0 ;
							Thread.sleep(200);
						}
						if ((currTab == 2) && (markWorG.newCarAdd == 1)){
							Envelope en2 = new Envelope(markWorG.getTempCar(),"AddNewCar");
							MyFuelApp.clien.SendMsgToServer(en2, WorkerController.this);
							Thread.sleep(500);
							markWorG.newCarAdd = 0;
						}
						if ((currTab == 3) && (markWorG.creWeeklyReport == 1)){
									Envelope ne = new Envelope(null,"checkWeeklyReportCustomerType");
									MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);	
									markWorG.creWeeklyReport = 0;
							Thread.sleep(500);
						}
						if((currTab == 3) && (markWorG.creFuelReport == 1)){
						Envelope ne = new Envelope(null,"checkFuelType");
						MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);
						markWorG.creFuelReport = 0;
						Thread.sleep(500);
					
					}if((currTab == 3) && (markWorG.newSetWeekRate == 1)){
						int op = JOptionPane.showConfirmDialog(null, "Are you sure you want to sent a new rank  ?", "warning", JOptionPane.YES_NO_OPTION);
						if(op == 0){
						Envelope ne = new Envelope(null,"setWeekRate");
						MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);
						}
					markWorG.newSetWeekRate = 0;
					Thread.sleep(500);
					}
				}
					catch (InterruptedException e) {e.printStackTrace();}
				}
					
				}
		 
	 }

	 /** 
		 * daemon thread - Marketing Manager
		 * checking what is the current tab
		 * and checks if button been pressed, in case
		 * a button was pressed connect to DB and make 
		 * changes/gather info.
		 * MultiThreading method.
		 * @author Arthur
		 */
	 public class marketingManagerDaemon extends Thread{
		 public void run(){
				Envelope en7 = new Envelope(null,"SaleReactionReport");
				MyFuelApp.clien.SendMsgToServer(en7, WorkerController.this);

			 while (true){
					try {
						Thread.sleep(1000);
						if ((currTab == 1) && (markManG.newSaleAdd == 1) ){
							Envelope en2 = new Envelope(markManG.getTempNewSale(),"AddNewSale");
							MyFuelApp.clien.SendMsgToServer(en2, WorkerController.this);
							Thread.sleep(500);
							markManG.newSaleAdd =0;
						}
						if ((currTab == 2) && (markManG.CheckRatesRequestclick==1)){
							Envelope en3 = new Envelope(null,"CheckRatesRequest");
							MyFuelApp.clien.SendMsgToServer(en3, WorkerController.this);
							Thread.sleep(500);
							markManG.CheckRatesRequestclick=0;
						}
						if ((currTab == 3) && (markManG.updateRatesSendClick == 1)){
							Envelope en4 = new Envelope(markManG.getTempRatesUpdate(),"RatesUpdate");
							MyFuelApp.clien.SendMsgToServer(en4, WorkerController.this);
							Thread.sleep(500);
							markManG.updateRatesSendClick=0;
						}
						if  ((currTab == 4) && (markManG.SaleReactionClick==1)){
							Envelope en5 = new Envelope(markManG.SaleId,"SaleReactionReportProduce");
							MyFuelApp.clien.SendMsgToServer(en5, WorkerController.this);
							markManG.SaleReactionClick=0;
							Thread.sleep(500);
						}
						if  ((currTab == 5) && (markManG.CustomerReportclick==1)){
							
							Envelope en6 = new Envelope(markManG.getTempNewSale(),"CustomerReport");
							MyFuelApp.clien.SendMsgToServer(en6, WorkerController.this);
							Thread.sleep(500);
							markManG.CustomerReportclick=0;
						}
					
					}
					catch (InterruptedException e) {e.printStackTrace();}
				}
					
				}
		 
	 }

	 /** 
		 * daemon thread - Station Manager
		 * checking what is the current tab
		 * and checks if button been pressed, in case
		 * a button was pressed connect to DB and make 
		 * changes/gather info.
		 * MultiThreading method.
		 * @author Maor
		 */
	 public class StatioManagerDaemon extends Thread{

		public void run(){
				while (true){
					try {
						Thread.sleep(1000);
						if ((currTab == 1) && (setFuelClicked == 1)){
							int[] temp = {sysMan.getUserId(), setFuelLevel};
							Envelope en1 = new Envelope(temp ,"SetFuelLevel");
							MyFuelApp.clien.SendMsgToServer(en1, WorkerController.this);
							staManG.setLblCurrentFuelLevel(setFuelLevel);
							fuelStation.setMinLevelAmount(setFuelLevel);
							JOptionPane.showMessageDialog(null, "Fuel lowest level changed!");
							setFuelClicked = 0;
							Thread.sleep(500);
						}
						if ((currTab == 2) && (showClicked == 1)){
							switch(whatToShow){
							case 0:
								staManG.setLblCurrentAmount(fuelStation.getFuel95Amount());
								break;
							case 1:
								staManG.setLblCurrentAmount(fuelStation.getDieselAmount());
								break;
							case 2:
								staManG.setLblCurrentAmount(fuelStation.getMotorsFuelAmount());
								break;
							}
							showClicked = 0;
							Thread.sleep(500);
						}
						if ((currTab == 2) && (orderClicked == 1)){
							Date date = Calendar.getInstance().getTime();
							SimpleDateFormat day = new SimpleDateFormat("dd");
							SimpleDateFormat month = new SimpleDateFormat("MM");
							SimpleDateFormat year = new SimpleDateFormat("yyyy");
							switch(whoToAdd){
							case 0:
								tempAcq = new Acquires(Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)),
										Integer.parseInt(year.format(date)), whatToAdd, fuelStation.getFuelStationId() , "Benzine95" );
								whatToAdd += fuelStation.getFuel95Amount();
								fuelStation.setFuel95Amount(whatToAdd);
								break;
							case 1:
								tempAcq = new Acquires(Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)),
										Integer.parseInt(year.format(date)), whatToAdd, fuelStation.getFuelStationId() , "Diesel" );
								whatToAdd += fuelStation.getDieselAmount();
								fuelStation.setDieselAmount(whatToAdd);
								break;
							case 2:
								tempAcq = new Acquires(Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)),
										Integer.parseInt(year.format(date)), whatToAdd, fuelStation.getFuelStationId() , "Motors" );
								whatToAdd += fuelStation.getMotorsFuelAmount();
								fuelStation.setMotorsFuelAmount(whatToAdd);
								break;
							}
							int[] temp = {sysMan.getUserId(), whatToAdd, whoToAdd};
							Envelope en2 = new Envelope(temp,"AddFuel");
							MyFuelApp.clien.SendMsgToServer(en2, WorkerController.this);
							en2.setTask("AddAcquire");
							en2.setObject(tempAcq);
							MyFuelApp.clien.SendMsgToServer(en2, WorkerController.this);
							whatToAdd = 0;
							orderClicked = 0;
							Thread.sleep(500);
						}

						if ((currTab == 3) && (produceClicked == 1)){
							Envelope en3 = new Envelope(fuelStation.getFuelStationId(),"");
							switch(whatToProduce)
							{
								case 0:
									en3.setTask("ProduceIncomes");
									break;
								case 1:
									en3.setTask("ProduceAcquires");
									break;
							}
							MyFuelApp.clien.SendMsgToServer(en3, WorkerController.this);
							whatToProduce = 0;
							produceClicked = 0;
							Thread.sleep(500);
						}
					}
					catch (InterruptedException e) {e.printStackTrace();}
				}
		 }
	 }
	 
	 /** 
		 * daemon thread - CEO
		 * checking what is the current tab
		 * and checks if button been pressed, in case
		 * a button was pressed connect to DB and make 
		 * changes/gather info.
		 * MultiThreading method.
		 * @author Omri
		 */
	 public class CEODaemon extends Thread{

		 @Override
			public void run(){
			
			 while (true){
					try {
						Thread.sleep(1000);
						if ((currTab == 1 )&&(ceoG.chcekFlag==1))
						{
							Envelope ne = new Envelope(null,"checkCampaign");
							MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);		
							ceoG.chcekFlag=0;
						}								

							if((currTab == 1 )&&(ceoG.approvFlag==1))
							{
								int op = JOptionPane.showConfirmDialog(null, "Are you sure you want to approve ?", "warning", JOptionPane.YES_NO_OPTION);
									if(op==0)
									{
										Envelope ne = new Envelope( Integer.parseInt(ceoG.textAprove.getText()),"aproveCampaign");
										MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);

									}
									ceoG.approvFlag=0;
									Thread.sleep(500);
							}

						if (currTab == 2 && ceoG.chooseReport != null ){
							Envelope en2 = new Envelope(ceoG.chooseReport,"ProduceQuarterReport");
							MyFuelApp.clien.SendMsgToServer(en2, WorkerController.this);
							ceoG.chooseReport = null;
							Thread.sleep(500);
						}
						if (currTab == 3 && ceoG.chcekQFlag == 1 ){
							String[] obj = new String [2];
							obj[0]=ceoG.chooseQuarterReport;
							obj[1]=ceoG.textField.getText();
							Envelope ne0 = new Envelope(obj,"checkQuarter");
							MyFuelApp.clien.SendMsgToServer(ne0, WorkerController.this);		
							ceoG.chcekQFlag=0;
							Thread.sleep(500);
						}
						
						if(ceoG.checkRateFlag==1){							
									Envelope ne = new Envelope( null,"checkRate");
									MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);
									Thread.sleep(500);
									ceoG.checkRateFlag=0;
							}
						if(ceoG.approvCheckFlag==1)
						{
								int op = JOptionPane.showConfirmDialog(null, "Are you sure you want to approve the rate ?", "warning", JOptionPane.YES_NO_OPTION);
								if(op==0){
									Envelope ne = new Envelope(ceoG.chooseRate,"approvRate");
									MyFuelApp.clien.SendMsgToServer(ne, WorkerController.this);
								}
								ceoG.approvCheckFlag=0;
								Thread.sleep(500);
							}
						
					}
					catch (InterruptedException e) {e.printStackTrace();}
			 }
		 }
	 }

	 /**
	  * a tab listener.
	  * change tabs in Marketing Worker GUI
	  * @author G16
	  *
	  */
	class marketingWorkerTabListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			if(markWorG.getTab().getSelectedIndex()==0) currTab = 1;
			if(markWorG.getTab().getSelectedIndex()==1) currTab = 2;
		}
		
	}
	
	 /**
	  * a tab listener.
	  * change tabs in CEO GUI
	  * @author G16
	  *
	  */
	class CEOTabListener implements ChangeListener{
		@Override
			public void stateChanged(ChangeEvent arg0) {
				if(ceoG.getTab().getSelectedIndex()==0) currTab = 1;
				if(ceoG.getTab().getSelectedIndex()==1) currTab = 2;
				if(ceoG.getTab().getSelectedIndex()==2) currTab = 3;
			}		
	}
	
	 /**
	  * a tab listener.
	  * change tabs in Station Manager GUI
	  * @author G16
	  *
	  */
	class stationManagerTabListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			if(staManG.getTab().getSelectedIndex()==0) currTab = 1;
			if(staManG.getTab().getSelectedIndex()==1) currTab = 2;
			if(staManG.getTab().getSelectedIndex()==2) currTab = 3;
		}		
}
	
	
}


