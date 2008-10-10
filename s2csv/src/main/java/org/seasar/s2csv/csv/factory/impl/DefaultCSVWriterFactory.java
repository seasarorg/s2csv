package org.seasar.s2csv.csv.factory.impl;

import org.seasar.s2csv.csv.factory.CSVFactoryConfigContext;
import org.seasar.s2csv.csv.factory.S2CSVWriterFactory;
import org.seasar.s2csv.csv.io.CSVWriter;
import org.seasar.s2csv.csv.io.DefaultCSVWriter;

/**
 * S2CSVで使用するDefaultCSVWriterを生成する、標準のファクトリです。
 * @author newta
 */
public class DefaultCSVWriterFactory implements S2CSVWriterFactory {

	public CSVWriter creatCSVWriter(CSVFactoryConfigContext context){
				
		return new DefaultCSVWriter(context.getWriter(),
				context.getCSVEntityDesc());
	}
}
