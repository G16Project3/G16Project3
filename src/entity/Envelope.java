package entity;

import java.io.Serializable;

/**
 * this class is Envelope Model which saves the Object, task and message fields to send to server , extends AbstractModel.
 * returns returnMsg from server to client.
 * @author G16
 *
 */
public class Envelope  extends AbstractModel{
	
	    /**the model that we send to server*/
		private Object obj;
		
		/**the task that need to do in server*/
		private String task;
		
		/** the message that return from server*/
		private Object returnMsg;

/**
 * 
 * @param obj1 model to send to server
 * @param Task1 that need to do in server
 */
		public Envelope(Object obj1, String Task1){
			obj = obj1;
			task = Task1;
			returnMsg = "";
		}
		
		/**************Getters and Setters************/
		
		public Object getMessage() {
			return returnMsg;
		}


		public void setMessage(Object returnMsg) {
			this.returnMsg = returnMsg;
		}
		public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}

		public Object getObject() 
		{
			return obj;
		}
		
		public void setObject(Object obj1) 
		{
			this.obj = obj1; 
		}
		
}
