package org.seasar.s2csv.csv.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * CSVフォーマットが正しくなかったときに発生するExceptionです
 * @author newta
 */
public class CSVFormatException extends SRuntimeException {

	private static final long serialVersionUID = 346646858530793664L;

	private long lineNo;
	/**
	 * 
	 * @param lineNo
	 * @param cause
	 */
	public CSVFormatException(long lineNo,Throwable cause) {
		super("ESCSV0009", new Object[]{String.valueOf(lineNo)}, cause);
		this.lineNo = lineNo;
	}
	/**
	 * 
	 * @param lineNo
	 */
	public CSVFormatException(long lineNo) {
		super("ESCSV0009", new Object[]{String.valueOf(lineNo)});
		this.lineNo = lineNo;
	}
	/**
	 * @return err line no
	 */
	public long getErrorLineNo(){
		return lineNo;
	}
}
