package Fixtures;

import fit.ActionFixture;
import entity.FuelStation;
import entity.StationFuelOrder;
public class FuelSupplierOrder extends ActionFixture{
	FuelStation temp;
	FuelStation station1 = new FuelStation() ;
	FuelStation station2 = new FuelStation();
	String fuelType;
	int amount;
	boolean flag=false;
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public void startOrder(){
		station1.setStationMenagerID(1111);
		station1.setMinLevelAmount(50);
		station1.setFuel95Amount(2000);
		station1.setDieselAmount(2000);
		station1.setMotorsFuelAmount(2000);
		temp = new FuelStation() ;
		
	}
	public void managerId(int id){
		temp.setStationMenagerID(id);
	}
	public void setLowLevel(int low){
		temp.setMinLevelAmount(low);
	}
	
	public void addAmount(int add){
		this.amount = add;
	}
	public boolean checkOrder(){
		
		return flag = temp.checkFuelOrder(temp,amount,this.getFuelType());
	}
	
	public int checkAddAmount(){
		if(flag == false)
			return -1;
		else
		return temp.checkAdded(station1,amount,this.getFuelType());
	}
}
