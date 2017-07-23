package client;

import gui.*;
import controller.*;
import entity.*;

/**
 * runs a client app
 */
public class MyFuelApp {

	public static MyFuelClient clien;
 
	public static void main(String[] args) {

		MyFuelClientGUI clientView = new MyFuelClientGUI(); // this is the gui view //
		MyFuelClientModel clientModel = new MyFuelClientModel(); // it's the module that hold the url port ...  // 
		MyFuelClientController clientController = new MyFuelClientController(clientView,clientModel); //here is all the implementation the Thread starts here // 
	}

}