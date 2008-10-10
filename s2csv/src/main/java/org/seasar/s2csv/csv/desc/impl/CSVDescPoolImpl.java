package org.seasar.s2csv.csv.desc.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.s2csv.csv.desc.CSVDescFactory;
import org.seasar.s2csv.csv.desc.CSVDescPool;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;

/**
 * CSVエンティティの設定プーリング実装
 * @author newta
 */
public class CSVDescPoolImpl implements CSVDescPool {

	private Map<Class<?>,CSVEntityDesc> cache;
	
	/**
	 * CSV設定情報作成ファクトリ
	 */
	public CSVDescFactory csvdescFactory;
	
	/**
	 * 
	 */
	public CSVDescPoolImpl(){
		this.cache = new HashMap<Class<?>,CSVEntityDesc>();
	}
	
	public void registCSVEntityDesc(CSVEntityDesc desc) {
		this.cache.put(desc.getEntityClass(), desc);
	}

	public CSVEntityDesc getCSVEntityDesc(Class<?> clazz) {
		
		CSVEntityDesc desc = this.cache.get(clazz);

		if(desc == null){
			//CSVエンティティの設定を作成します
			desc = csvdescFactory.createCSVEntityDesc(clazz);
			registCSVEntityDesc(desc);
		}
		
		return desc;
	}

	public void clear() {
		this.cache.clear();
	}
	


}
