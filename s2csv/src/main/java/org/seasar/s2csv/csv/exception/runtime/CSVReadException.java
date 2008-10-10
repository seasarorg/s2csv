package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.framework.exception.SRuntimeException;

/**
 * CSV読み込みException
 * @author newta
 */
public class CSVReadException extends SRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param cause
	 */
	public CSVReadException(Throwable cause) {
		super("ESCSV0005", null, cause);
	}

	/**
	 * 
	 */
	public CSVReadException() {
		super("ESCSV0005");
	}

}
