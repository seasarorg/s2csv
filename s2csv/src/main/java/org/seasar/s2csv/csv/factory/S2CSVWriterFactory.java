package org.seasar.s2csv.csv.factory;

import org.seasar.s2csv.csv.io.CSVWriter;

/**
 * CSVライトするクラスのファクトリです。
 * @author newta
 */
public interface S2CSVWriterFactory {

	/**
	 * CSVWriterを生成します
	 * @param context
	 * @return CSVWriter
	 */
	public CSVWriter creatCSVWriter(CSVFactoryConfigContext context);
}
