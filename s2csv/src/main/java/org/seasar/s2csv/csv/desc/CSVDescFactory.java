package org.seasar.s2csv.csv.desc;

import java.util.List;
import java.util.Map;

import org.seasar.s2csv.csv.command.S2CSVCommand;

/**
 * CSVの設定を生成します
 * @author newta
 */
public interface CSVDescFactory {
	/**
	 * @param csvClass
	 * @return csv設定
	 */
	CSVEntityDesc createCSVEntityDesc(Class<?> csvClass);

	/**
	 * csvに変換、バリデーションするときのコマンドをcsvEntityClassから作成します。
	 * @param csvEntityClass
	 * @return 変換コマンド
	 */
	Map<String, List<S2CSVCommand>> createToCsvCommands(Class<?> csvEntityClass);

	/**
	 * csvからオブジェクトに変換、バリデーションするときのコマンドをcsvEntityClassから作成します。
	 * @param csvEntityClass
	 * @return 変換コマンド
	 */
	Map<String, List<S2CSVCommand>> createToObjCommands(Class<?> csvEntityClass);
}
