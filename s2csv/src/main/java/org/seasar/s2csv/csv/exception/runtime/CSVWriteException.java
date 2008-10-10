package org.seasar.s2csv.csv.exception.runtime;

/**
 * CSV書き込みエラーException
 * @author newta
 */
public class CSVWriteException  extends org.seasar.framework.exception.SRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param cause
	 */
	public CSVWriteException(Throwable cause) {
		super("ESCSV0006", null, cause);
	}

	/**
	 * 
	 */
	public CSVWriteException() {
		super("ESCSV0006");
	}
}