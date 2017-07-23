package entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is OrderHomeFuel entity which saves a OrderHomeFuel from a specific customer
 * @author G16
 *
 */
public class OrderHomeFuel extends AbstractModel
{
	/** order id*/
	private int orderId;
	
	private int Urgent;
	

	/** invited by id*/
	private int invitedById;
	
	/** quantity*/
	private int quantity;
	
	/** address by supply*/
	private String addressToSupply;
	
	/** supply date*/
	private String SupplyDate;
	
	/** supply time*/
	private String supplyTime;
	
	/** order type*/
	private String orderType;
	
	/**
	 * Invoice Constructor
	 * this constructor initialize all fields 
	 */
	public OrderHomeFuel(){
		this.orderId = 0;
		this.invitedById = 0;
		this.quantity = 0;
		this.addressToSupply = null;
		this.SupplyDate = null;
		this.supplyTime = null;
		this.orderType = null;
	}
	
	/*****************************************Getters and Setters of fields *****************************/
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getInvitedById() {
		return invitedById;
	}

	public void setInvitedById(int invitedById) {
		this.invitedById = invitedById;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAddressToSupply() {
		return addressToSupply;
	}

	public void setAddressToSupply(String addressToSupply) {
		this.addressToSupply = addressToSupply;
	}

	public String getSupplyDate() {
		return SupplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		SupplyDate = supplyDate;
	}

	public String getSupplyTime() {
		return supplyTime;
	}

	public void setSupplyTime(String supplyTime) {
		this.supplyTime = supplyTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getUrgent() {
		return Urgent;
	}

	public void setUrgent(int urgent) {
		Urgent = urgent;
	}

	public double makeOrder(OrderHomeFuel temp, Rates rates) {
		double discount = 1.0;
		if (temp.getUrgent() == 1)
			discount += 0.02;
		if (temp.getQuantity() < 600)
			;
		else if (temp.getQuantity() <= 800)
			discount -= 0.03;
		else if (temp.getQuantity() > 800)
			discount -= 0.04;
	
		return discount*temp.getQuantity()*rates.getMaxHomeFuel();
	}

}


	