package org.seasar.s2csv.csv.factory.impl;

import java.io.Reader;
import java.io.Writer;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.convertor.CSVConvertCtrl;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.factory.CSVFactoryConfigContext;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.factory.S2CSVParserFactory;
import org.seasar.s2csv.csv.factory.S2CSVWriterFactory;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.CSVWriter;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;
import org.seasar.s2csv.csv.validator.CSVValidateCtrl;

/**
 * Parse、WriteやValidation、Convertをコントロールするインスタンスを生成するファクトリです。
 * @author newta
 */
public class S2CSVCtrlFactoryImpl implements S2CSVCtrlFactory {

	@SuppressWarnings("unchecked")
	public <T> S2CSVParseCtrl<T> getParseController(
			Class<T> entityClass, CSVParser parser){
		
		if(entityClass == null || parser == null){
			//TODO メッセージをつける
			throw new IllegalArgumentException();
		}
		
		S2CSVParseCtrl<T> csvpc = SingletonS2Container
									.getComponent(S2CSVParseCtrl.class);
		
		CSVEntityDesc csvEntityDesc = S2CSVDescUtil.getCSVEntityDesc(entityClass);
		
		if(csvEntityDesc == null){
			//TODO メッセージをつける
			throw new RuntimeException("null csv entity desc[" + entityClass.getName() + "]");
		}

		CSVConvertCtrl maker = new CSVConvertCtrl(csvEntityDesc);
		CSVValidateCtrl validator = new CSVValidateCtrl(csvEntityDesc);
		
		csvpc.setCSVEntityDesc(csvEntityDesc);
		csvpc.setCSVParser(parser);
		csvpc.setCSVMaker(maker);
		csvpc.setCSVValidator(validator);
		csvpc.setValidateFlag(true);
		
		return csvpc;
	}
	

	public <T> S2CSVParseCtrl<T> getParseController(
			Class<T> entityClass, Reader reader){
		CSVEntityDesc csvEntityDesc = S2CSVDescUtil.getCSVEntityDesc(entityClass);
		
		S2CSVParserFactory parserFactory = SingletonS2Container.getComponent(S2CSVParserFactory.class);
		CSVFactoryConfigContext config = SingletonS2Container.getComponent(CSVFactoryConfigContext.class);
		
		config.setCSVEntityDesc(csvEntityDesc);
		config.setReader(reader);
		
		CSVParser parser =  parserFactory.creatCSVParser(config);
		
		return getParseController(entityClass, parser);
	}
	

	@SuppressWarnings("unchecked")
	public <T> S2CSVWriteCtrl<T> getWriteController(
			Class<T> entityClass, CSVWriter writer){

		
		if(entityClass == null || writer == null){
			//TODO メッセージをつける
			throw new IllegalArgumentException();
		}
		
		S2CSVWriteCtrl<T> csvwc = SingletonS2Container.getComponent(S2CSVWriteCtrl.class);
		
		CSVEntityDesc csvEntityDesc = S2CSVDescUtil.getCSVEntityDesc(entityClass);
		
		if(csvEntityDesc == null){
			//TODO メッセージをつける
			throw new RuntimeException("null csv entity desc[" + entityClass.getName() + "]");
		}

		CSVConvertCtrl maker = new CSVConvertCtrl(csvEntityDesc);
		CSVValidateCtrl validator = new CSVValidateCtrl(csvEntityDesc);

		csvwc.setCSVEntityDesc(csvEntityDesc);
		csvwc.setCSVWriter(writer);
		csvwc.setCSVMaker(maker);
		csvwc.setCSVValidator(validator);
		csvwc.setValidateFlag(false);
		
		return csvwc;
	}
	

	public <T> S2CSVWriteCtrl<T> getWriteController(
			Class<T> entityClass, Writer writer){
		CSVEntityDesc csvEntityDesc = S2CSVDescUtil.getCSVEntityDesc(entityClass);

		S2CSVWriterFactory writerFactory = SingletonS2Container.getComponent(S2CSVWriterFactory.class);
		CSVFactoryConfigContext config = SingletonS2Container.getComponent(CSVFactoryConfigContext.class);
		
		config.setCSVEntityDesc(csvEntityDesc);
		config.setWriter(writer);
		
		CSVWriter csvwriter =  writerFactory.creatCSVWriter(config);
		
		return getWriteController(entityClass, csvwriter);
	}
}
