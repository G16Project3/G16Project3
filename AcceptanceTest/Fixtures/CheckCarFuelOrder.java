package Fixtures;

import java.util.IllegalFormatException;

import entity.Car;
import entity.CarFuelOrder;
import entity.Customer;
import entity.FuelStation;
import entity.Rates;
import fit.ActionFixture;

public class CheckCarFuelOrder extends ActionFixture {	
	CarFuelOrder temp = null;
	
	// car with an owner who is a member
	Car car = null;
	
	// car without an owner who is a member
	Car car2 = null;
	
	// rates
	Rates rates = null;
	
	// an owner who is a member in a station
	Customer customer = null;
	
	public void setUp(){
		temp = new CarFuelOrder();
		temp.setQuantity(10);
		customer = new Customer();
		customer.setCustomerId(14);
		customer.setFuelStationAllowed(333);
		customer.setMemberShipType("single car");
		car = new Car();
		car.setlicenseplatenumber("1111");
		car.setFuelType("Benzine95");
		car.setownerid(14);
		car2 = new Car();
		car2.setlicenseplatenumber("2222");
		car2.setFuelType("Benzine95");
		car2.setownerid(-1);
		rates = new Rates();
		rates.setMaxBenzine95((float)5.23);
	}
	
	public void customerId(int id){
		temp.setCustomer_id(id);
	}
	
	public void licensePlateNumber(String num){
		temp.setLicensePlateNumber(num);
	}

	public void fuelType(String type){
		temp.setFuel_type(type);	
	}
	
	public void stationId(int statiom){
		temp.setFuel_station_id(statiom);
	}
	
	public void quantity(int qty) {
		temp.setQuantity(qty);
	}
	
	public double chargeTest() {
		if (temp.getQuantity() <= 0)
			return 0;
		if ((car2.getlicenseplatenumber().equals(temp.getLicensePlateNumber())))
			return temp.makeOrder(temp,null,rates);
		if (!car.getFuelType().equals(temp.getFuel_type()))
				return -1;
		return temp.makeOrder(temp,customer,rates);
		
	}

}
