package org.seasar.s2csv.csv.desc;

import org.seasar.framework.beans.PropertyDesc;

/**
 * CSVカラム設定
 * @author newta
 */
public class CSVColumnDesc {

	private PropertyDesc columnDesc;
	private boolean quote;
	private String colomnName;
	private int columnIndex;
	
	/**
	 * @return columnDesc
	 */
	public PropertyDesc getColumnDesc() {
		return columnDesc;
	}
	/**
	 * @param columnDesc
	 */
	public void setColumnDesc(PropertyDesc columnDesc) {
		this.columnDesc = columnDesc;
	}
	
	/**
	 * @return quote
	 */
	public boolean isQuote() {
		return quote;
	}
	/**
	 * @param quote
	 */
	public void setQuote(boolean quote) {
		this.quote = quote;
	}
	/**
	 * @return colomnName
	 */
	public String getColomnName() {
		return colomnName;
	}
	/**
	 * @param colomnName
	 */
	public void setColomnName(String colomnName) {
		this.colomnName = colomnName;
	}
	/**
	 * @return columnIndex
	 */
	public int getColumnIndex() {
		return columnIndex;
	}
	/**
	 * @param columnIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
}
