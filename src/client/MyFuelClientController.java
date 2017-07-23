package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.LoginController;
import entity.LoginMod;
import entity.MyFuelClientModel;
import gui.LoginGUI;
import client.MyFuelClientGUI;
import client.MyFuelClient;
import client.MyFuelApp;;
//import client.CCRMClient;

/**
 * This Class is a CCRM Client Controller which create ccrmClient instance and check the port and host to server
 * @author G16
 *
 */
public class MyFuelClientController 
{
	
	private MyFuelClientGUI clientView;
	private MyFuelClientModel clientModel;
	
	/**
	 * constructor  
	 * @param clientView is gui that show the host and port
	 * @param clientModel is entity of client that include host and port
	 */
	public MyFuelClientController(MyFuelClientGUI clientView,MyFuelClientModel clientModel)
	{
		this.clientView = clientView;
		this.clientModel = clientModel;
		clientView.addOKActionListener(new OKListener());
		clientView.addCancelActionListener(new CancelListener());
	}

	/**
	 * check if the Input type correctly
	 * @return boolean
	 */
	public boolean checkInput() 
	{
		try
		{
			if(clientView.getHost().equals("") || clientView.getPort()==0)
			{
				clientView.displayWarnningMessage("please enter some fields!!");
				clientView.clearFields();
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			clientView.displayWarnningMessage("Error: Use of illegal characters");
			return false;
		}
	}

	/**
	 * Inner class that handles when Button OK Pressed, implements ActiontListener
	 * @author G16
	 *
	 */
	class OKListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			if(checkInput() == false)
				return;
			try 
			{
				String host = new String (clientView.getHost());
				int port = clientView.getPort();
				clientModel.setHost(host);
				clientModel.setPort(port);
				
				MyFuelApp.clien = new MyFuelClient(host,port); //singleton
				
				if(MyFuelApp.clien.isConnected())
				{
					clientView.dispose(); //remove the current window to display login window
				
					//create a new login controller
					LoginGUI logView = new LoginGUI();
					LoginMod logModel = new LoginMod();
					LoginController logController = new LoginController(logView,logModel);
				}
				else
				{
					clientView.displayWarnningMessage("Faild to connect. check IP and port!");
					clientView.clearFields();
				}
				
			} catch (NumberFormatException e) 
			{
				clientView.displayWarnningMessage("Faild to connect. check IP and port!");
				clientView.clearFields();
			} 
			catch (IOException e) 
			{
				clientView.displayWarnningMessage("Connection problem. check IP and Port.");
			}
		}
	}
	
	/**
	 *  Inner class that handles when Button cancel Pressed, implements ActiontListener
	 * @author G16
	 *
	 */
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			clientView.dispose();
			System.exit(1);
		}
	}

}
