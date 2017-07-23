package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * this class is Graphic user interface of Server which extends AbstractGuiServer
 * this class is the gui we need to connect to DB scheme, given user name and password 
 * @author G16
 */

public class ServerGui extends AbstractGuiServer {
	
	/**all the buttons, labels and text fields */
	private JLabel lblLogo=null;
	private JLabel lblServerConnection=null;
	private JButton loginBut = null;
	private JLabel lblUserName = null;
    private JLabel lblwarningMessage = null;
	private JLabel lblPassword = null;
	private JLabel lblPort = null;
	private JLabel lblScheme = null;
	private JLabel lblServerPic=null;
	
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private JTextField textFieldPort;
	private JTextField textFieldScheme;
	
	private Font myFont;
	
	/**
	 * constructor 
	 * this constructor add all the Label, Button,TextField to the Frame
	 */
	public ServerGui() {
		myFont = new Font("Serif",Font.BOLD,17);
		getContentPane().setLayout(null);
		getContentPane().add(getLblLogo());
		getContentPane().add(getLblServerConnection());
		getContentPane().add(getLogin());
		getContentPane().add(getLblwarningMessage());
		getContentPane().add(getLblUserName());
		getContentPane().add(getLblPassword());
		getContentPane().add(getTextFieldUser());
		getContentPane().add(getTextFieldPass());
		getContentPane().add(getTextFieldport());
		getContentPane().add(getLblPort());
		getContentPane().add(getLblScheme());
		getContentPane().add(getTextFieldScheme());
		getContentPane().add(getLblServerPic());

		
		this.setTitle("MyFuel Login");
		this.setBounds(100, 100, 722, 535);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	
	}
	
	/*********** Getters and Setters of Label, Button,TextField **********/
//create MyFuel logo
	public JLabel getLblLogo() {
		if(lblLogo == null) {
			lblLogo = new JLabel("");
			Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
			lblLogo.setIcon(new ImageIcon(logo));
			lblLogo.setBounds(494, 43, 183, 115);
		}
		return lblLogo;
	} 	

//create "Server Connection" label
	public JLabel getLblServerConnection() {
		if(lblServerConnection==null){
			lblServerConnection = new JLabel("Login");
			lblServerConnection.setFont(new Font("Dialog", Font.BOLD, 25));
			lblServerConnection.setBounds(117, 128, 336, 26);
		}
		return lblServerConnection;
	}		
	
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("Can't login to server ");
			lblwarningMessage.setVerticalAlignment(SwingConstants.TOP);
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 385, 400, 111);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		if(lblwarningMessage != null){
			st = lblwarningMessage.getText() + st;
		}
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setVisible(true);	
		
	}

	public JButton getLogin() {
		
		if(loginBut == null ) {
			loginBut = new JButton("Login");
			loginBut.setBounds(172, 354, 80, 20);
		}
		return loginBut;
	}

	
	public JLabel getLblUserName() {
		if(lblUserName == null) {
			lblUserName = new JLabel("User name:");
			lblUserName.setBounds(37, 218, 90, 20);
			lblUserName.setFont(myFont);
		}
		return lblUserName;
	}
	
	public JLabel getLblPort() {
		if(lblPort == null) {
			lblPort = new JLabel("Listening Port: ");
			lblPort.setBounds(37, 298, 120, 20);
			lblPort.setFont(myFont);
		}
		return lblPort;
	}

	public JLabel getLblPassword() {
		if(lblPassword == null){
			lblPassword = new JLabel("Password :");
			lblPassword.setBounds(37, 258, 90, 20);
			lblPassword.setFont(myFont);
		}
		return lblPassword;
	}
	
	public JLabel getLblScheme() {
		if(lblScheme == null){
			lblScheme = new JLabel("Scheme:");
			lblScheme.setBounds(37, 178, 90, 20);
			lblScheme.setFont(myFont);
		}
		return lblScheme;
	}

	public JTextField getTextFieldUser() {
		if(textFieldUser == null) {
			textFieldUser = new JTextField();
			textFieldUser.setBounds(162, 218, 170, 20);
			textFieldUser.setColumns(10);
		}
		return textFieldUser;
	}

	public JTextField getTextFieldPass() {
		if(textFieldPass == null) {
			textFieldPass = new JTextField();
			textFieldPass.setBounds(162, 258, 170, 20);
			textFieldPass.setColumns(10);
		}
		return textFieldPass;
	}
	
	public JTextField getTextFieldport() {
		if(textFieldPort == null) {
			textFieldPort = new JTextField();
			textFieldPort.setBounds(162, 298, 170, 20);
			textFieldPort.setColumns(10);
		}
		return textFieldPort;
	}
	
	public JTextField getTextFieldScheme() {
		if(textFieldScheme == null) {
			textFieldScheme = new JTextField();
			textFieldScheme.setBounds(162, 178, 170, 20);
			textFieldScheme.setColumns(10);
		}
		return textFieldScheme;
	}
	
//create server picture
	public JLabel getLblServerPic(){
		if(lblServerPic==null){
			lblServerPic = new JLabel("");
			lblServerPic.setBounds(369, 196, 430, 300);
			Image serverpic = new ImageIcon(this.getClass().getResource("/buying.png")).getImage();
			lblServerPic.setIcon(new ImageIcon(serverpic));
		}
		return lblServerPic;	
	}

	public void setTextFieldPass(String str) {
		textFieldPass.setText(str);
	}
	
	public void setTextFieldUser(String str) {
		textFieldUser.setText(str);
	}
	
	public void setTextFieldPort(String str) {
		textFieldPort.setText(str);
	}
	
	public void setTextFieldscheme(String str) {
		textFieldScheme.setText(str);
	}
	
	
	
	public void ClearText(){
		textFieldUser.setText("");
		textFieldPass.setText("");
		textFieldPort.setText("");
		textFieldScheme.setText("");
	}

	public String getTextUserName() {
		return textFieldUser.getText();
	}
	
	public String getTextPassword() {
		
		return textFieldPass.getText();
	}
	
	public String getTextPort() {
		
		return textFieldPort.getText();
	}
	public String getTextScheme() {
		
		return textFieldScheme.getText();
	}
	/**
	 * add to login button ActionListener
	 * @param listener
	 */
	public void addLoginActionListener(ActionListener listener){
		loginBut.addActionListener(listener);
	}

	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
}
