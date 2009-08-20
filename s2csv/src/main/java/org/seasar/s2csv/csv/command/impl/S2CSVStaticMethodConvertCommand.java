package org.seasar.s2csv.csv.command.impl;

import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;
import org.seasar.s2csv.csv.command.base.S2CSVConvertCommand;

/**
 * static メソッドでバリデーションチェックを行います。
 * @author newta
 */
public class S2CSVStaticMethodConvertCommand extends S2CSVConvertCommand {

	private Method convertMethod;

	/**
	 * @param convertMethod
	 */
	public S2CSVStaticMethodConvertCommand(Method convertMethod){ 
		this.convertMethod = convertMethod;
	}

	/**
	 * @param errorMsgKey 
	 * @param errorMsgValues 
	 * @param convertMethod
	 */
	public S2CSVStaticMethodConvertCommand(String errorMsgKey,
			Object[] errorMsgValues, Method convertMethod) {
		setErrorMsgKey(errorMsgKey);
		setErrorMsgValues(errorMsgValues);
		
		this.convertMethod = convertMethod;
	}
	
	@Override
	protected Object convert(Object o) {
		return (Boolean) MethodUtil.invoke(convertMethod,null, new Object[]{o});
	}

}
