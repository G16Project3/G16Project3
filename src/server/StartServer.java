package server;

/**
 * this class is used to start a server
 * @author G16
 *
 */
public class StartServer {

	public static void main(String[] args) {

		/**
		 * StartServer fields which runs the server
		 * @author G16
		 */
		serverLogGui servLog = new serverLogGui();
	    ServerGui serv = new ServerGui();
	    ServerController servCon = new ServerController(serv,servLog);
	}

}
