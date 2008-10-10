package org.seasar.s2csv.csv.io;

/**
 * CSVの書き込み処理を行う
 * @author newta
 */
public interface CSVWriter {
	
	/**
	 * CSVデータを書き出します。
	 * @param csvColumns カラムデータ
	 */
	public void writeLine(String[] csvColumns);

	/**
	 * クローズ処理を行います。
	 */
	public void close();
}
