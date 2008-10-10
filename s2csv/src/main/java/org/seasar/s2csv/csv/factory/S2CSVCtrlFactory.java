package org.seasar.s2csv.csv.factory;

import java.io.Reader;
import java.io.Writer;

import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.CSVWriter;

/**
 * CSVコントローラを生成するクラス
 * @author newta
 */
public interface S2CSVCtrlFactory {

	/**
	 * Parseコントローラを生成します
	 * @param <T>
	 * @param entityClass
	 * @param parser
	 * @return Parseコントローラ
	 */
	public <T> S2CSVParseCtrl<T> getParseController(
			Class<T> entityClass, CSVParser parser);
	

	/**
	 * Parseコントローラを生成します
	 * @param <T>
	 * @param entityClass
	 * @param reader
	 * @return Parseコントローラ
	 */
	public <T> S2CSVParseCtrl<T> getParseController(
			Class<T> entityClass, Reader reader);
	
	/**
	 * Writeコントローラを生成します
	 * @param <T>
	 * @param entityClass
	 * @param writer
	 * @return Writeコントローラ
	 */
	public <T> S2CSVWriteCtrl<T> getWriteController(
			Class<T> entityClass, CSVWriter writer);
	
	/**
	 * Writeコントローラを生成します
	 * @param <T>
	 * @param entityClass
	 * @param writer
	 * @return Writeコントローラ
	 */
	public <T> S2CSVWriteCtrl<T> getWriteController(
			Class<T> entityClass, Writer writer);
}
