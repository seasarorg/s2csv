package org.seasar.s2csv.csv.factory;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.seasar.s2csv.csv.desc.CSVEntityDesc;

/**
 * S2CSVCtrlFactoryに引き渡されるCSVの基本定義データです。
 * @author newta
 */
public class CSVFactoryConfigContext {

	private Map<String,Object> config;
	private Reader reader;
	private Writer writer;
	private CSVEntityDesc desc;
	
	/**
	 * インスタンスを生成します。
	 */
	public CSVFactoryConfigContext(){
		config = new HashMap<String,Object>();
	}
	
	/**
	 * コンフィグにパラメータを追加します
	 * @param key
	 * @param o
	 */
	public void putConfig(String key,Object o){
		config.put(key,o);
	}
	
	/**
	 * コンフィグから値を取得します
	 * @param key
	 * @return value
	 */
	public Object getConfig(String key){
		return config.get(key);
	}
	/**
	 * @param reader
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * @param writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * @return reader
	 */
	public Reader getReader(){
		return reader;
	}

	/**
	 * @return writer
	 */
	public Writer getWriter(){
		return writer;
	}
	
	/**
	 * CSVEntityの設定
	 * @param desc
	 */
	public void setCSVEntityDesc(CSVEntityDesc desc) {
		this.desc = desc;
	}

	/**
	 * @return CSVEntityの設定
	 */
	public CSVEntityDesc getCSVEntityDesc(){
		return desc;
	}
}
