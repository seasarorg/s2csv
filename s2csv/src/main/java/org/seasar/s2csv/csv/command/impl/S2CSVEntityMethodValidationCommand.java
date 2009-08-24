package org.seasar.s2csv.csv.command.impl;

import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;
import org.seasar.s2csv.csv.command.base.S2CSVColmunValidationCommand;

/**
 * entityの メソッドでバリデーションチェックを行います。
 * @author newta
 */
public class S2CSVEntityMethodValidationCommand extends S2CSVColmunValidationCommand {

	private Object[] methodArgs;
	private Method validateMethod;

	/**
	 * @param validateMethod
	 * @param methodArgs
	 */
	public S2CSVEntityMethodValidationCommand(Method validateMethod, Object[] methodArgs){ 
		this.methodArgs = methodArgs;
		this.validateMethod = validateMethod;
	}

	/**
	 * @param errorMsgKey 
	 * @param errorMsgValues 
	 * @param validateMethod
	 * @param methodArgs
	 */
	public S2CSVEntityMethodValidationCommand(String errorMsgKey,Object[] errorMsgValues, Method validateMethod, Object[] methodArgs){
		setErrorMsgKey(errorMsgKey);
		setErrorMsgValues(errorMsgValues);
		
		this.methodArgs = methodArgs;
		this.validateMethod = validateMethod;
	}
	
	@Override
	protected boolean validate(String o) {
		methodArgs[0] = o;
		return (Boolean) MethodUtil.invoke(
				validateMethod,
				getContext().getEntity(),
				methodArgs);
	}

}
