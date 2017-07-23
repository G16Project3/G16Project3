package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import com.toedter.calendar.JDateChooser;

import controller.UserController;
import controller.WorkerController;
import entity.Campaign;
import entity.Rates;
import gui.UserGUI.TabListener;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JTable;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
/**
 * this class is MarketingManagerGui that drawing the MarketingManager Gui
 * @author G16
 *
 */
public class MarketingManager extends WorkerGUI {

	/**table for fuel rates */
	private Object[][] TableRowsfuelrates=null;
	
	/**  table on tab no. 2 */
	public DefaultTableModel VecToTable = 
			new DefaultTableModel(TableRowsfuelrates,new String[] {"Fuel type", 
					"Current price", "Price requested", "Status"});
	
	/** list of items */ 
	private Object[][] ListItems=null;
	
	/** upper table on tab no. 4 */
	public DefaultTableModel VecToTable2 = 
			new DefaultTableModel(ListItems,new String[] {"Sale Id", "Campaig Name",
					"Discount Percent", "Start Day", "End Day", "Start Hour", "End Hour",
					"Campaign Status"});
	
	/** sale reaction report*/
	private Object[][] SaleReactionReportProduce=null;
	
	/** lower table on tab no. 4 */
	public DefaultTableModel VecToTable3 =
			new DefaultTableModel(SaleReactionReportProduce,new String[] {"Sale name", "Customer amount", "TBD"});
	
	/** customer report */
	private Object[][] CustomerReport=null;
	
	/** table on tab no. 5 */
	public DefaultTableModel VecToTable4 = 
			new DefaultTableModel(CustomerReport,new String[] {"Customer Id", "Rank", "Station ID"});
	
	/** used for getting campaign name*/
	private JTextField txtlblCampaigName;
	
	/** used for getting discount*/
	private JTextField txtDiscount;
	
	/** temporary sale */
	private Campaign tempNewSale= new Campaign();
	
	/** tabs */
	private JTabbedPane Tab=null;
	
	/** new sale panel */
	private JPanel newSale;
	
	/** stores date */
	private java.util.Date datef=null;
	
	/** stores date */
	private java.util.Date dated=null;
	
	/** stores date */
	private java.util.Date Start_hour=null;
	
	/** stores date */
	private java.util.Date End_hour=null;
	
	/** new sale flag */
	public int newSaleAdd = 0;
	
	/** used for getting motorcycle fuel update*/
	private JTextField MotorcycleFuelUpdate;
	
	/** used for getting benzine fuel update*/
	private JTextField Benzine95Update;
	
	/** used for getting diesel fuel update*/
	private JTextField DieselUpdate;
	
	/** used for getting home fuel update*/
	private JTextField HomeFuelUpdate;
	
	/** temporary rates update */
	private Rates tempRatesUpdate = new Rates();
	
	/** update rates request flag */
	public int updateRatesSendClick = 0;
	
	/** check rates request flag */
	public int CheckRatesRequestclick=0;
	
	/** table of data */
	private JTable table;
	
	/** sale reaction flag */
	public int SaleReactionClick=0;
	
	/** table of data */
	private JTable table_1;
	
	/** table of data */
	private JTable table_2;
	
	/** used for getting sale id*/
	private JTextField SaleIDText;
	
	/** stores sale id*/
	public int SaleId;
	
	/** table of data */
	private JTable table_3;
	
	/** customer report flag */
	public int CustomerReportclick=0;

	/**
	 * Marketing Manager GUI constructor
	 * @author Arthur
	 */
	public MarketingManager() {
		//initialize();
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 780, 600);
		this.setTitle("MyFuel - Marketing Manager Window");
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
			Tab = new JTabbedPane(JTabbedPane.TOP);

		Tab.setBounds(10, 150, 708, 353);
		Tab.addChangeListener(new TabListener());

		    
			//Add new customer Tab
		newSale = new JPanel();
		newSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		newSale.setLayout(null);
	
		Tab.addTab("Initiate Sale", null, newSale, null);
		newSale.setLayout(null);
		
		JButton btnInitate = new JButton("initate ");
		
		btnInitate.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format5 = new SimpleDateFormat("hh:mm:ss");
				tempNewSale.setcampaigName(txtlblCampaigName.getText());
				tempNewSale.setdiscountPercent(Integer.parseInt(txtDiscount.getText()));
				tempNewSale.setstartDay(datef);
				tempNewSale.setendDay(dated);
				tempNewSale.setstartHour(Start_hour);
				tempNewSale.setendHour(End_hour);
				newSaleAdd = 1;
			}
		});

		btnInitate.setBounds(417, 240, 153, 52);
		newSale.add(btnInitate);
		
		Label lblCampaigName = new Label("Campaign name");
		lblCampaigName.setBounds(10, 26, 91, 19);
		newSale.add(lblCampaigName);
		
		txtlblCampaigName = new JTextField();
		txtlblCampaigName.setBounds(132, 26, 140, 20);
		newSale.add(txtlblCampaigName);
		txtlblCampaigName.setColumns(10);
		
		Label lblDiscount = new Label("Discount percent");
		lblDiscount.setBounds(10, 52, 104, 19);
		newSale.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(132, 51, 140, 20);
		newSale.add(txtDiscount);
		
		Label lblStartDay = new Label("Start day");
		lblStartDay.setBounds(10, 76, 61, 19);
		newSale.add(lblStartDay);
		
		Label lblEndDay = new Label("End day");
		lblEndDay.setBounds(10, 103, 61, 19);
		newSale.add(lblEndDay);
		
		Label lblStartHour = new Label("Start hour");
		lblStartHour.setBounds(10, 128, 61, 19);
		newSale.add(lblStartHour);
		
		Label lblEndHour = new Label("End hour");
		lblEndHour.setBounds(10, 153, 61, 19);
		newSale.add(lblEndHour);
		
		final JComboBox CBEndHour = new JComboBox();
		CBEndHour.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		CBEndHour.setBounds(132, 152, 68, 20);
		SimpleDateFormat format7 = new SimpleDateFormat("hh:mm");
		CBEndHour.setSelectedIndex(0);
		try {
			End_hour = format7.parse(CBEndHour.getSelectedItem().toString());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		CBEndHour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				SimpleDateFormat format4 = new SimpleDateFormat("hh:mm");
				try {
					End_hour = format4.parse(CBEndHour.getSelectedItem().toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		newSale.add(CBEndHour);
		
		final JComboBox CBStartHour = new JComboBox();
		CBStartHour.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		CBStartHour.setBounds(132, 127, 68, 20);
		SimpleDateFormat format6 = new SimpleDateFormat("hh:mm");
		CBStartHour.setSelectedIndex(0);
		try {
			Start_hour = format6.parse(CBStartHour.getSelectedItem().toString());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		CBStartHour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				SimpleDateFormat format3 = new SimpleDateFormat("hh:mm");
				try {
				Start_hour = format3.parse(CBStartHour.getSelectedItem().toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		newSale.add(CBStartHour);
		
		final JDateChooser dateChooserStartDay = new JDateChooser();
		dateChooserStartDay.setDateFormatString("dd/MM/yyyy");
		dateChooserStartDay.setBounds(132, 76, 95, 22);
		dateChooserStartDay.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {	
				datef = dateChooserStartDay.getDate();
			}
		});
		newSale.add(dateChooserStartDay);
		
		final JDateChooser dateChooserEndDay = new JDateChooser();
		dateChooserEndDay.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dated = dateChooserEndDay.getDate();
			}
		});
		dateChooserEndDay.setBounds(132, 100, 95, 22);
		dateChooserEndDay.setDateFormatString("dd/MM/yyyy");
	
		newSale.add(dateChooserEndDay);
		
		JPanel CheckStatusButton = new JPanel();
		Tab.addTab("Check rates request status", null, CheckStatusButton, null);
		
		JButton btnCheckStatus = new JButton("Check status");
		btnCheckStatus.setBounds(182, 250, 200, 50);
		btnCheckStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckRatesRequestclick=1;
			}
		});
		CheckStatusButton.setLayout(null);
		CheckStatusButton.add(btnCheckStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 683, 91);
		CheckStatusButton.add(scrollPane);
		
		table = new JTable();
		table.setModel(VecToTable);
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		Tab.addTab("Send rates update request", null, panel_4, null);
		panel_4.setLayout(null);
		
		JButton btnSend = new JButton("SEND"); // update rates //
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(Benzine95Update.getText().isEmpty()))
					tempRatesUpdate.setBenzine95_update(Float.valueOf(Benzine95Update.getText()));
				if (!(DieselUpdate.getText().isEmpty()))
				tempRatesUpdate.setDiesel_update(Float.valueOf(DieselUpdate.getText()));
				if (!(HomeFuelUpdate.getText().isEmpty()))
				tempRatesUpdate.setHome_fuel_update(Float.valueOf(HomeFuelUpdate.getText()));
				if (!(MotorcycleFuelUpdate.getText().isEmpty()))
				tempRatesUpdate.setMotorcycle_fuel_update(Float.valueOf(MotorcycleFuelUpdate.getText()));
				updateRatesSendClick=1;
			}
		});
		btnSend.setBounds(370, 242, 200, 50);
		panel_4.add(btnSend);
		
		MotorcycleFuelUpdate = new JTextField();
		MotorcycleFuelUpdate.setBounds(289, 74, 155, 28);
		panel_4.add(MotorcycleFuelUpdate);
		MotorcycleFuelUpdate.setColumns(10);
		
		Benzine95Update = new JTextField();
		Benzine95Update.setColumns(10);
		Benzine95Update.setBounds(289, 113, 155, 28);
		panel_4.add(Benzine95Update);
		
		DieselUpdate = new JTextField();
		DieselUpdate.setColumns(10);
		DieselUpdate.setBounds(289, 152, 155, 28);
		panel_4.add(DieselUpdate);
		
		HomeFuelUpdate = new JTextField();
		HomeFuelUpdate.setColumns(10);
		HomeFuelUpdate.setBounds(289, 191, 155, 28);
		panel_4.add(HomeFuelUpdate);
		
		JLabel lblNewLabel = new JLabel("Motorcycle Fuel");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(108, 72, 134, 28);
		panel_4.add(lblNewLabel);
		
		JLabel lblBenzine = new JLabel("Benzine 95");
		lblBenzine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBenzine.setBounds(108, 111, 134, 28);
		panel_4.add(lblBenzine);
		
		JLabel lblDiesel = new JLabel("Diesel");
		lblDiesel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiesel.setBounds(108, 150, 134, 28);
		panel_4.add(lblDiesel);
		
		JLabel lblHomeFuel = new JLabel("Home Fuel");
		lblHomeFuel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHomeFuel.setBounds(108, 191, 134, 28);
		panel_4.add(lblHomeFuel);
		
		JLabel lblRatesUpdate = new JLabel("Rates update");
		lblRatesUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRatesUpdate.setBounds(188, 11, 191, 38);
		panel_4.add(lblRatesUpdate);
		
		JPanel panel_2 = new JPanel();
		Tab.addTab("Sale reaction report", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnProduce = new JButton("Produce");
		btnProduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaleReactionClick=1;
				SaleId=Integer.parseInt(SaleIDText.getText());
			}
		});
		btnProduce.setBounds(258, 248, 200, 50);
		panel_2.add(btnProduce);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 11, 693, 87);
		panel_2.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setModel(VecToTable2);
		scrollPane_1.setViewportView(table_2);
		
		JLabel lblSaleReactionReport = new JLabel("Sale reaction report");
		lblSaleReactionReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaleReactionReport.setBounds(286, 168, 159, 14);
		panel_2.add(lblSaleReactionReport);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(123, 194, 463, 43);
		panel_2.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(VecToTable3);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(118);
		scrollPane_2.setViewportView(table_1);
		
		SaleIDText = new JTextField();
		SaleIDText.setBounds(338, 126, 86, 20);
		panel_2.add(SaleIDText);
		SaleIDText.setColumns(10);
		
		JLabel lblEnterSaleId = new JLabel("Enter sale ID");
		lblEnterSaleId.setBounds(242, 129, 86, 14);
		panel_2.add(lblEnterSaleId);
		
		JPanel panel_3 = new JPanel();
		Tab.addTab("Customer report", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton button = new JButton("Produce");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerReportclick=1;
			}
		});
		button.setBounds(239, 264, 200, 50);
		panel_3.add(button);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 36, 683, 209);
		panel_3.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(VecToTable4);
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblCutomerReport = new JLabel("Cutomer report");
		lblCutomerReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCutomerReport.setBounds(253, 11, 160, 14);
		panel_3.add(lblCutomerReport);
	}
		return Tab;
}

	/**
	 * Listener of changing tabs
	 */
	public class TabListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if (getTab().getSelectedIndex()==0)
				WorkerController.currTab  = 1;
			if (getTab().getSelectedIndex()==1)
				WorkerController.currTab  = 2;
			if (getTab().getSelectedIndex()==2)
				WorkerController.currTab  = 3;
			if (getTab().getSelectedIndex()==3)
				WorkerController.currTab  = 4;
			if (getTab().getSelectedIndex()==4)
				WorkerController.currTab  = 5;

		}
		
	}

	/*************Getters and Setters *************/
	public Object[][] getTableRowsfuelrates() {
		return TableRowsfuelrates;
	}

	public void setTableRowsfuelrates(Object[][] tableRowsfuelrates) {
		TableRowsfuelrates = tableRowsfuelrates;
	}

	public Campaign getTempNewSale() {
		return tempNewSale;
	}

	public void setTempNewSale(Campaign tempNewSale) {
		this.tempNewSale = tempNewSale;
	}

	public Rates getTempRatesUpdate() {
		return tempRatesUpdate;
	}
	
	public void setTempRatesUpdate(Rates tempRatesUpdate) {
		this.tempRatesUpdate = tempRatesUpdate;
	}
	 
	public int getUpdateRatesSendClick() {
		return updateRatesSendClick;
	}

	public void setUpdateRatesSendClick(int updateRatesSendClick) {
		this.updateRatesSendClick = updateRatesSendClick;
	}
	
	public Object[][] getListItems() {
		return ListItems;
	}

	public void setListItems(Object[][] listItems) {
		ListItems = listItems;
	}
	
	public Object[][] getSaleReactionReportProduce() {
		return SaleReactionReportProduce;
	}

	public void setSaleReactionReportProduce(Object[][] saleReactionReportProduce) {
		SaleReactionReportProduce = saleReactionReportProduce;
	}
	
	public DefaultTableModel getVecToTable3() {
		return VecToTable3;
	}

	public void setVecToTable3(DefaultTableModel vecToTable3) {
		VecToTable3 = vecToTable3;
	}
	
	public Object[][] getCustomerReport() {
		return CustomerReport;
	}

	public void setCustomerReport(Object[][] customerReport) {
		CustomerReport = customerReport;
	}

}