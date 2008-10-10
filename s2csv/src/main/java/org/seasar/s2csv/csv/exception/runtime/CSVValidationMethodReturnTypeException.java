package org.seasar.s2csv.csv.exception.runtime;

import java.lang.reflect.Method;

import org.seasar.framework.exception.SRuntimeException;

/**
 * バリデーション設定エラーException
 * バリデーション定義メソッドでTypeがboolean以外が設定されているときに投げられる
 * @author newta
 */
public class CSVValidationMethodReturnTypeException extends SRuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * @param m
	 * @param cause
	 */
	public CSVValidationMethodReturnTypeException(Method m,Throwable cause) {
		super("ESCSV0008", new Object[]{m.getDeclaringClass(), m.getName()}, cause);
	}
	/**
	 * @param m
	 */
	public CSVValidationMethodReturnTypeException(Method m) {
		this(m, null);
	}

}
