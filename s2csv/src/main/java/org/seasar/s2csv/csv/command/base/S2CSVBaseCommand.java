package org.seasar.s2csv.csv.command.base;

import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.command.S2CSVCommandContext;

/**
 * @author newta
 */
public abstract class S2CSVBaseCommand implements S2CSVCommand {

	private String errorMsgKey;
	private Object[] errorMsgValues;
	private S2CSVCommandContext context;
	
	@Override
	public void setErrorMsgKey(String errorMsgKey) {
		this.errorMsgKey = errorMsgKey;
	}

	@Override
	public void setErrorMsgValues(Object[] errorMsgValues) {
		this.errorMsgValues = errorMsgValues;
	}
	
	@Override
	public void setContext(S2CSVCommandContext context) {
		this.context = context;
	}


	@Override
	public String getErrorMsgKey() {
		return errorMsgKey;
	}

	@Override
	public Object[] getErrorMsgValues() {
		return errorMsgValues;
	}
	
	/**
	 * @return the context
	 */
	public S2CSVCommandContext getContext() {
		return context;
	}
}
