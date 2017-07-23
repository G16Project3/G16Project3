package Fixtures;

import java.util.IllegalFormatException;

import entity.OrderHomeFuel;
import entity.Customer;
import entity.FuelStation;
import entity.Rates;
import fit.ActionFixture;

public class CheckHomeFuelOrder extends ActionFixture {	
	OrderHomeFuel temp = null;
	
	// rates
	Rates rates = null;
	
	public void setUp(){
		temp = new OrderHomeFuel();
		rates = new Rates();
		rates.setMaxHomeFuel((float)5.5);
	}
	
	public void amount(int qty){
		temp.setQuantity(qty);
	}
	
	public void urgent(int num){
		temp.setUrgent(num);
	}
	
	public double chargeTest() {
		return temp.makeOrder(temp,rates);
		
	}

}
