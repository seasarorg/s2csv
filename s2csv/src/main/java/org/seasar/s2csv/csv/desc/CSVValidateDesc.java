package org.seasar.s2csv.csv.desc;

import java.lang.reflect.Method;

/**
 * バリデーションの設定詳細
 * @author newta
 */
public class CSVValidateDesc {

	/** エンティティのインスタンスのvalidateメソッドを呼ぶかどうか */
	private boolean entityVadlidateMethod;
	/** バリデーションメソッド */
	private Method validateMethod;
	/** バリデーション引数 先頭データはCSVカラム文字になるため、可変 */
	private Object[] validateMethodArgs;

	/** バリデーションのエラーメッセージキー */
	private String msgKey;
	
	/** バリデーションのエラーメッセージの引数 */
	private Object[] msgArgs;
	
	/**
	 * @return msgKey
	 */
	public String getMsgKey() {
		return msgKey;
	}
	/**
	 * @param msgKey
	 */
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	/**
	 * @return msgArgs
	 */ 
	public Object[] getMsgArgs() {
		return msgArgs;
	}
	/**
	 * @param msgArgs
	 */
	public void setMsgArgs(Object[] msgArgs) {
		this.msgArgs = msgArgs;
	}
	/**
	 * @return validateMethodArgs
	 */
	public Object[] getValidateMethodArgs() {
		return validateMethodArgs;
	}
	/**
	 * @param validateMethodArgs
	 */
	public void setValidateMethodArgs(Object[] validateMethodArgs) {
		this.validateMethodArgs = validateMethodArgs;
	}
	/**
	 * @return validateMethod
	 */
	public Method getValidateMethod() {
		return validateMethod;
	}
	/**
	 * @param validateMethod
	 */
	public void setValidateMethod(Method validateMethod) {
		this.validateMethod = validateMethod;
	}
	/**
	 * @return entityVadlidateMethod
	 */
	public boolean isEntityVadlidateMethod() {
		return entityVadlidateMethod;
	}
	/**
	 * @param entityVadlidateMethod
	 */
	public void setEntityVadlidateMethod(boolean entityVadlidateMethod) {
		this.entityVadlidateMethod = entityVadlidateMethod;
	}
	
}
