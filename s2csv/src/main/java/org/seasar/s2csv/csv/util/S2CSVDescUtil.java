package org.seasar.s2csv.csv.util;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.desc.CSVDescPool;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;

/**
 * CSVの設定関連Util
 * @author newta
 */
public class S2CSVDescUtil {

	private S2CSVDescUtil(){
	}
	
	/**
	 * CSVエンティティの設定をプールから取得します
	 * @param csvEntityClass
	 * @return CSVエンティティの設定
	 */
	public static CSVEntityDesc getCSVEntityDesc(Class<?> csvEntityClass){
		
		return getDescPool().getCSVEntityDesc(csvEntityClass);
	}
	
	/**
	 * CSV設定プールを取得します
	 * @return CSV設定プール
	 */
	public static CSVDescPool getDescPool(){
		return SingletonS2Container.getComponent(CSVDescPool.class);
	}
}
