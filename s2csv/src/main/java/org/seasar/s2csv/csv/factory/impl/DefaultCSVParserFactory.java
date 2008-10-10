package org.seasar.s2csv.csv.factory.impl;

import org.seasar.s2csv.csv.factory.CSVFactoryConfigContext;
import org.seasar.s2csv.csv.factory.S2CSVParserFactory;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.DefaultCSVParser;

/**
 * S2CSVで使用するDefaultCSVParserを生成する、標準のファクトリです。
 * @author newta
 */
public class DefaultCSVParserFactory implements S2CSVParserFactory {

	public CSVParser creatCSVParser(CSVFactoryConfigContext context){
		
		return new DefaultCSVParser(context.getReader(),
				context.getCSVEntityDesc());
	}
}
