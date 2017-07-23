package gui;

import java.awt.Font;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import controller.LoginController;
import entity.Dummy;
import entity.Envelope;
import client.*;

/**
 * this class is Abstract GUI that extends JFrame
 * @author G16
 *
 */
public abstract class AbstractGUI extends JFrame
{
	private JButton btnLogOut=null;
	private JLabel lblLogo=null;
	private JLabel lblWelcome=null;
	
	/**
	 * constructor
	 * add window Listener 
	 */
	public AbstractGUI(){
		this.addWindowListener(new Exit());
		this.add(getLblLogo());
		this.add(getBtnLogOut());
		this.add(getLblWelcome());
		
	}
	
	/**
	 * constructor
	 *  
	 */
	public AbstractGUI(int i){
		
	}
	public void displayInfoMessage(String message,String title,int messageType)
	{
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}

	
	/**
	 * create MyFuel logo
	 */
	public JLabel getLblLogo() {
		if(lblLogo == null) {
			lblLogo = new JLabel("");
			Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
			lblLogo.setIcon(new ImageIcon(logo));
			lblLogo.setBounds(80, 11, 638, 115);
		}
		return lblLogo;
	}

	/**
	 * create welcome label
	 */
	public JLabel getLblWelcome() {
		if(lblWelcome == null) {
			lblWelcome = new JLabel("Welcome to MyFuel. Enjoy!");
			lblWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
			lblWelcome.setBounds(450, 11, 250, 115);
		}
		return lblWelcome;
	}	
	
	/**
	 * create "log out" button
	 */
	public JButton getBtnLogOut() {
		if(btnLogOut==null){
			btnLogOut = new JButton("Log Out");
			Image ImgLogOut = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
			btnLogOut.setIcon(new ImageIcon(ImgLogOut));
			btnLogOut.setFont(new Font("Dialog", Font.BOLD, 12));
			btnLogOut.setBounds(560, 450, 168, 57);
			btnLogOut.setBorderPainted(false);
			btnLogOut.setContentAreaFilled(false);
			btnLogOut.setLocation(571, 514);

			}
			return btnLogOut;
		}	
			
	/**
	 * This method adds an action listener to back to menu button.
	 * @param listener
	 */
	public void addBackActionListener(ActionListener listener){
		btnLogOut.addActionListener(listener);
	}
	
	/**
	 * Inner class that we press on Exit button - update the status of Employee to 0 (Disconnected) and exit from program
	 * @author G16
	 *
	 */
	class Exit implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {  
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
		
	}
	public void setBtnLogOut(JButton btnLogOut) {
		this.btnLogOut = btnLogOut;
	}
	
	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}
	
	public void setLblWelcome(JLabel lblWelcome) {
		this.lblWelcome = lblWelcome;
	}
}
