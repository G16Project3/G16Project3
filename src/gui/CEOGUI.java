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
import java.awt.event.KeyEvent;
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
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JFormattedTextField;

import java.awt.event.KeyAdapter;




import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

/**
 * this class is GUI of CEO
 * @author G16
 *
 */
public class CEOGUI extends WorkerGUI {

	/** CEO panel */
	private JPanel contentPane;
	
	/** warning label */
	private JLabel lblwarningMessage=null;
	
	/** tabs */
	private JTabbedPane Tab=null;
		
	/** Quarter report panel*/
	private JPanel QuarterReport;

	/** Campaign panel*/
	private JPanel Campigan;
	
	/** Insert order details label*/
	private JLabel lblInsertOrderDetails_1;
	
	/** flag if car accept button was pressed*/
	public  int carAcceptClick = 0;
	
	/** flag if home accept button was pressed*/
	public  int homeAcceptClick = 0;
	
	/** stores a Quarter Report details*/
	private QuarterReport tempQuarterReport = null;
	
	/** stores Campaign details*/
	private Campaign tempCampaign = null; 
	
	/** for use of campaign id*/
	private JTextField campaignIdPattern;
	
	/** for use of campaign name*/
	private JTextField CampaiganNameText;
	
	/** for use of discount percent*/
	private JTextField discountPrecentPattern;
	
	/** for use of quarter id*/
	private JTextField quarterIdPattern;
	
	/** for use of invoice report*/
	private JTextField invoiceIdReportPattern;
	
	/** for use of incomes report*/
	private JTextField incomesReportPattern;
			
	/** stores date*/
	private Date datef;
	
	/** stores date*/
	private Date dated;
	
	/** stores date*/
	private Date datef1;
	
	/** stores date*/
	private Date dated1;
	
	/** chosen report*/
	public String chooseReport;
	public String chooseRate;
	public String chooseQuarterReport;
	
	private String[] quarterReport = {"","FirstQuarter","SecondQuarter","ThirdQuarter","FourthQuarter"};
	
	/** check campaign button*/
	public JButton btnCheckCampaign;
	
	/** approve button*/
	public JButton btnApprove;
	
	/** table to show details*/
	private JTable table;
	
	/** for use of table*/
	private Object[][] TableRows=null;

	public DefaultTableModel VecToTable = new DefaultTableModel(TableRows,new String[] {"Campaign Id", "Campaig name", "Discount percent", "start date", "End date", "Start hour", "End hour","Status"});
	public DefaultTableModel VecToTable1 = new DefaultTableModel(TableRows,new String[] {"ReportQuarterId", "totalIncome", "Benzine95", "Diesel", "Motors", "Quarter", "year"});
	public DefaultTableModel VecToTable2 = new DefaultTableModel(TableRows,new String[] {"maxMotorsFuel", "Motors", "maxbenzine95", "Benzine95", "maxDiesel", "Diesel", "maxHomefuel", "HomeFuel"," RatesId"});

	public JTextField textAprove;
	
	/** flag if approve button was pressed*/
	public int approvFlag=0;
	
	/** flag if check campaign button was pressed*/
	public int chcekFlag=0;
	public int chcekQFlag=0;
	public int checkRateFlag=0;
	public int approvCheckFlag=0;
	public JTextField textField;
	public JComboBox comboBox_1;
	
	public JComboBox cbcrate;
	 
	
	/**
	 * CEO GUI constructor
	 * @author Omri
	 */
	public CEOGUI() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 780, 600);
		this.setTitle("MyFuel - CEO Window");
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getTab());
		this.setVisible(true);
		}
	
	/** initialize tabs */
	public JTabbedPane getTab() {
		if(Tab==null){
			Tab = new JTabbedPane();
			Tab.setBounds(0, 139, 765, 371);
			
		    Campigan = new JPanel();
		    Campigan.setBorder(new LineBorder(new Color(0, 0, 0)));
		    Tab.addTab("Campaign", Campigan);

		    Campigan.setLayout(null);
		    btnCheckCampaign = new JButton("Check Campaign");
		    btnCheckCampaign.setBounds(588, 282, 162, 50);
		    btnCheckCampaign.setFont(new Font("Tahoma", Font.BOLD, 13));
		    Campigan.add(btnCheckCampaign);
		    
		    JLabel lblNewLabel = new JLabel("Campaign");
		    lblNewLabel.setBounds(0, 0, 120, 31);
		    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		    Campigan.add(lblNewLabel);
		    
		    JScrollPane scrollPane_1 = new JScrollPane();
		    scrollPane_1.setBounds(10, 42, 740, 236);
		    Campigan.add(scrollPane_1);
		    
		    table = new JTable();
		    scrollPane_1.setViewportView(table);
		    table.setBorder(UIManager.getBorder("Button.border"));
		    table.setFillsViewportHeight(true);
		    table.setModel(VecToTable);
			scrollPane_1.setViewportView(table);
			
			textAprove = new JTextField();
			textAprove.setBounds(371, 298, 146, 20);
			Campigan.add(textAprove);
			textAprove.setColumns(10);
			textAprove.addKeyListener(new KeyAdapter() {
				@Override 
				//make sure that the user can only enter digits 
				public void keyTyped(KeyEvent evt) {
					char vChar = evt.getKeyChar();
					if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) { evt.consume(); }
						
				}
			});
			
			JLabel lblEnterTheCampaign = new JLabel("Enter the campaign ID you want to  approved");
			lblEnterTheCampaign.setBounds(10, 283, 299, 50);
			Campigan.add(lblEnterTheCampaign);
			
			JButton btnApprove = new JButton("Approve");
			btnApprove.setBounds(400, 320, 89, 23);
			Campigan.add(btnApprove);
			this.btnApprove=btnApprove;
			
			btnCheckCampaign.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					chcekFlag=1;
				}
			});
			
			btnApprove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					approvFlag=1;
				}
			});
			

		    		    		    
			//CEO window
			QuarterReport = new JPanel();
			QuarterReport.setLayout(null);
		    
		    Tab.addTab("Create quarter report", QuarterReport);
		    Tab.addChangeListener(new TabListener());
		    JLabel lblInsertOrderDetails = new JLabel("");
		    lblInsertOrderDetails.setBounds(10, 11, 2, 30);
		    QuarterReport.add(lblInsertOrderDetails);
		    
		    lblInsertOrderDetails_1 = new JLabel("Create quarter report");
		    lblInsertOrderDetails_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		    lblInsertOrderDetails_1.setBounds(197, 0, 228, 50);
		    QuarterReport.add(lblInsertOrderDetails_1);
		    
		    final JComboBox comboBox = new JComboBox(quarterReport);
		    comboBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		chooseReport = comboBox.getSelectedItem().toString();
		    	}
		    });
		    comboBox.setBounds(186, 63, 92, 22);
		    QuarterReport.add(comboBox);
		    
		    JLabel lblChosse = new JLabel("Choose Quarter");
		    lblChosse.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblChosse.setBounds(30, 63, 111, 22);
		    QuarterReport.add(lblChosse);
		      
		    JPanel checkQuarter = new JPanel();
		    checkQuarter.setBorder(new LineBorder(new Color(0, 0, 0)));
		    Tab.addTab("check quarter report", checkQuarter);
		    checkQuarter.setLayout(null);
		    JScrollPane scrollPane_2 = new JScrollPane();
		    scrollPane_2.setBounds(0, 0, 748, 238);
		    checkQuarter.add(scrollPane_2);
		    JTable tableQ = new JTable();
		    scrollPane_2.setViewportView(tableQ);
		    tableQ.setBorder(UIManager.getBorder("Button.border"));
		    tableQ.setFillsViewportHeight(true);
		    tableQ.setModel(VecToTable1);
		    scrollPane_2.setViewportView(tableQ);
		    
		    JButton btnCheck = new JButton("Check ");
		    btnCheck.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		if (comboBox_1.getSelectedIndex() > 0){
					    chooseQuarterReport = comboBox_1.getSelectedItem().toString();
			    		chcekQFlag=1;
		    		}
		    		else chcekQFlag=0;
		    	}
		    });
		    btnCheck.setFont(new Font("Tahoma", Font.BOLD, 13));
		    btnCheck.setBounds(636, 285, 112, 43);
		    checkQuarter.add(btnCheck);
		    
		    JLabel lblSelectQuarter = new JLabel("Select quarter");
		    lblSelectQuarter.setHorizontalAlignment(SwingConstants.CENTER);
		    lblSelectQuarter.setBounds(10, 251, 100, 16);
		    checkQuarter.add(lblSelectQuarter);
		    
		    comboBox_1 = new JComboBox();
		    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "FirstQuarter", "SecondQuarter", "ThirdQuarter", "FourthQuarter"}));
		    comboBox_1.setBounds(163, 248, 140, 22);
		    checkQuarter.add(comboBox_1);
		    
		    JLabel lblEnterYearEx = new JLabel("Enter year ex : 2016");
		    lblEnterYearEx.setHorizontalAlignment(SwingConstants.CENTER);
		    lblEnterYearEx.setBounds(12, 298, 147, 16);
		    checkQuarter.add(lblEnterYearEx);
		    
		    textField = new JTextField();
		    textField.setBounds(163, 295, 140, 22);
		    checkQuarter.add(textField);
		    textField.setColumns(10);
		    textField.addKeyListener(new KeyAdapter() {
				@Override 
				//make sure that the user can only enter digits 
				public void keyTyped(KeyEvent evt) {
					char vChar = evt.getKeyChar();
					if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) { evt.consume(); }
						
				}
			});
		    
		    
		    JPanel rates = new JPanel();
		    rates.setBorder(new LineBorder(new Color(0, 0, 0)));
		    Tab.addTab("check rates requests", rates);
		    rates.setLayout(null);
		    JScrollPane scrollPane_3 = new JScrollPane();
		    scrollPane_3.setBounds(0, 0, 748, 238);
		    rates.add(scrollPane_3);
		    
		    
		    JTable tableR = new JTable();
		    scrollPane_3.setViewportView(tableR);
		    tableR.setBorder(UIManager.getBorder("Button.border"));
		    tableR.setFillsViewportHeight(true);
		    tableR.setModel(VecToTable2);
		    scrollPane_3.setViewportView(tableR);
		    
		    JLabel lblEnterRateId = new JLabel("Select rate ");
		    lblEnterRateId.setHorizontalAlignment(SwingConstants.CENTER);
		    lblEnterRateId.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblEnterRateId.setBounds(12, 270, 107, 25);
		    rates.add(lblEnterRateId);
		    
		    JButton btnrateApprove = new JButton("Approve rate");
		    btnrateApprove.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		approvCheckFlag=1;
		    	}
		    });
		    btnrateApprove.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnrateApprove.setBounds(131, 303, 116, 25);
		    rates.add(btnrateApprove);
		    
		    JButton btnCheckRate = new JButton("Check rate");
		    btnCheckRate.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		checkRateFlag=1;
		    	}
		    });
		    btnCheckRate.setFont(new Font("Tahoma", Font.BOLD, 13));
		    btnCheckRate.setBounds(641, 292, 107, 35);
		    rates.add(btnCheckRate);
		    
		    cbcrate = new JComboBox();
		    cbcrate.setModel(new DefaultComboBoxModel(new String[] {"", "Benzine95", "Diesel", "Motors", "HomeFuel"}));
		    cbcrate.setBounds(131, 271, 116, 22);
		    rates.add(cbcrate);
		    cbcrate.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		chooseRate = cbcrate.getSelectedItem().toString();
		    	}
		    });
		}
		return Tab;
	}


//create warning message 
	
	/** creates warning message*/
	public JLabel getLblwarningMessage() 
	{
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setFont(new Font("Arial", Font.BOLD, 12));
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	
//set warning message true with string
	
	/** sets warning message on and shows a string*/
	public void setWarningMessageVisibleTrue(String st) 
	{
		lblwarningMessage.setText(st);
		lblwarningMessage.setVisible(true);	
	}
//set warning message true with string	
	
	/** sets warning message off*/
	public void setWarningMessageVisibleFalse() 
	{
		lblwarningMessage.setVisible(false);	
	}
	
	/** welcomes a user with msg*/
	public void setLblWelcome(String Userid)
	 {
		 this.setTitle("Hello "+Userid);
	 }

	     //----------------Create New Campaign start ----------//	
	
	/** shows a new campaign when pressed*/
	 public class addNewCampaign implements ActionListener
	 {
		public void actionPerformed(ActionEvent e) {
			tempCampaign.setCampaignId(Integer.parseInt(campaignIdPattern.getText()));
			tempCampaign.setcampaigName(CampaiganNameText.getText());
			tempCampaign.setdiscountPercent(Integer.parseInt(discountPrecentPattern.getText()));
			tempCampaign.setstartDay(datef);
			tempCampaign.setendDay(dated);
		}
		 
	 }
	 
		public Campaign getTempCampaign() {
			return tempCampaign;
		}

		public void setTempCampaign(Campaign tempCampaign) {
			this.tempCampaign = tempCampaign;
		}

		//--------------------Create New Campaign end ---------//
		

		
		
		//----------------Create New Quarter Report start-----------//
		
		/** shows a new quarter report when pressed*/
	public class CreatNewQuarterReport implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				tempQuarterReport.setReportQuarterId(Integer.parseInt(quarterIdPattern.getText()));
				tempQuarterReport.setInvoicesIds(Integer.parseInt(invoiceIdReportPattern.getText()));
				tempQuarterReport.setTotalIncome(Integer.parseInt(incomesReportPattern.getText()));

			}
		}

	public QuarterReport getQuarterReport() {
			return tempQuarterReport;
		}

	public void QuarterReport(QuarterReport tempQuarterReport) {
			this.tempQuarterReport = tempQuarterReport;
		}
		
	//--------------------Create New Quarter Report end ---------//
	
	/** used to change tabs when user press on a tab*/
	public class TabListener implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				if (getTab().getSelectedIndex()==0)
					UserController.currTab  = 1;
				if (getTab().getSelectedIndex()==1)
					UserController.currTab  = 2;
				if (getTab().getSelectedIndex()==2)
					UserController.currTab  = 3;
			}
			
		}
	
	public void createTabActionListener(ChangeListener listener){
		Tab.addChangeListener(listener);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public Object[][] getTableRows() {
	return TableRows;
}

	public void setTableRows(Object[][] tableRows) {
	TableRows = tableRows;
}
}



