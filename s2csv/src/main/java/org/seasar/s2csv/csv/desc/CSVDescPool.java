package org.seasar.s2csv.csv.desc;

/**
 * CSVEntityの設定をプーリングします
 * @author newta
 */
public interface CSVDescPool {

	/** 
	 * CSVエンティティの設定を取得します
	 * @param clazz
	 * @return CSVエンティティの設定
	 */
	CSVEntityDesc getCSVEntityDesc(Class<?> clazz);
	
	/**
	 * CSVエンティティの設定を登録します
	 * @param desc 設定
	 */
	void registCSVEntityDesc(CSVEntityDesc desc);
	
	/**
	 * 設定をクリアします
	 */
	void clear();
}
