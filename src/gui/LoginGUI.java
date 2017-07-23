package gui; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JRadioButton;

/**
 * this class is LoginGUI that draws the login 
 * @author G16
 *
 */
public class LoginGUI extends JFrame  {
	
	/** shows logo*/
	private JLabel lblLogo=null;
	
	/** shows pic*/
	private JLabel lblPic=null;
	
	/** login button*/
	private JButton loginBut = null;
	
	/** shows username label*/
	private JLabel lblUserName = null;
	
	/** shows warning msg*/
    private JLabel lblwarningMessage = null;
    
    /** shows login label*/
    private JLabel lblLoginshow = null;
    
    /** shows password label*/
	private JLabel lblPassword = null;
	
	/** used to get username*/
	private JTextField userField;
	
	/** used to get password*/
	private JPasswordField passfield;
	
	/** used to select user type*/
	private ButtonGroup decisionGroup;
	
	/** option to login as customer*/
	private JRadioButton loginCustomer;
	
	/** option to login as worker*/
	private JRadioButton loginWorker;
	
	/**
	 * login GUI Constructor
	 * this constructor add all the Label, Button,userField on the Frame
	 */
	public LoginGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 722, 535);
		this.setTitle("MyFuel-Login");
		this.setResizable(false);
		getContentPane().add(getLblLogo());
		getContentPane().add(getLblPic());
		getContentPane().setLayout(null);
		getContentPane().add(getLogin());
		getContentPane().add(getLblwarningMessage());
		getContentPane().add(getLblUserName());
		getContentPane().add(getLblPassword());
		getContentPane().add(getTextFieldUser());
		getContentPane().add(getTextFieldPass());
		getContentPane().add(getLblLog());
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblLoginAs = new JLabel("login as :");
		lblLoginAs.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLoginAs.setBounds(61, 312, 112, 30);
		getContentPane().add(lblLoginAs);
		
		
		//---------Radio Button of the Login--------//
		loginCustomer = new JRadioButton("Customer");
		loginCustomer.setBounds(173, 318, 90, 25);
		loginCustomer.setSelected(true);
		getContentPane().add(loginCustomer);
		
		loginWorker = new JRadioButton("Worker");
		loginWorker.setBounds(270, 318, 90, 25);
		getContentPane().add(loginWorker);
		this.setVisible(true);

		decisionGroup = new ButtonGroup();
		decisionGroup.add(loginCustomer);
		decisionGroup.add(loginWorker);
		//---------Radio Button of the Login--------//

	}
	
	/*************Getters and Setters of Label, Button,userField *************/
	
	public JLabel getLblLogo() {
		if(lblLogo == null) {
			lblLogo = new JLabel("");
			Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
			lblLogo.setIcon(new ImageIcon(logo));
			lblLogo.setBounds(494, 35, 183, 115);
		}
		return lblLogo;
	} 
	
	public JLabel getLblPic() {
		if(lblPic == null) {
			lblPic = new JLabel("");
			Image loginpic = new ImageIcon(this.getClass().getResource("/buying.png")).getImage();
			lblPic.setIcon(new ImageIcon(loginpic));
			lblPic.setBounds(400, 200, 280, 280);
		}
		return lblPic;
	} 
	
	
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("user name or password is wrong!");
			lblwarningMessage.setFont(new Font("Dialog", Font.BOLD, 11));
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(60, 439, 300, 30);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setVisible(true);		
	}

	public JButton getLogin() {
		if(loginBut == null ) {
			Image imgLogin = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
			loginBut = new JButton("");
			loginBut.setFont(new Font("Dialog", Font.BOLD, 14));
			loginBut.setBounds(154, 360, 155, 70);
			loginBut.setIcon(new ImageIcon(imgLogin));
			loginBut.setContentAreaFilled(false);
		}
		return loginBut;
	}

	public JLabel getLblUserName() {
		if(lblUserName == null) {
			lblUserName = new JLabel("User name:");
			lblUserName.setBounds(61, 229, 112, 20);
			lblUserName.setFont(new Font("Dialog", Font.BOLD, 20));
		}
		return lblUserName;
	}
	
	public JLabel getLblLog() {
		if(lblLoginshow == null) {
			lblLoginshow = new JLabel("Welcome to MyFuel - Login");
			lblLoginshow.setFont(new Font("Dialog", Font.BOLD, 25));
			lblLoginshow.setBounds(180, 150, 380, 32);
			lblLoginshow.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblLoginshow;
	}

	public JLabel getLblPassword() {
		if(lblPassword == null){
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(61, 269, 112, 30);
			lblPassword.setFont(new Font("Dialog", Font.BOLD, 20));
		}
		return lblPassword;
	}

	public JTextField getTextFieldUser() {
		if(userField == null) {
			userField = new JTextField();
			userField.setBounds(173, 233, 130, 20);
			userField.setColumns(10);
		}
		return userField;
	}

	public JPasswordField getTextFieldPass() {
		if(passfield == null) {
			passfield = new JPasswordField();
			passfield.setBounds(173, 276, 130, 20);
			passfield.setColumns(10);
		}
		return passfield;
	}
	
	public void ClearText(){
		userField.setText("");
		passfield.setText("");
	}
	
	/******************get the texts from Textfields**************/
	
	public String getTextUserName() {
		return userField.getText();
	}
	
	public String getTextPassword() {
		String str = new String(passfield.getPassword());
		return str;
	}
	
	public boolean getloginCustomerSelect() {
		return loginCustomer.isSelected();
	}
	
	public boolean getloginWorkerSelect() {
		return loginWorker.isSelected();
	}
	
	
	/**
	 * add to Login button Action Listener
	 * @param listener
	 */
	
	public void addLoginActionListener(ActionListener listener){
		loginBut.addActionListener(listener);

	}
	
	
}
