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
	
	/** 
	 * カラムの設定
	 */
	private List<CSVColumnDesc> columnConfigs;

	/**
	 * CSV行全体に対するチェックやエンティティに対するチェックの設定
	 * (相関チェック等)
	 */
	private List<CSVValidateDesc> recordValidate;
	
	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public List<CSVColumnDesc> getColumnConfigs() {
		return this.columnConfigs;
	}

	public void setColumnConfigs(List<CSVColumnDesc> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}
	

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public void setHeaderNames(String[] headerNames) {
		this.headerNames = headerNames;
	}

	public String[] getHeaderNames(){
		return this.headerNames;
	}
	
	public void setColmunSize(int colmunSize) {
		this.colmunSize = colmunSize;
	}
	
	public int getColmunSize(){
		return this.colmunSize;
	}

	public List<CSVValidateDesc> getRecordValidate() {
		return recordValidate;
	}

	public void setRecordValidate(List<CSVValidateDesc> recordValidate) {
		this.recordValidate = recordValidate;
	}
}
