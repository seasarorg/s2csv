package org.seasar.s2csv.csv.convertor;

/**
 * CSVのカラムデータから変換を行います
 * @author newta
 */
public interface CSVColumnConvertor {

	/**
	 * CSVカラム文字列からオブジェクトに変換します
	 * @param column
	 * @return 変換したオブジェクト
	 * @throws Exception
	 */
	public Object getAsObject(String column) throws Exception;
	
	/**
	 * オブジェクトからCSVカラム文字列に変換します
	 * @param o
	 * @return 変換した文字列
	 * @throws Exception
	 */
	public String getAsString(Object o) throws Exception;
}
