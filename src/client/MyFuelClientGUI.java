package client;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

/**
 * this class is Graphic user interface of MyFuel Client, which extends AbstractGUI
 * @author G16
 *
 */
public class MyFuelClientGUI extends JFrame{
	
	/** Client panel */
	private JPanel contentPane;
	
	/** logo */
	private JLabel lblLogo=null;
	
	/** server label */
	private JLabel lblServerConnection=null;
	
	/** host label */
	private JLabel lbllHost = null;
	
	/** host text field */
	private JTextField TextHost = null;
	
	/** port label */
	private JLabel lblPort = null;
	
	/** port text field */
	private JTextField TextPort = null;
	
	/** connect to server button */
	private JButton btnOk = null;
	
	/** cancel button */
	private JButton btnCancel = null;
	
	/** server pic */
	private JLabel lblServerPic=null;

	/**
	 * Constructor
	 * add all labels, fields and buttons on the frame
	 */
	public MyFuelClientGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 722, 535);
		this.setTitle("MyFuel Connection to Server");
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getLblLogo());
		getContentPane().add(getLblServerConnection());
		getContentPane().add(getLblHost());
		getContentPane().add(getTextHost());
		getContentPane().add(getLblPort());
		getContentPane().add(getTextPort());
		getContentPane().add(getBtnOk());
		getContentPane().add(getBtnCancel());
		getContentPane().add(getLblServerPic());
		getContentPane().setLayout(null);
		this.setVisible(true);
	}
	
	/**************Getters and Setters of Labels, Buttons,TextFields**********/

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
		if(lblServerConnection == null){
			lblServerConnection = new JLabel("Server Connection");
			lblServerConnection.setFont(new Font("Dialog", Font.BOLD, 25));
			lblServerConnection.setBounds(224, 169, 222, 26);
		}
		return lblServerConnection;
	}	


	//create "host" label
	public JLabel getLblHost() {
		if(lbllHost == null){
			lbllHost = new JLabel("Host:");
			lbllHost.setFont(new Font("Dialog", Font.BOLD, 20));
			lbllHost.setBounds(25, 250, 55, 20);
		}
		return lbllHost;
	}		
		

	//create host text field				
	public JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(100, 250, 160, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}
		

	//create "port" label
	public JLabel getLblPort() {
		if(lblPort==null){
			lblPort = new JLabel("Port:");
			lblPort.setFont(new Font("Dialog", Font.BOLD, 20));
			lblPort.setBounds(25, 290, 50, 20);
		}
		return lblPort;
	}


	//create port text field 		
	public JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(100, 290, 160, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}
			

	//create ok button
	public JButton getBtnOk(){
		if(btnOk == null){
			btnOk = new JButton("Connect");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnOk.setBounds(50, 360, 89, 23);
		}
		return btnOk;
	}	
		

	//create cancel button
	public JButton getBtnCancel(){
		if(btnCancel == null){
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(180, 360, 89, 23);
		}
		return btnCancel;
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

	public void clearFields(){
		TextPort.setText("");
		TextHost.setText("");
	}
		
	public String getHost(){
		return TextHost.getText();
	}
		
	public int getPort(){
		if(TextPort.getText().equals(""))
			return 0;
		return Integer.parseInt(TextPort.getText());
	}

	/**
	 * add listener to connection button
	 * @param listener
	 */
	public void addOKActionListener(ActionListener listener){
		btnOk.addActionListener(listener);
	}
	
	/**
	 *  add listener to cancel button  
	 * @param listener
	 */
	public void addCancelActionListener(ActionListener listener){
		btnCancel.addActionListener(listener);
	}
	
	/**
	 * displays warning msg on screen
	 * @param msg
	 */
	public void displayWarnningMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
}
