package controller;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import entity.Envelope;
import gui.AbstractGUI;
import client.MyFuelClient;
import client.CliMessage;
import client.IObserve;
//import client.ImageSerializer;
import client.MyFuelApp;

/**
 * this class is abstract transfer 
 * @author G16
 *
 */
public abstract class AbstractTransfer 
{

		
		/** The model. 
		 * @author G16*/
		protected Object theModel;
		
		/** The view. 
		 * @author G16*/
		protected AbstractGUI theView;
		
		

		/**
		 * Instantiates a new abstract controller.
		 *
		 * @param theView the the view
		 * @param theModel the the model
		 * @author G16
		 */
		public AbstractTransfer(){}
		
		public AbstractTransfer( AbstractGUI theView , Object theModel) 
		{
			this.theModel = theModel;
			this.theView = theView;
		}
		
		/**
		 * Send to server with String
		 *@author G16
		 * @param request the request
		 */
		protected void sendToServer(String request)
		{
			try 
			{
				MyFuelApp.clien.sendToServer(request);
			} 
			catch (IOException e)
			{
				theView.displayInfoMessage("Error: Server communication problem","Communication Error",0);
				e.printStackTrace();
			}
		}
		
	/**
	 * 
	 * sent to server with Object	
	 * @author G16
	 * @param request
	 */
		
		protected void sendToServer(Object request)
		{
			try 
			{
				MyFuelApp.clien.sendToServer(request);
			} 
			catch (IOException e)
			{
				theView.displayInfoMessage("Error: Server communication problem","Communication Error",0);
				e.printStackTrace();
			}
		}
		
	/**
	 * this function is handle the DB result 	
	 * @author G16
	 * @param message
	 */
		
		 public void handleDBResult(Object message) {
			 
			 //
		 }
		 
		 public void UpdateDB(){
			 //
		 }
		
	}
