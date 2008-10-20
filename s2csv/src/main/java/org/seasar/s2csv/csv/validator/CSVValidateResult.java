package org.seasar.s2csv.csv.validator;

import java.util.ArrayList;
import java.util.List;

import org.seasar.s2csv.csv.message.CSVMsg;

/**
 * バリデーション結果情報
 * @author newta
 */
public class CSVValidateResult {

	/** 発生行番号 */
	private long lineNo;
	/** 発生行データ */
	private String[] data;
	/** バリデーションメッセージ */
	private List<CSVMsg> msgs = new ArrayList<CSVMsg>();
	
	/**
	 * @return lineNo
	 */
	public long getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo
	 */
	public void setLineNo(long lineNo) {
		this.lineNo = lineNo;
	}
	/**
	 * @return data
	 */
	public String[] getData() {
		return data;
	}
	/**
	 * @param data
	 */
	public void setData(String[] data) {
		this.data = data;
	}
	/**
	 * @return msgs
	 */
	public List<CSVMsg> getMsgs() {
		return msgs;
	}
	/**
	 * @param msgs
	 */
	public void setMsgs(List<CSVMsg> msgs) {
		this.msgs = msgs;
	}
	/**
	 * @return data
	 */
	public String dataToString(){
		StringBuilder sb = new StringBuilder();
		for(String tmp: data){
			sb.append(tmp);
			sb.append("\t");
		}
		
		return sb.toString();
	}
}
