package client;

import entity.Envelope;

/** 
 *	abstract. handles messages from server
 *  @author G16
 */
public interface IHandleMessage {
	void OnMessageRecived(Envelope envelope);

}
