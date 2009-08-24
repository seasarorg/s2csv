package org.seasar.s2csv.csv.command.impl;

import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;
import org.seasar.s2csv.csv.command.base.S2CSVRecodeValidationCommand;

/**
 * entityの メソッドでバリデーションチェックを行います。
 * @author newta
 */
public class S2CSVEntityMethodRecodeValidationCommand extends S2CSVRecodeValidationCommand {

	private Object[] methodArgs;
	private Method validateMethod;

	/**
	 * @param validateMethod
	 * @param methodArgs
	 */
	public S2CSVEntityMethodRecodeValidationCommand(Method validateMethod, Object[] methodArgs){ 
		this.methodArgs = methodArgs;
		this.validateMethod = validateMethod;
	}

	/**
	 * @param errorMsgKey 
	 * @param errorMsgValues 
	 * @param validateMethod
	 * @param methodArgs
	 */
	public S2CSVEntityMethodRecodeValidationCommand(String errorMsgKey,Object[] errorMsgValues, Method validateMethod, Object[] methodArgs){
		setErrorMsgKey(errorMsgKey);
		setErrorMsgValues(errorMsgValues);
		
		this.methodArgs = methodArgs;
		this.validateMethod = validateMethod;
	}
	
	@Override
	protected boolean validate(String[] o) {
		methodArgs[0] = o;
		return (Boolean) MethodUtil.invoke(
				validateMethod,
				getContext().getEntity(),
				methodArgs);
	}

}
