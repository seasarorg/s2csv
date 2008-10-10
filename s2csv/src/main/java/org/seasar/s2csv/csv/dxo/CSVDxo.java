package org.seasar.s2csv.csv.dxo;

/**
 * CSVエンティティからオブジェクトに変換するときに使用します。
 * @author newta
 */
public interface CSVDxo {

	/**
	 * 変換
	 * @param <T>
	 * @param c
	 * @param o
	 * @return 変換結果
	 */
	<T> T dxo(Class<T> c,Object o);
}
