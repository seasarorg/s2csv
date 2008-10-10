package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.framework.exception.SRuntimeException;

/**
 * CSVエンティティ設定全般のException
 * @author newta
 */
public class CSVConfigException  extends SRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param clazz
	 * @param cause
	 */
	public CSVConfigException(Class<?> clazz, Throwable cause) {
		super("ESCSV0001", new Object[]{clazz.getName()}, cause);
	}

	/**
	 * @param clazz
	 */
	public CSVConfigException(Class<?> clazz) {
		super("ESCSV0001", new Object[]{clazz.getName()});
	}
}