package org.seasar.s2csv.csv.util;

import java.util.List;
import java.util.Map;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.desc.CSVDescPool;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;

/**
 * CSVの設定関連Util
 * @author newta
 */
public class S2CSVDescUtil {

	protected S2CSVDescUtil(){
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
	 * objectからcsvにバリデーション、コンバートするときに使用するコマンドを取得します。
	 * @param csvEntityClass
	 * @return commands
	 */
	public static Map<String, List<S2CSVCommand>> getToCsvCommands(Class<?> csvEntityClass){
		return getDescPool().getToCsvCommands(csvEntityClass);
	}

	/**
	 * csvからobjectにバリデーション、コンバートするときに使用するコマンドを取得します。
	 * @param csvEntityClass
	 * @return commands
	 */
	public static Map<String, List<S2CSVCommand>> getToObjCommands(Class<?> csvEntityClass){
		return getDescPool().getToObjCommands(csvEntityClass);
	}
	
	/**
	 * CSV設定プールを取得します
	 * @return CSV設定プール
	 */
	public static CSVDescPool getDescPool(){
		return SingletonS2Container.getComponent(CSVDescPool.class);
	}
}
