package org.seasar.s2csv.csv.desc;

import java.util.List;


/**
 * CSVエンティティ設定
 * @author newta
 */
public class CSVEntityDesc {

	private Class<?> entityClass;
	private int colmunSize;
	private String[] headerNames;
	private String[] fieldNames;
	private boolean header;
	
	private boolean checkColumnSize;
	private boolean checkHeader;
	
	private char demiliter;
	
	/** 
	 * カラムの設定
	 */
	private List<CSVColumnDesc> columnConfigs;

	/**
	 * CSV行全体に対するチェックやエンティティに対するチェックの設定
	 * (相関チェック等)
	 */
	private List<CSVValidateDesc> recordValidate;
	
	/**
	 * @return fieldNames
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}
	/**
	 * @param fieldNames
	 */
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}
	/**
	 * @return entityClass
	 */
	public Class<?> getEntityClass() {
		return entityClass;
	}
	/**
	 * @param entityClass
	 */
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	/**
	 * @return columnConfigs
	 */
	public List<CSVColumnDesc> getColumnConfigs() {
		return this.columnConfigs;
	}
	/**
	 * @param columnConfigs
	 */
	public void setColumnConfigs(List<CSVColumnDesc> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}
	
	/**
	 * @return header
	 */
	public boolean isHeader() {
		return header;
	}
	/**
	 * @param header
	 */
	public void setHeader(boolean header) {
		this.header = header;
	}
	/**
	 * @param headerNames
	 */
	public void setHeaderNames(String[] headerNames) {
		this.headerNames = headerNames;
	}
	/**
	 * @return headerNames
	 */
	public String[] getHeaderNames(){
		return this.headerNames;
	}
	/**
	 * @param colmunSize
	 */
	public void setColmunSize(int colmunSize) {
		this.colmunSize = colmunSize;
	}
	/**
	 * @return colmunSize
	 */
	public int getColmunSize(){
		return this.colmunSize;
	}
	/**
	 * @return recordValidate
	 */
	public List<CSVValidateDesc> getRecordValidate() {
		return recordValidate;
	}
	/**
	 * @param recordValidate
	 */
	public void setRecordValidate(List<CSVValidateDesc> recordValidate) {
		this.recordValidate = recordValidate;
	}
	/**
	 * @return the checkColumnSize
	 */
	public boolean isCheckColumnSize() {
		return checkColumnSize;
	}
	/**
	 * @param checkColumnSize the checkColumnSize to set
	 */
	public void setCheckColumnSize(boolean checkColumnSize) {
		this.checkColumnSize = checkColumnSize;
	}
	/**
	 * @return the checkHeader
	 */
	public boolean isCheckHeader() {
		return checkHeader;
	}
	/**
	 * @param checkHeader the checkHeader to set
	 */
	public void setCheckHeader(boolean checkHeader) {
		this.checkHeader = checkHeader;
	}
	/**
	 * @return the demiliter
	 */
	public char getDemiliter() {
		return demiliter;
	}
	/**
	 * @param demiliter the demiliter to set
	 */
	public void setDemiliter(char demiliter) {
		this.demiliter = demiliter;
	}
}
