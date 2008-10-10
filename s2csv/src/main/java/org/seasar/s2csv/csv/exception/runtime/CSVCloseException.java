package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.framework.exception.SRuntimeException;

/**
 * Close処理Exception
 * @author newta
 */
public class CSVCloseException  extends SRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param cause
	 */
	public CSVCloseException(Throwable cause) {
		super("ESCSV0007", null, cause);
	}

	/**
	 * 
	 */
	public CSVCloseException() {
		super("ESCSV0007");
	}
}