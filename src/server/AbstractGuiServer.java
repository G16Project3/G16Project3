package server;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * this class is Abstract GUI Server that extends JFrame
 * @author G16
 *
 */
public abstract class AbstractGuiServer extends JFrame
{
	/**
	 * AbstractGuiServer fields -> jbutton
	 * @author G16
	 */
	protected JButton backButton=null;
	
	/**
	 * display Info  method
	 * @param message
	 * @param title
	 * @param messageType
	 * @author G16
	 */
	public void displayInfoMessage(String message,String title,int messageType)
	{
		JOptionPane.showMessageDialog(this, message, title,messageType);
	}
	
	/**
	 * This method adds an action listener to back to menu button.
	 * @param listener
	 * @author G16
	 */
	
	public void addBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
}
