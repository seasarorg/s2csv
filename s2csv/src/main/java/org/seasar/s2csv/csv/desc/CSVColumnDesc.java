package org.seasar.s2csv.csv.desc;

import java.util.List;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;

/**
 * CSVカラム設定
 * @author newta
 */
public class CSVColumnDesc {

	private CSVColumn csvColumn;
	
	private PropertyDesc columnDesc;
	
	private List<CSVValidateDesc> validateList;
	private CSVConvertDesc convert;

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
	 * 
	 * @return convert
	 */
	public CSVConvertDesc getConvert() {
		return convert;
	}
	/**
	 * 
	 * @param convert
	 */
	public void setConvert(CSVConvertDesc convert) {
		this.convert = convert;
	}
	
	/**
	 * カラムインデックスを取得するエイリアス
	 * @return index
	 */
	public int getColumnIndex(){
		return this.getCsvColumn().columnIndex();
	}
	/**
	 * 
	 * @return csvColumn
	 */
	public CSVColumn getCsvColumn() {
		return csvColumn;
	}
	/**
	 * 
	 * @param csvColumn
	 */
	public void setCsvColumn(CSVColumn csvColumn) {
		this.csvColumn = csvColumn;
	}
	
	/**
	 * @return quote
	 */
	public boolean quote(){
		return this.csvColumn.quote();
	}

	/**
	 * @return the validateList
	 */
	public List<CSVValidateDesc> getValidateList() {
		return validateList;
	}

	/**
	 * @param validateList the validateList to set
	 */
	public void setValidateList(List<CSVValidateDesc> validateList) {
		this.validateList = validateList;
	}
}
