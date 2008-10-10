package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.framework.exception.SRuntimeException;

/**
 * CSVカラム設定Exception
 * @author newta
 */
public class CSVColumnConfigException  extends SRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param clazz
	 * @param cause
	 */
	public CSVColumnConfigException(Class<?> clazz, Throwable cause) {
		super("ESCSV0002", new Object[]{clazz.getName()}, cause);
	}
	/**
	 * @param clazz
	 */
	public CSVColumnConfigException(Class<?> clazz) {
		super("ESCSV0002", new Object[]{clazz.getName()});
	}
}