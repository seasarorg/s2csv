package org.seasar.s2csv.csv.desc;

import java.util.List;
import java.util.Map;

import org.seasar.s2csv.csv.command.S2CSVCommand;

/**
 * CSVEntityの設定をプーリングします
 * @author newta
 */
public interface CSVDescPool {

	/** 
	 * CSVエンティティの設定を取得します
	 * @param clazz
	 * @return CSVエンティティの設定
	 */
	CSVEntityDesc getCSVEntityDesc(Class<?> clazz);
	
	/**
	 * CSVエンティティの設定を登録します
	 * @param desc 設定
	 */
	void registCSVEntityDesc(CSVEntityDesc desc);
	
	/**
	 * 設定をクリアします
	 */
	void clear();

	/**
	 * objectからcsvにするときに使用するバリデーション、コンバートのコマンドを取得します。
	 * @param csvEntityClass
	 * @return commands
	 */
	Map<String, List<S2CSVCommand>> getToCsvCommands(Class<?> csvEntityClass);

	/**
	 * csvからobjectにするときに使用するバリデーション、コンバートのコマンドを取得します。
	 * @param csvEntityClass
	 * @return commands
	 */
	Map<String, List<S2CSVCommand>> getToObjCommands(Class<?> csvEntityClass);
}
