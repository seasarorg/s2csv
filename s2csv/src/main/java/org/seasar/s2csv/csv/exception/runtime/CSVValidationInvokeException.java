package org.seasar.s2csv.csv.exception.runtime;

import java.lang.reflect.Method;

import org.seasar.framework.exception.SRuntimeException;

/**
 * バリデーション実行Exception
 * @author newta
 */
public class CSVValidationInvokeException extends SRuntimeException{

	private static final long serialVersionUID = 1L;
	/**
	 * @param method
	 * @param cause
	 */
	public CSVValidationInvokeException(Method method, Throwable cause) {
		super("ESCSV0003", new Object[]{method.getName()}, cause);
	}
	/**
	 * @param method
	 */
	public CSVValidationInvokeException(Method method) {
		super("ESCSV0003", new Object[]{method.getName()});
	}
}
