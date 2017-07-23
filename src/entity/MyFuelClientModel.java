package entity;

/**
 * MyFuelClientModel saves the Client fields data like host and port
 * @author G16
 *
 */
public class MyFuelClientModel {
	
	/** the host  */
	private String host;
	
	/** the port  */
	private int port;
	
/**
 * set the new host
 * @param host
 */
	public void setHost(String host) {
		this.host = host;
	}
	
/**
 * set the new port
 * @param port
 */
	public void setPort(int port) {
		this.port = port;
	}
	
/**
 * get the host
 * @return String
 */
	public String getHost() {
		return host;
	}
	
/**
 *  get the port
 * @return int
 */
	public int getPort() {
		return port;
	}
	
}