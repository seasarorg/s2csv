package org.seasar.s2csv.csv.message;



/**
 * バリデーション結果メッセージ情報
 */
public class CSVMsg {

	private String key;
	private Object[] args;
	
	public String toString(){
		return CSVMessageFormatter.getSimpleMessage(getMsgKey(), getMsgArgs());
	}

	/**
	 * message key
	 * @param key
	 */
	public void setMsgKey(String key) {
		this.key = key;
	}

	/**
	 * @return message key
	 */
	public String getMsgKey() {
		return key;
	}

	/**
	 * message args
	 * @param args
	 */
	public void setMsgArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * @return message args
	 */
	public Object[] getMsgArgs() {
		return args;
	}
}
