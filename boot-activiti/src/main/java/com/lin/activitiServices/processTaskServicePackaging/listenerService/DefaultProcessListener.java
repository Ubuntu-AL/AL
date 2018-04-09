/**
 * <p>Description: </p>
 */
package com.lin.activitiServices.processTaskServicePackaging.listenerService;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * <p>Description: </p>
 */
 @Service("defaultProcessListener")
 @Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DefaultProcessListener implements ExecutionListener{

	 private static final long serialVersionUID = -5398947755254042660L;
	 
	 private Expression message;
		
	/* (non-Javadoc)
	 * <p>Title: notify</p>
	 * <p>Description: </p>
	 * @param execution
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	public void notify(DelegateExecution execution) {
			
		// TODO Auto-generated method stub
		try{
	         //set global flow varible
	         execution.setVariableLocal("condition","success",execution.isProcessInstanceType());
	    }catch(Exception e){
	        execution.setVariableLocal("condition", "fail", execution.isProcessInstanceType());
	    }
		
		System.out.println("流程监听器："+ message.getValue(execution));
	}

	/**
	 * @return the message
	 */
	public Expression getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Expression message) {
		this.message = message;
	}
}
