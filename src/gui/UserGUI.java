package gui;

import controller.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.sql.Date;

import entity.*;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import entity.*;
import gui.*;

import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;

import java.awt.event.MouseEvent;
import java.awt.List;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

import java.awt.SystemColor;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import com.toedter.components.JLocaleChooser;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;

/**
 * this class is GUI of user
 * @author G16
 *
 */
public class UserGUI extends AbstractGUI {

	/** tabs */
	private JTabbedPane Tab=null;

	/** car order panel*/
	private JPanel carOrder;

	/** home order panel*/
	private JPanel homeOrder;
	
	/**  panel*/
	private JPanel panel;
	
	/** insert order details label*/
	private JLabel lblInsertOrderDetails_1;
	
	/** car label*/
	private JLabel lblCar;
	
	/** used to get license plate number*/
	private JTextField licensePlateNumberText; 
	
	/** used to get quantity*/
	private JTextField QuantityTextCar;
	
	/** used to get fuel station id*/
	private JTextField FuelStationIdCar;
	
	/** used to get quantity for home fuel*/
	private JTextField QuantitiyHomeText;
	
	/** used to get address to supply*/
	private JTextField AddressToSupplyHometext;
	
	/** used to get date*/
	private JTextField SupplyDateHomeText;
	
	/** used to get time*/
	private JTextField SupplyTimeHomeText;
	
	/** scroll pane*/
	public JScrollPane scrollPane;
	
	/** table of orders*/
	public JTable TableOfOrders;
	
	/** flag for car fuel order button clicked*/
	public  int carAcceptClick = 0;
	
	/** flag for home fuel order button clicked*/
	public  int homeAcceptClick = 0;
	
	/** flag for tab 3 clicked*/
	public  int Tab3Ckick = 0;
	
	/** temporary car fuel order filled with data*/
	private CarFuelOrder tempOrder=null;
	
	/** temporary home fuel order filled with data*/
	private OrderHomeFuel tempHomeOrder=null;
	
	/** urgent flag*/
	private boolean urgentHomeFuelOrder=false;
	
	/** used as table*/
	private Object[][] TableRows=null;
	
	/** used to fill the table*/
	public DefaultTableModel VecToTable = 
			new DefaultTableModel(TableRows,new String[] {"order Id", 
					"invited by id", "quantity", "supply address", 
					"supply date", "supply time", "order type"});
	
	/** stores fuel type*/
	private String fuelType;
	
	
	/** UserGUI constructor 
	 * @author G16
	 */
	public UserGUI() {
		super();
		//Logout button // 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 780, 600);
		this.setTitle("MyFuel - Customer Window");
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
		    Tab.addChangeListener(new TabListener());
			
			//Car fuel order Tab
			carOrder = new JPanel();
			carOrder.setLayout(null);
			
			Tab.addTab("Car fuel order", carOrder);
			JLabel lblInsertOrderDetails = new JLabel("");
			lblInsertOrderDetails.setBounds(10, 11, 2, 30);
			carOrder.add(lblInsertOrderDetails);
			
			lblInsertOrderDetails_1 = new JLabel("Order details");
			lblInsertOrderDetails_1.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblInsertOrderDetails_1.setBounds(298, 0, 127, 50);
			carOrder.add(lblInsertOrderDetails_1);
			
			lblCar = new JLabel("Car number");
			lblCar.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCar.setBounds(177, 51, 147, 30);
			carOrder.add(lblCar);
			
					    JLabel lblFuelType = new JLabel("Fuel type");
					    lblFuelType.setFont(new Font("Tahoma", Font.BOLD, 13));
					    lblFuelType.setBounds(177, 134, 147, 30);
					    carOrder.add(lblFuelType);
					    
					    licensePlateNumberText = new JTextField();
					    licensePlateNumberText.setColumns(10);
					    licensePlateNumberText.setBounds(334, 57, 220, 20);
					    carOrder.add(licensePlateNumberText);
					    
					    JLabel lblQ = new JLabel("Quantity");
					    lblQ.setFont(new Font("Tahoma", Font.BOLD, 13));
					    lblQ.setBounds(177, 88, 147, 30);
					    carOrder.add(lblQ);
					    
					    QuantityTextCar = new JTextField();
					    QuantityTextCar.setColumns(10);
					    QuantityTextCar.setBounds(334, 94, 220, 20);
					    carOrder.add(QuantityTextCar);
					    
					    JLabel lblFuelStationId = new JLabel("Fuel Station Id");
					    lblFuelStationId.setFont(new Font("Tahoma", Font.BOLD, 13));
					    lblFuelStationId.setBounds(177, 177, 147, 30);
					    carOrder.add(lblFuelStationId);
					    
					    		    
					    		    FuelStationIdCar = new JTextField();
					    		    FuelStationIdCar.setColumns(10);
					    		    FuelStationIdCar.setBounds(335, 187, 220, 20);
					    		    carOrder.add(FuelStationIdCar);
					    		    
			JButton AcceptButtonCar = new JButton("Accept");
			AcceptButtonCar.addActionListener(new addNewCarOrder());
			AcceptButtonCar.setBounds(609, 283, 112, 38);
			carOrder.add(AcceptButtonCar);
			
			final JComboBox comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fuelType=(String) comboBox.getSelectedItem();
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"","Diesel","Benzine95","Motors"}));
			comboBox.setSelectedIndex(0);
			fuelType=  (String) comboBox.getSelectedItem();
			comboBox.setBounds(334, 140, 220, 20);
			carOrder.add(comboBox);

		    //Home fuel order Tab
			homeOrder = new JPanel();
			homeOrder.setLayout(null);
			Tab.addTab("Home fuel order", homeOrder);
			
			// -----------------------------------------labels Home fuel order start --------------------------------//
			
			JLabel label = new JLabel("Order details");
			label.setFont(new Font("Tahoma", Font.BOLD, 18));
			label.setBounds(286, 38, 127, 50);
			homeOrder.add(label);
			
			JLabel label_2 = new JLabel("Quantity");
			label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			label_2.setBounds(121, 102, 147, 30);
			homeOrder.add(label_2);
			
			JLabel lblAddressToSupply = new JLabel("Address to supply");
			lblAddressToSupply.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAddressToSupply.setBounds(121, 143, 147, 30);
			homeOrder.add(lblAddressToSupply);
			
			JLabel lblSupplyDate = new JLabel("Supply date");
			lblSupplyDate.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSupplyDate.setBounds(121, 184, 147, 30);
			
			homeOrder.add(lblSupplyDate);
			
			JLabel lblSupplyTime = new JLabel("Supply time");
			lblSupplyTime.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSupplyTime.setBounds(121, 225, 147, 30);
			homeOrder.add(lblSupplyTime);
			
			QuantitiyHomeText = new JTextField();
			QuantitiyHomeText.setColumns(10);
			QuantitiyHomeText.setBounds(322, 113, 220, 20);
			homeOrder.add(QuantitiyHomeText);
			
			AddressToSupplyHometext = new JTextField();
			AddressToSupplyHometext.setColumns(10);
			AddressToSupplyHometext.setBounds(322, 150, 220, 20);
			homeOrder.add(AddressToSupplyHometext);
			
			SupplyDateHomeText = new JTextField();
			SupplyDateHomeText.setColumns(10);
			SupplyDateHomeText.setBounds(322, 191, 220, 20);
			homeOrder.add(SupplyDateHomeText);
			
			SupplyTimeHomeText = new JTextField();
			SupplyTimeHomeText.setColumns(10);
			SupplyTimeHomeText.setBounds(322, 232, 220, 20);
			homeOrder.add(SupplyTimeHomeText);
			
			JButton AcceptButtonHome = new JButton("Accept");
			AcceptButtonHome.addActionListener(new addNewHomeOrder());
			AcceptButtonHome.setBounds(594, 283, 112, 38);
			homeOrder.add(AcceptButtonHome);
			
		    panel = new JPanel();
		    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		    Tab.addTab("Track Orders", panel);
		    panel.setLayout(null);
		    
		    scrollPane = new JScrollPane();
		    scrollPane.setBounds(0, 0, 758, 342);
		    panel.add(scrollPane);
		    
		    TableOfOrders = new JTable();
		    
			TableOfOrders.setBorder(UIManager.getBorder("Button.border"));
			TableOfOrders.setFillsViewportHeight(true);
			TableOfOrders.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"order Id", "invited by id", "quantity", "supply address", "supply date", "supply time", "order type"
				}
			));
			scrollPane.setViewportView(TableOfOrders);
	
		}
		return Tab;
	}
		
		/*************Change title*************/
		public void setLblWelcome(String UserName)
		{
		 this.setTitle("Hello "+UserName);
		}

		/*************add new car listener*************/
		public class addNewCarOrder implements ActionListener
	 {
		public void actionPerformed(ActionEvent e) {
			
			tempOrder.setLicensePlateNumber(licensePlateNumberText.getText());
			tempOrder.setFuel_type(fuelType);
			tempOrder.setQuantity(Integer.parseInt(QuantityTextCar.getText()));
			tempOrder.setFuel_station_id(Integer.parseInt(FuelStationIdCar.getText()));
			carAcceptClick = 1;
		}
		 
	 }
	 
		/** add new home listener */
		public class addNewHomeOrder implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				tempHomeOrder.setAddressToSupply(AddressToSupplyHometext.getText());
				tempHomeOrder.setSupplyTime(SupplyTimeHomeText.getText());
				tempHomeOrder.setSupplyDate(SupplyDateHomeText.getText());
				tempHomeOrder.setQuantity(Integer.parseInt(QuantitiyHomeText.getText()));
			
				if (tempHomeOrder.getQuantity()>600 && tempHomeOrder.getQuantity()<800)
					tempHomeOrder.setOrderType("600-800");	
				else if (tempHomeOrder.getQuantity()>800) tempHomeOrder.setOrderType("800+");
				else if (tempHomeOrder.getQuantity()<600) tempHomeOrder.setOrderType("Under 600");
				else if (urgentHomeFuelOrder)	{
				tempHomeOrder.setOrderType("Urgent");
				}
				
				homeAcceptClick=1;
			}
			
		}

		/** tab listener - used to change tabs */
		public class TabListener implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				if (getTab().getSelectedIndex()==0)
					UserController.currTab  = 1;
				if (getTab().getSelectedIndex()==1)
					UserController.currTab  = 2;
				if (getTab().getSelectedIndex()==2)
				{
					UserController.currTab = 3;
					Tab3Ckick=1;
				}
			}
			
		}

		/*************Getters and Setters *************/
		
		public Object[][] getTableRows() {
		return TableRows;
	}

		public void setTableRows(Object[][] tableRows) {
		TableRows = tableRows;
	}
		
		public OrderHomeFuel getTempHomeOrder() {
			return tempHomeOrder;
		}

		public void setTempHomeOrder(OrderHomeFuel tempHomeOrder) {
			this.tempHomeOrder = tempHomeOrder;
		}
		
		public CarFuelOrder getTempOrder() {
			return tempOrder;
		}

		public void setTempOrder(CarFuelOrder tempOrder) {
			this.tempOrder = tempOrder;
		}
	
}



