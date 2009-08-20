package org.seasar.s2csv.csv.command.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.MethodUtil;
import org.seasar.s2csv.csv.command.base.S2CSVConvertCommand;

/**
 * static メソッドでバリデーションチェックを行います。
 * @author newta
 */
public class S2CSVMethodConvertCommand extends S2CSVConvertCommand {

	private Map<PropertyDesc, String> convClassProps;
	private Method convertMethod;
	private Object convertClassInstance;

	/**
	 * @param convertClassInstance 
	 * @param convertMethod
	 * @param convClassProps
	 */
	public S2CSVMethodConvertCommand(
			Object convertClassInstance,
			Method convertMethod,
			Map<PropertyDesc, String> convClassProps){
		this.convertClassInstance = convertClassInstance;
		this.convClassProps = convClassProps;
		this.convertMethod = convertMethod;
	}

	/**
	 * @param errorMsgKey 
	 * @param errorMsgValues 
	 * @param convertClassInstance 
	 * @param convertMethod
	 * @param convClassProps
	 */
	public S2CSVMethodConvertCommand(
			String errorMsgKey,
			Object[] errorMsgValues,
			Object convertClassInstance,
			Method convertMethod,
			Map<PropertyDesc, String> convClassProps) {
		setErrorMsgKey(errorMsgKey);
		setErrorMsgValues(errorMsgValues);

		this.convertClassInstance = convertClassInstance;
		this.convClassProps = convClassProps;
		this.convertMethod = convertMethod;
	}
	
	@Override
	protected Object convert(Object o) {
		
		//コンバータのプロパティを設定する
		if(convClassProps != null){
			for(PropertyDesc convPropDesc : convClassProps.keySet()){
				convPropDesc.setValue(convertClassInstance, convClassProps.get(convPropDesc));
			}
		}
		
		Object convInstance = convertClassInstance == null ? getContext().getEntity() : convertClassInstance;
		
		return MethodUtil.invoke(convertMethod,
				convInstance,
				new Object[]{o});
	}

}
