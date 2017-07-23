package unittests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Invoice;
import entity.OrderHomeFuel;

public class TrackOrdersUnitTest {


	@Test
	public void test() {
		OrderHomeFuel[] homefuelorder1 = new OrderHomeFuel[3];
		
		//---------------- Test number 1-----------------///
				for (int i=0;i<3;i++)
				{
				homefuelorder1[i] = new OrderHomeFuel();
				homefuelorder1[i].setAddressToSupply("ort Braude"); 
				homefuelorder1[i].setInvitedById(1);
				homefuelorder1[i].setOrderId(1+i);
				if (i==0)
				{
				homefuelorder1[i].setOrderType("Under 600");
				homefuelorder1[i].setQuantity(400);
				}else if (i==1)
				{
					homefuelorder1[i].setOrderType("600-800");
					homefuelorder1[i].setQuantity(700);
				}else if (i==2)
				{
					homefuelorder1[i].setOrderType("800+");
					homefuelorder1[i].setQuantity(850);		
				}
				homefuelorder1[i].setSupplyDate("2016/02/03");
				homefuelorder1[i].setSupplyTime("10:00");
				}
				
				Object[][] TableRows=new Object[3][7];
				for (int i=0;i<3;i++)
				{
				TableRows[i][0]=homefuelorder1[i].getAddressToSupply();
				TableRows[i][1]=homefuelorder1[i].getInvitedById();
				TableRows[i][2]=homefuelorder1[i].getOrderId();
				TableRows[i][3]=homefuelorder1[i].getOrderType();
				TableRows[i][4]=homefuelorder1[i].getQuantity();
				TableRows[i][5]=homefuelorder1[i].getSupplyDate();
				TableRows[i][6]=homefuelorder1[i].getSupplyTime();
				}
				
				for (int i=0;i<3;i++)
				{
				assertEquals(TableRows[i][0],"ort Braude");
				assertEquals(TableRows[i][1],1);
				assertEquals(TableRows[i][2],i+1);
				if (i==0)
				{
				assertEquals(TableRows[i][3],"Under 600");
				assertEquals(TableRows[i][4],400);
				}else if (i==1)
				{
					assertEquals(TableRows[i][3],"600-800");
					assertEquals(TableRows[i][4],700);
				}else if (i==2)
				{
					assertEquals(TableRows[i][3],"800+");
					assertEquals(TableRows[i][4],850);	
				}
				assertEquals(TableRows[i][5],"2016/02/03");
				assertEquals(TableRows[i][6],"10:00");
				}
				}

}
