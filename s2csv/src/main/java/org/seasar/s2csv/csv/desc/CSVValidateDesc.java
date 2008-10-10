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
	
	
	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public Object[] getMsgArgs() {
		return msgArgs;
	}

	public void setMsgArgs(Object[] msgArgs) {
		this.msgArgs = msgArgs;
	}

	public Object[] getValidateMethodArgs() {
		return validateMethodArgs;
	}

	public void setValidateMethodArgs(Object[] validateMethodArgs) {
		this.validateMethodArgs = validateMethodArgs;
	}

	public Method getValidateMethod() {
		return validateMethod;
	}

	public void setValidateMethod(Method validateMethod) {
		this.validateMethod = validateMethod;
	}

	public boolean isEntityVadlidateMethod() {
		return entityVadlidateMethod;
	}

	public void setEntityVadlidateMethod(boolean entityVadlidateMethod) {
		this.entityVadlidateMethod = entityVadlidateMethod;
	}
	
}
