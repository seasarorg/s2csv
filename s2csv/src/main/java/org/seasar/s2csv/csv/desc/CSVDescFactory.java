package org.seasar.s2csv.csv.desc;

/**
 * CSVの設定を生成します
 * @author newta
 */
public interface CSVDescFactory {
	/**
	 * @param csvClass
	 * @return csv設定
	 */
	CSVEntityDesc createCSVEntityDesc(Class<?> csvClass);
}
