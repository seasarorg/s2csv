package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.framework.exception.SRuntimeException;

/**
 * コンバートエラーException
 * @author newta
 */
public class CSVChangeException extends SRuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * @param cause
	 */
	public CSVChangeException(Throwable cause) {
		super("ESCSV0004", null, cause);
	}
	/**
	 * 
	 */
	public CSVChangeException() {
		super("ESCSV0004");
	}

}
