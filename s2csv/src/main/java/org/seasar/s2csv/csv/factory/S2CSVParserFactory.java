package org.seasar.s2csv.csv.factory;

import org.seasar.s2csv.csv.io.CSVParser;

/**
 * CSVパースするクラスのファクトリです。
 * @author newta
 */
public interface S2CSVParserFactory {

	/**
	 * CSVParserを生成します
	 * @param context
	 * @return CSVParser
	 */
	public CSVParser creatCSVParser(CSVFactoryConfigContext context);
}
