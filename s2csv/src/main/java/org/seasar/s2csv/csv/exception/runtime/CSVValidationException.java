package org.seasar.s2csv.csv.exception.runtime;

/**
 * バリデーションエラーException
 * @author newta
 */
public class CSVValidationException extends RuntimeException {


	private String msgKey = null;
	private Object[] args = null;
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    public CSVValidationException() {
    }
    /**
     * @param cause
     */
    public CSVValidationException(Throwable cause) {
        super(cause);
    }
    /**
     * @param msgKey
     */
    public CSVValidationException(String msgKey) {
    	this.msgKey = msgKey;
    }
    /**
     * @param msgKey
     * @param args
     */
    public CSVValidationException(String msgKey,Object[] args){
    	this.msgKey = msgKey;
    	this.args = args;
    }
    /**
     * @return msgKey
     */
	public String getMsgKey() {
		return msgKey;
	}
	/**
	 * @param msgKey
	 */
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	/**
	 * @return args
	 */
	public Object[] getArgs() {
		return args;
	}
	/**
	 * @param args
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
