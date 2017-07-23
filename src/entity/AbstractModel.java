package entity;

import java.io.Serializable;

/**
 * this class is Abstruct and implements Serializable and will be extended by 'Envelope'
 * @author G16
 *
 */
public class AbstractModel implements Serializable  //to send Object to server we need to implement Serializable
{

	public boolean equals() {
		return false;
	}

}
