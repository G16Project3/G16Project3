package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import gui.UserGUI.TabListener;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import controller.UserController;
import controller.WorkerController;
import entity.*;

import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

/**
 * this class is MarketingWorkerGUI that draws the Marketing Worker GUI 
 * @author Tal
 *
 */
public class MarketingWorkerGUI extends AbstractGUI {

	/**serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** tabs */
	private JTabbedPane Tab=null;
	
	/** tabs */
	private JTabbedPane tab =null;
	
	/** warning label*/
	private JLabel lblwarningMessage=null;
	
	/** insert new customer label*/
	private JLabel lblInsertNewCustomer1;
	
	/** insert weekly report label*/
	private JLabel lblInsertWeeklyReport;
	
	/** add new car label*/
	private JLabel lblAddNewCar;
	
	/** choosing payment*/
	public JComboBox comboBoxPayment;
	
	/** new customer panel*/
	private JPanel newCustomer;
	
	/** new weekly report panel*/
	private JPanel newWeeklyReport;
	
	/** add new car panel*/
	private JPanel addNewCar;
	
	/** new user panel*/
	private JPanel newUser;
	
	/** used to get customer id*/
	private JTextField CustomerIdText;
	
	/** used to get first name*/
	private JTextField firstNameText;
	
	/** used to get last name*/
	private JTextField LastNameText;
	
	/** used to get email*/
	private JTextField EmailText;
	
	/** phone label*/
	private JLabel lblPhone;
	
	/** type label*/
	private JLabel lblType;
	
	/** payment label*/
	private JLabel lblPayment;
	
	/** used to get address*/
	private JTextField AddressText;
	
	/** used to get phone number*/
	private JTextField PhoneText;
	
	/** fuel types array*/
	private String[] fuelType = {"","Diesel","Benzine95","HomeFuel","Motors"};
	
	/** customer type array*/
	private String[] customerType = {"","Refueling occur","Subscription one car","Subscription multiple car","Full subscription"};
	
	/** payment types array*/
	private String[] payment = {"","Cash","CreditCard","Both"};
	
	/** stores customer data*/
	private Customer tempCustomer=null;
	
	/** stores car data*/
	private Car tempCar = null;
	
	/** stores user data*/
	private User tempUser = null;
	
	/** flag for adding new customer*/
	public int newCustomerAdd = 0;
	
	/** flag for adding new car*/
	public int newCarAdd = 0;
	
	/** flag for adding creating weekly report*/
	public int creWeeklyReport = 0;
	
	/** flag for adding creating car fuel report*/
	public int creFuelReport = 0;
	
	/** flag for adding new user*/
	public int newUserAdd =0;
	
	/** flag for adding new week ranks for customer*/
	public int newSetWeekRate = 0;
	
	/** comboBoxFuelType string */
	public String comboBoxFuelType;
	
	/** comboBoxType1 string */
	public String comboBoxType1;
	
	/** comboBoxpayment1 string */
	public String comboBoxpayment1;
	
	/** used to get credit card*/
	private JTextField CreditCardText;
	
	/** credit card number label*/
	private JLabel lblCreditcardnumber;
	
	/**  label*/
	private JLabel lblNewLabel;
	
	/** incorrect input label*/
	private JLabel lblIncorectInput;
	
	/**  label*/
	private JLabel label;
	
	/** wrong phone warning label*/
	private JLabel worngPhone;
	
	/** wrong credit warning label*/
	private JLabel WorngCredit;
	
	/** customer id label*/
	private JLabel lblCustomerId;
	
	/** used to get customer id*/
	private JTextField txtCustomerid;
	
	/** license plate number label*/
	private JLabel lblLicenseplatenumber;
	
	/** used to get license plate number*/
	private JTextField licenseplatenumberText;
	
	/** car type label*/
	private JLabel lblCartype;
	
	/** used to get car type*/
	private JTextField CarTypeText;
	
	/** fuel type label*/
	private JLabel lblFueltype;
	
	/** choosing type*/
	private JComboBox comboBoxType;
	
	/** produce button*/
	public JButton btnproduce1;
	
	/** nfc checkbox*/
	private boolean NFC=false;
	
	/** table rows*/
	private Object[][] TableRows=null;
	
	/** table rows*/
	private Object [][] TableRows1 = null;
	
	/** fills table with data*/
	public DefaultTableModel VecToTable = new DefaultTableModel(TableRows,new String[] {"customer Id","Type","rank"});
	
	/** fills table with data*/
	public DefaultTableModel VecToTable1 = 	new DefaultTableModel(TableRows,new String[] {"Customer type","hour","Rank"});
	
	/** fills table with data*/
	public DefaultTableModel VecToTable2 = 	new DefaultTableModel(TableRows1,new String[] {"Fuel type","quantity","Rank"});
	
	
	
	/**
	 * Create the application.
	 */
	public MarketingWorkerGUI() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 780, 600);
		this.setTitle("MyFuel - Marketing Worker Window");
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getTab());
		this.setVisible(true);
		
		
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public JTabbedPane getTab() {
		if(Tab==null){
			Tab = new JTabbedPane();
			
			Tab.setBounds(0, 137, 765, 373);
			
			//Add new customer Tab
			newCustomer = new JPanel();
			newCustomer.setBorder(new LineBorder(new Color(0, 0, 0)));
			newCustomer.setLayout(null);
			    
		    Tab.addTab("Add New Customer", newCustomer);
		    Tab.addChangeListener(new TabListener());
		    newCustomerGui();
		    
		    addNewCar = new JPanel();
		    addNewCar.setBorder(new LineBorder(new Color(0, 0, 0)));
		    addNewCar.setLayout(null);
		    
		    Tab.addTab("add new car", addNewCar);
		    newCarGUI();
		    
		    newWeeklyReport = new JPanel();
		    newWeeklyReport.setBorder(new LineBorder(new Color(0, 0, 0)));
		    newWeeklyReport.setLayout(null);
		    
		    Tab.addTab("Create Weekly report", newWeeklyReport);
	
		    lblInsertWeeklyReport = new JLabel("Insert weekly report");
		    lblInsertWeeklyReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		    lblInsertWeeklyReport.setBounds(228, 0, 197, 54);
		    newWeeklyReport.add(lblInsertWeeklyReport);
		    newWeeklyReportGui();
		    JButton btnproduce = new JButton("produce Fuel quantity");
		    btnproduce.addActionListener(new creatFuelQuantity());
		    btnproduce.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnproduce.setBounds(558, 194, 175, 23);
		    newWeeklyReport.add(btnproduce);

		}
		return Tab;
	}
	
	/**
	 * create warning message 
	 */
		public JLabel getLblwarningMessage() 
		{
			if(lblwarningMessage == null){
				lblwarningMessage = new JLabel("");
				//Image ImgWarning = new ImageIcon(this.getClass().getResource("/Error.png")).getImage();
				//lblwarningMessage.setIcon(new ImageIcon(ImgWarning));
				lblwarningMessage.setFont(new Font("Arial", Font.BOLD, 12));
				lblwarningMessage.setForeground(Color.RED);
				lblwarningMessage.setVisible(false);
			}
			return lblwarningMessage;
		}
		
		/**
		 * set warning message with string	
		 */
		public void setWarningMessageVisibleTrue(String st) 
		{
			lblwarningMessage.setText(st);
			lblwarningMessage.setVisible(true);	
		}

		public void setWarningMessageVisibleFalse() 
		{
			lblwarningMessage.setVisible(false);	
		}
		
		public void setLblWelcome(String Userid)
		 {
			 this.setTitle("Hello "+Userid);
		 }
	
		/** tab listener*/
	public class TabListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			//userG.setWarningMessageVisibleFalse();
			if (getTab().getSelectedIndex()==0)
				WorkerController.currTab  = 1;
			if (getTab().getSelectedIndex()==1)
				WorkerController.currTab  = 2;
			if (getTab().getSelectedIndex()==2)
				WorkerController.currTab  = 3;
		}
		
	}
	
	/** tab listener*/
	public void createTabActionListener(ChangeListener listener){
		Tab.addChangeListener(listener);
	}
	
	/**
	 * add new customer listener when pressing add new customer button
	 */
	public class addNewCustomer implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			int id =Integer.parseInt(CustomerIdText.getText());
			tempCustomer.setCustomerId(id);
			tempCustomer.setCustomerFirstName(firstNameText.getText());
			tempCustomer.setCustomerLastName(LastNameText.getText());
			tempCustomer.setCustomerEmail(EmailText.getText());
			tempCustomer.setCustomerAddress(AddressText.getText());
			tempCustomer.setCustomerPhone(PhoneText.getText());
			tempCustomer.setMemberShipType(comboBoxType1);
			tempCustomer.setPayment(comboBoxpayment1);
			if(comboBoxpayment1.equals("CreditCard") || comboBoxpayment1.equals("Both"))
			tempCustomer.setCreditCardNumber(CreditCardText.getText());
			if(checkEmail(EmailText.getText()) && (checkName(firstNameText.getText()) &&checkName(LastNameText.getText()))
					&& (validatePhoneNumber(PhoneText.getText()))&& checkCreditCard(CreditCardText.getText())){
			newCustomerAdd = 1;
			String userName =JOptionPane.showInputDialog("set User name");
			String password =JOptionPane.showInputDialog("set password");		
			tempUser.setUserName(userName);
			tempUser.setUserPw(password);
			tempUser.setUserType("customer");
			tempUser.setUserId(tempCustomer.getCustomerId());
			tempUser.setUserLogStatus(0);
			tempUser.setRole("user");
			newUserAdd = 1;
			}
			else
				newCustomerAdd = 0;
		}
		 
	 }
	
	/** adds a new user GUI */
	public void addNewUserGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 780, 600);
		this.setTitle("MyFuel - Marketing Worker Window");
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getTab());
		this.setVisible(true);
		
		if(tab == null){
			tab = new JTabbedPane();
			Tab.setBounds(0, 137, 765, 373);
			
			//Add new customer Tab
			newUser = new JPanel();
			newUser.setBorder(new LineBorder(new Color(0, 0, 0)));
			newUser.setLayout(null);
			    
		    tab.addTab("Add New User", newUser);
		    tab.addChangeListener(new TabListener());
		    
		    JLabel lblAddNewUser = new JLabel("Insert new user");
		    lblAddNewUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		    lblAddNewUser.setBounds(228, 0, 197, 54);
		    newUser.add(lblAddNewUser);
			
		    JLabel lblUserName = new JLabel("Set user name");
		    lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblUserName.setBounds(68, 66, 88, 16);
			newUser.add(lblUserName);
			
			JTextField txtUserName = new JTextField();
			txtUserName.setBounds(168, 64, 132, 19);
			newUser.add(txtUserName);
			txtUserName.setColumns(10);
			
			JLabel lblSetpassword  = new JLabel("set Password");
			lblSetpassword.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSetpassword.setBounds(9, 96, 147, 16);
			newUser.add(lblSetpassword);
			JPasswordField passfield  = new JPasswordField();
			passfield.setBounds(173, 276, 130, 20);
			passfield.setColumns(10);
				
			JButton btnSend2 = new JButton("Send");
			btnSend2.addActionListener(new addNewCar());
			btnSend2.setBounds(589, 287, 98, 39);
			newUser.add(btnSend2);
		}
		
	}
	
	/** adds a new car*/
	public class addNewCar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(txtCustomerid.getText());
			tempCar.setownerid(id);
			tempCar.setlicenseplatenumber(licenseplatenumberText.getText());
			tempCar.setCarType(CarTypeText.getText());
			tempCar.setFuelType(comboBoxFuelType);
			if(NFC)
				tempCar.setCarNFC("YES");
			else
				tempCar.setCarNFC("NO");
			newCarAdd =1;
			
		}
	}
	
	/** raises create weekly report flag when weekly report button pressed*/
	public class creatWeeklyReport implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			creWeeklyReport = 1;	
		}
		
	}
	/** raises car fuel report flag when car fuel quantity button pressed*/
	public class creatFuelQuantity implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			creFuelReport = 1;	
		}
	}
	/*************check fields*************/
	
	/** checks if e-mail is valid */
	public Boolean checkEmail(String email){
	     String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     if(email.matches(EMAIL_REGEX)){
	    	 lblNewLabel.setVisible(false);
	    	 return true;
	     }
	     else{
				lblNewLabel.setVisible(true);
			     return false;
	     }

	}
	
	/** checks if phone number is valid */
	public Boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
        	worngPhone.setVisible(false);
        	return true;
        }
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")){
        	worngPhone.setVisible(false);
        	return true;
        }
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")){
        	worngPhone.setVisible(false);
        	return true;
        }
        //return false if nothing matches the input
        else{
        	worngPhone.setVisible(true);
        	return false;
        }
         
    }
	
	/** checks if name is valid*/
	public Boolean checkName(String name){
		//check if it is the first name than check if it contain only alphbet
		if(firstNameText.getText().equals(name)){
		        for (int i = 0; i < name.length(); i++){
		            char c = name.charAt(i);
		            //Only the following characters are allowed { [a-z].[A-Z]}
		            //Check for character ','
		            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') );
		            else {
		            	label.setVisible(true);
		            	return false;
		            }
		        }
		        label.setVisible(false);
		        return true;
		}//check if it is the last name than check if it contain only alphbet
		if(LastNameText.getText().equals(name)){
	        for (int i = 0; i < name.length(); i++){
	            char c = name.charAt(i);
	            //Only the following characters are allowed { [a-z].[A-Z]}
	            //Check for character ','
	            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') );
	            else {
	            	lblIncorectInput.setVisible(true);
	            	return false;
	            }
	        }
			lblIncorectInput.setVisible(false);
	        return true;
		}
		return false;
	}
	
	/** check if credit card is valid*/
	public Boolean checkCreditCard(String credit){
		if(comboBoxpayment1.equals("CreditCard") || comboBoxpayment1.equals("Both"))
		if (credit.matches("[0-9]+") && credit.length() >= 6){
			WorngCredit.setVisible(false);
			return true;
		}else{
			WorngCredit.setVisible(true);
			return false;
		}
		else return true;
	}
	/*************Getters and Setters *************/
	public User getTempUser() {
		return tempUser;
	}
	public void setTempUser(User tempUser) {
		this.tempUser = tempUser;
	}
	public Car getTempCar() {
		return tempCar;
	}
	public void setTempCar(Car tempCar) {
		this.tempCar = tempCar;
	}
	public Customer getTempCustomer() {
		return tempCustomer;
	}
	public void setTempCustomer(Customer tempCustomer) {
		this.tempCustomer = tempCustomer;
	}
	public Object[][] getTableRows() {
	return TableRows;
}

	public void setTableRows(Object[][] tableRows) {
	this.TableRows = tableRows;
}
	public Object[][] getTableRows1() {
		return TableRows1;
	}
	public void setTableRows1(Object[][] tableRows1) {
		this.TableRows1 = tableRows1;
	}

	/*************Additional GUI to complete tasks*************/
	
	/** adds new customer GUI */
	public void newCustomerGui(){
		lblInsertNewCustomer1 = new JLabel("Insert new customer");
	    lblInsertNewCustomer1.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblInsertNewCustomer1.setBounds(228, 0, 197, 54);
	    newCustomer.add(lblInsertNewCustomer1);
	    
	    JLabel lblCustomerid = new JLabel("CustomerId");
	    lblCustomerid.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblCustomerid.setBounds(50, 79, 88, 21);
	    newCustomer.add(lblCustomerid);
	    
	    CustomerIdText = new JTextField();
	    CustomerIdText.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyTyped(KeyEvent evt) {
	    		char vChar = evt.getKeyChar();
	    		if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) { evt.consume(); }
	    	}
	    });
	    CustomerIdText.setBounds(148, 80, 210, 20);
	    CustomerIdText.setColumns(10);
	    newCustomer.add(CustomerIdText);
	    
	    JLabel lblCustomerfirstname = new JLabel("CustomerFirstName");
	    lblCustomerfirstname.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblCustomerfirstname.setBounds(10, 111, 137, 21);
	    newCustomer.add(lblCustomerfirstname);
	    
	    JLabel lblCustomerlastname = new JLabel("CustomerLastName");
	    lblCustomerlastname.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblCustomerlastname.setBounds(10, 143, 128, 21);
	    newCustomer.add(lblCustomerlastname);
	    
	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblEmail.setBounds(93, 175, 45, 21);
	    newCustomer.add(lblEmail);
	    
	    firstNameText = new JTextField();
	    firstNameText.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String expression = "^[a-zA-Z]*$";
	            String inputStr = firstNameText.getText();
	            if(inputStr.matches("[a-zA-Z]")){
	            	
	            }
	            else
	    		lblwarningMessage = new JLabel("");
				lblwarningMessage.setText("incorect First name");
				lblwarningMessage.setVisible(true);	
				lblwarningMessage.setFont(new Font("Arial", Font.BOLD, 12));
				lblwarningMessage.setForeground(Color.RED);
	    	}
	    });
	    firstNameText.setColumns(10);
	    firstNameText.setBounds(148, 112, 210, 20);
	    newCustomer.add(firstNameText);
	    
	    LastNameText = new JTextField();
	    LastNameText.setColumns(10);
	    LastNameText.setBounds(148, 143, 210, 20);
	    newCustomer.add(LastNameText);
	    
	    EmailText = new JTextField();
	    EmailText.setColumns(10);
	    EmailText.setBounds(148, 176, 210, 20);
	    newCustomer.add(EmailText);
	    
	    JLabel lblAddress = new JLabel("Address");
	    lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblAddress.setBounds(78, 207, 88, 21);
	    newCustomer.add(lblAddress);
	    
	    lblPhone = new JLabel("Phone");
	    lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblPhone.setBounds(93, 237, 88, 21);
	    newCustomer.add(lblPhone);
	    
	    lblType = new JLabel("Type");
	    lblType.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblType.setBounds(103, 269, 88, 21);
	    newCustomer.add(lblType);
	    
	    lblPayment = new JLabel("Payment");
	    lblPayment.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblPayment.setBounds(78, 303, 88, 21);
	    newCustomer.add(lblPayment);
	    
	    AddressText = new JTextField();
	    AddressText.setColumns(10);
	    AddressText.setBounds(148, 208, 210, 20);
	    newCustomer.add(AddressText);
	    
	    PhoneText = new JTextField();
	    PhoneText.setColumns(10);
	    PhoneText.setBounds(148, 239, 210, 20);
	    newCustomer.add(PhoneText);
	    
	    
		comboBoxType = new JComboBox(customerType);
		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxType1 = comboBoxType.getSelectedItem().toString();
			}
		});
	    comboBoxType.setBounds(148, 269, 210, 20);
	    newCustomer.add(comboBoxType);
	    
	    comboBoxPayment = new JComboBox(payment);
	    comboBoxPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxpayment1 = comboBoxPayment.getSelectedItem().toString();
				if(comboBoxpayment1=="CreditCard" || comboBoxpayment1=="Both"){
					lblCreditcardnumber.setVisible(true);
					CreditCardText.setVisible(true);
				}
				if(comboBoxpayment1.equals("")||comboBoxpayment1.equals("Cash")){
					lblCreditcardnumber.setVisible(false);
					CreditCardText.setVisible(false);
				}
				
			}
		});
	    comboBoxPayment.setBounds(148, 303, 210, 20);
	    newCustomer.add(comboBoxPayment);
	    
	    JButton btnSend = new JButton("Send");
	    btnSend.addActionListener(new addNewCustomer());
	    btnSend.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnSend.setBounds(589, 287, 98, 39);
	    newCustomer.add(btnSend);
	    
	    CreditCardText = new JTextField();
	    CreditCardText.setBounds(390, 287, 155, 18);
	    newCustomer.add(CreditCardText);
	    CreditCardText.setColumns(10);
	    CreditCardText.setVisible(false);
	    
	    lblCreditcardnumber = new JLabel("credit Card Number");
	    lblCreditcardnumber.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblCreditcardnumber.setBounds(380, 269, 155, 21);
	    newCustomer.add(lblCreditcardnumber);
	    
	    lblNewLabel = new JLabel("worng email");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblNewLabel.setForeground(Color.RED);
	    lblNewLabel.setBounds(370, 175, 115, 20);
	    newCustomer.add(lblNewLabel);
	    
	    lblIncorectInput = new JLabel("incorect input");
	    lblIncorectInput.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblIncorectInput.setForeground(Color.RED);
	    lblIncorectInput.setBounds(370, 145, 114, 16);
	    newCustomer.add(lblIncorectInput);
	    lblIncorectInput.setVisible(false);
	    label = new JLabel("incorect input");
	    label.setForeground(Color.RED);
	    label.setFont(new Font("Tahoma", Font.BOLD, 13));
	    label.setBounds(370, 113, 114, 16);
	    newCustomer.add(label);
	    
	    worngPhone = new JLabel("worng phone ");
	    worngPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
	    worngPhone.setForeground(Color.RED);
	    worngPhone.setBounds(370, 239, 115, 16);
	    newCustomer.add(worngPhone);
	    
	    WorngCredit = new JLabel("Worng credit card number");
	    WorngCredit.setForeground(Color.RED);
	    WorngCredit.setFont(new Font("Tahoma", Font.BOLD, 13));
	    WorngCredit.setBounds(392, 305, 187, 16);
	    newCustomer.add(WorngCredit);
	    WorngCredit.setVisible(false);
	    worngPhone.setVisible(false);
	    label.setVisible(false);
	    lblNewLabel.setVisible(false);
	    lblCreditcardnumber.setVisible(false);
	}

	/** adds new car GUI */
	public void newCarGUI(){
		lblAddNewCar = new JLabel("Insert new car");
		lblAddNewCar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddNewCar.setBounds(228, 0, 197, 54);
		addNewCar.add(lblAddNewCar);
		
		lblCustomerId = new JLabel("Customer Id");
		lblCustomerId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerId.setBounds(68, 66, 88, 16);
		addNewCar.add(lblCustomerId);
		
		txtCustomerid = new JTextField();
		txtCustomerid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) { e.consume(); }
					
			}
		});
		txtCustomerid.setBounds(168, 64, 132, 19);
		addNewCar.add(txtCustomerid);
		txtCustomerid.setColumns(10);
		
		lblLicenseplatenumber = new JLabel("license plate number");
		lblLicenseplatenumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLicenseplatenumber.setBounds(9, 96, 147, 16);
		addNewCar.add(lblLicenseplatenumber);
		
		licenseplatenumberText = new JTextField();
		licenseplatenumberText.setColumns(10);
		licenseplatenumberText.setBounds(168, 96, 132, 19);
		addNewCar.add(licenseplatenumberText);
		
		lblCartype = new JLabel("Car Type");
		lblCartype.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCartype.setBounds(68, 125, 88, 16);
		addNewCar.add(lblCartype);
		
		CarTypeText = new JTextField();
		CarTypeText.setColumns(10);
		CarTypeText.setBounds(168, 123, 132, 19);
		addNewCar.add(CarTypeText);
		
		lblFueltype = new JLabel("Fuel Type");
		lblFueltype.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFueltype.setBounds(68, 154, 88, 16);
		addNewCar.add(lblFueltype);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new addNewCar());
		btnSend.setBounds(589, 287, 98, 39);
		addNewCar.add(btnSend);
		
		comboBoxType = new JComboBox(fuelType);
		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxFuelType = comboBoxType.getSelectedItem().toString();
			}
		});
		comboBoxType.setBounds(168, 151, 132, 22);
		addNewCar.add(comboBoxType);
		
		JCheckBox chckbxNfc = new JCheckBox("NFC");
		chckbxNfc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NFC = true;
			}
		});
		chckbxNfc.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNfc.setBounds(68, 191, 97, 23);
		addNewCar.add(chckbxNfc);
	}
	
	/** adds new weekly report GUI */
	public void newWeeklyReportGui(){
     JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(10, 42, 220, 150);
    newWeeklyReport.add(scrollPane_1);
    JTable table = new JTable();
    scrollPane_1.setViewportView(table);
    table.setBorder(UIManager.getBorder("Button.border"));
    table.setFillsViewportHeight(true);
    table.setModel(VecToTable);
	scrollPane_1.setViewportView(table);
	
	 JScrollPane scrollPane = new JScrollPane();
	 scrollPane.setBounds(255, 42, 250, 150);
	 newWeeklyReport.add(scrollPane);
	 JTable table1 = new JTable();
	 scrollPane.setViewportView(table1);
	 table1.setBorder(UIManager.getBorder("Button.border"));
	 table1.setFillsViewportHeight(true);
	 table1.setModel(VecToTable1);
	 scrollPane.setViewportView(table1);
		
	 JScrollPane scrollPane_2 = new JScrollPane();
	 scrollPane_2.setBounds(530, 42, 220, 150);
	 newWeeklyReport.add(scrollPane_2);
	 JTable table2 = new JTable();
	 scrollPane_2.setViewportView(table2);
	 table2.setBorder(UIManager.getBorder("Button.border"));
	 table2.setFillsViewportHeight(true);
	 table2.setModel(VecToTable2);
	 scrollPane_2.setViewportView(table2);
	 
	 btnproduce1 = new JButton("produce type rank");
	 btnproduce1.addActionListener(new creatWeeklyReport());
	 btnproduce1.setFont(new Font("Tahoma", Font.BOLD, 11));
	 btnproduce1.setBounds(20, 194, 189, 23);
	 newWeeklyReport.add(btnproduce1);
	 
	 
	    
	    JButton btnsetNewRate = new JButton("Set new week rates");
	    btnsetNewRate.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnsetNewRate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		newSetWeekRate = 1;
	    	}
	    });
	    btnsetNewRate.setBounds(61, 284, 180, 36);
	    newWeeklyReport.add(btnsetNewRate);
    
	}
}


