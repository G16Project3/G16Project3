package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.UserController;
import gui.MarketingWorkerGUI.TabListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;

/**
 * this class is Station Manager GUI that draws the GUI 
 * @author G16
 *
 */
public class StationManagerGUI extends WorkerGUI {
	
	/** tabes */
	private JTabbedPane tabbedPane;
	
	/** Set min fuel level button*/
	public JButton btnSet;
	
	/** used to get min fuel level */
	public JSpinner SPFuelLevel;
	
	/** used to get order amount of fuel for a fuel station */
	public JSpinner SPOrderAmount;
	
	/** current fuel label*/
	private JLabel lblCurrentFuelLevel;
	
	/** warning label*/
	private JLabel lblWarning;
	
	/** station manager without fuel station label*/
	private JLabel lblNoStation;
	
	/** show fuel button*/
	public JButton btnShow;
	
	/** order fuel manually button*/
	public JButton btnManOrder;
	
	/** order fuel automatically button*/
	public JButton btnAutOrder;
	
	/** used to choose fuel to order manually*/
	public JComboBox fuelMan;
	
	/** used to choose fuel to order automatically*/
	public JComboBox fuelAut;
	
	/** current fuel amount label*/
	private JLabel lblCurrentAmount;
	
	/** fuel amount needed to get to 5000 liters label*/
	private JLabel lblAmountNeeded;
	
	/** warning label - maximum fuel*/
	private JLabel lblAlreadyFull;
	
	/** bad amount entered*/
	private JLabel badAmountWarning;
	
	/** text pane to show quarter reports */
	private JTextPane textPane;
	
	/** produce report button*/
	public JButton btnProduce;
	
	/** choose quarter report type*/
	public JComboBox CBReportType;
	
	/** choose quarter report year*/
	public JComboBox yearBox;
	
	/** choose quarter report quarter*/
	public JComboBox quarterBox;
	
	/** produce inventory status button*/
	public JButton btnProduceInventory;

	/**
	 * Station Manager GUI constructor
	 */
	public StationManagerGUI() {
				super();
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setBounds(100, 100, 780, 600);
				this.setTitle("MyFuel - Station Manager Window");
				this.setResizable(false);
				getContentPane().setLayout(null);
				getContentPane().add(getTab());
				this.setVisible(true);
				
				
				}

	/**
	 * Initialize the contents of the frame.
	 */
	public JTabbedPane getTab() {
		if(tabbedPane==null){
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			
		tabbedPane.addChangeListener(new TabListener());
		tabbedPane.setBounds(20, 128, 620, 328);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Set lowest levels for fuel order", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblFuelLevel = new JLabel("Fuel level");
		lblFuelLevel.setBounds(10, 42, 67, 23);
		panel.add(lblFuelLevel);
		
		btnSet = new JButton("SET");
		btnSet.setBounds(405, 240, 200, 50);
		panel.add(btnSet);
		
		SPFuelLevel = new JSpinner();
		SPFuelLevel.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		SPFuelLevel.setBounds(109, 42, 94, 23);
		panel.add(SPFuelLevel);
		
		lblCurrentFuelLevel = new JLabel("Current Fuel Level Minimum: ");
		lblCurrentFuelLevel.setBounds(10, 76, 329, 23);
		panel.add(lblCurrentFuelLevel);
		
		lblWarning = new JLabel("");
		lblWarning.setBounds(233, 46, 216, 19);
		lblWarning.setForeground (Color.red);
		panel.add(lblWarning);
		
		lblNoStation = new JLabel("No station for this user!");
		lblNoStation.setBounds(232, 110, 193, 39);
		lblNoStation.setForeground (Color.red);
		panel.add(lblNoStation);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Make a fuel order", null, panel_1, null);
		panel_1.setLayout(null);
		
		btnManOrder = new JButton("Order Manually");
		btnManOrder.setBounds(36, 201, 200, 50);
		panel_1.add(btnManOrder);
		
		JLabel lblOrderAmount = new JLabel("Order amount");
		lblOrderAmount.setBounds(10, 68, 118, 24);
		panel_1.add(lblOrderAmount);
		
		SPOrderAmount = new JSpinner();
		SPOrderAmount.setBounds(169, 69, 95, 23);
		panel_1.add(SPOrderAmount);
		
		JLabel lblFuelType = new JLabel("Fuel type");
		lblFuelType.setBounds(10, 103, 95, 14);
		panel_1.add(lblFuelType);
		
		fuelMan = new JComboBox();
		fuelMan.setModel(new DefaultComboBoxModel(new String[] {"Benzine95", "Diesel" ,"Motors"}));
		fuelMan.setBounds(151, 103, 113, 20);
		panel_1.add(fuelMan);
		
		JLabel lblManual = new JLabel("Manual:");
		lblManual.setBounds(62, 20, 174, 37);
		panel_1.add(lblManual);
		
		JLabel lblAutomatic = new JLabel("Automatic:");
		lblAutomatic.setBounds(380, 26, 166, 24);
		panel_1.add(lblAutomatic);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(318, 0, 1, 300);
		panel_1.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(318, 0, 8, 300);
		panel_1.add(verticalStrut_1);
		
		lblCurrentAmount = new JLabel("Current amount: ");
		lblCurrentAmount.setBounds(359, 125, 174, 24);
		panel_1.add(lblCurrentAmount);
		
		lblAmountNeeded = new JLabel("Order amount: ");
		lblAmountNeeded.setBounds(359, 160, 174, 28);
		panel_1.add(lblAmountNeeded);
		
		fuelAut = new JComboBox();
		fuelAut.setModel(new DefaultComboBoxModel(new String[] {"Benzine95", "Diesel" ,"Motors"}));
		fuelAut.setBounds(347, 93, 113, 24);
		panel_1.add(fuelAut);
		
		JLabel lblSelectFuelType = new JLabel("Select fuel type:");
		lblSelectFuelType.setBounds(371, 61, 175, 24);
		panel_1.add(lblSelectFuelType);
		
		btnAutOrder = new JButton("Approve");
		btnAutOrder.setBounds(380, 201, 194, 50);
		panel_1.add(btnAutOrder);
		
		lblAlreadyFull = new JLabel("Already full!");
		lblAlreadyFull.setBounds(428, 262, 89, 27);
		lblAlreadyFull.setForeground (Color.red);
		panel_1.add(lblAlreadyFull);
		lblAlreadyFull.setVisible(false);
		
		btnShow = new JButton("Show");
		btnShow.setBounds(485, 94, 89, 23);
		panel_1.add(btnShow);
		
		badAmountWarning = new JLabel("Bad amount value!");
		badAmountWarning.setBounds(50, 146, 189, 19);
		badAmountWarning.setForeground (Color.red);
		panel_1.add(badAmountWarning);
		badAmountWarning.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Produce quarter report", null, panel_2, null);
		panel_2.setLayout(null);
		
		btnProduce = new JButton("Produce");
		btnProduce.setBounds(424, 30, 159, 50);
		panel_2.add(btnProduce);
		
		CBReportType = new JComboBox();
		CBReportType.setModel(new DefaultComboBoxModel(new String[] {"Incomes", "Acquires"}));
		CBReportType.setBounds(170, 45, 117, 20);
		panel_2.add(CBReportType);
		
		Label lblSelectReportType = new Label("Select report type");
		lblSelectReportType.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSelectReportType.setAlignment(Label.CENTER);
		lblSelectReportType.setBounds(0, 45, 141, 19);
		panel_2.add(lblSelectReportType);
		
		textPane = new JTextPane();
		textPane.setBounds(40, 89, 339, 183);
		panel_2.add(textPane);
		
		JLabel lblSelectYear = new JLabel("Select year");
		lblSelectYear.setBounds(10, 11, 76, 20);
		panel_2.add(lblSelectYear);
		
		JLabel lblSelectQuarter = new JLabel("Select quarter");
		lblSelectQuarter.setBounds(218, 12, 83, 19);
		panel_2.add(lblSelectQuarter);
		
		yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016"}));
		yearBox.setBounds(96, 11, 83, 20);
		panel_2.add(yearBox);
		
		quarterBox = new JComboBox();
		quarterBox.setModel(new DefaultComboBoxModel(new String[] {"first", "second", "third", "fourth"}));
		quarterBox.setBounds(309, 11, 105, 20);
		panel_2.add(quarterBox);
		
		btnProduceInventory = new JButton("Produce Inventory");
		btnProduceInventory.setBounds(424, 91, 159, 23);
		panel_2.add(btnProduceInventory);
		}
		return tabbedPane;
}

	
	public void setTextPane(String str){
		textPane.setText("" + str);
	}
	
	public void setFuelAmountWarningInVisible() {
		this.badAmountWarning.setVisible(false);
	}
	
	public void setFuelAmountWarningVisible() {
		this.badAmountWarning.setVisible(true);

	}
	
	public void setLblCurrentAmount(int lblCurrentAmount) {
		this.lblCurrentAmount.setText("Current amount: " + lblCurrentAmount);
		int temp = 5000 - lblCurrentAmount;
		if (temp <= 0) {
			this.btnAutOrder.setVisible(false);
			this.lblAlreadyFull.setVisible(true);
			this.lblAmountNeeded.setText("Order amount: none");
		}
		else {
			this.btnAutOrder.setVisible(true);
			this.lblAlreadyFull.setVisible(false);
			this.lblAmountNeeded.setText("Order amount: " + temp);
		}
		this.lblCurrentAmount.setVisible(true);
	}
	
	public void setLblCurrentFuelLevel(int lblCurrentFuelLevel) {
		if (lblCurrentFuelLevel > 0)
			this.lblCurrentFuelLevel.setText("Current Fuel Level Minimum: " + lblCurrentFuelLevel);
	}
	
	public void setLblNoStationVisible() {
		this.lblNoStation.setVisible(true);
	}
	
	public void setLblNoStationInVisible() {
		this.lblNoStation.setVisible(false);
	}
	/**
	 * Warning messages Visible
	 */
	public void setWarningVisible(){
		lblWarning.setText("wrong value, please enter value > 0");
		lblWarning.setVisible(true);
	}
	
	public void setWarningInVisible(){
		lblWarning.setVisible(false);
	}
	
	/**
	 * Listener of changing tabs
	 */
	public class TabListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if (getTab().getSelectedIndex()==0)
				UserController.currTab  = 1;
			if (getTab().getSelectedIndex()==1)
				UserController.currTab  = 2;
			if (getTab().getSelectedIndex()==2)
				UserController.currTab  = 3;
			if (getTab().getSelectedIndex()==3)
				UserController.currTab  = 4;
			if (getTab().getSelectedIndex()==4)
				UserController.currTab  = 5;
		}
		
	}

	public void createTabActionListener(ChangeListener listener){
		tabbedPane.addChangeListener(listener);
	}
}
