package org.seasar.s2csv.csv.command;

import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;

/**
 * バリデーション、コンバートを実行するコマンドです。
 * @author newta
 */
public interface S2CSVCommand {
	
	/**
	 * 行チェックを行うCommandをCommandMapから取り出すのキーです。
	 */
	String RECODE = S2CSVCommand.class.getName() + "_RECODE" ;

	/**
	 * エンティティ変換後チェックを行うCommandをCommandMapから取り出すのキーです。
	 */
	String RECODE_AFTER = S2CSVCommand.class.getName() + "_RECODE_AFTER" ;

	/**
	 * CSVValidationExceptionで変換が中断された際の
	 * defaultのメッセージキーを設定します。
	 * @param errorMsgKey
	 */
	void setErrorMsgKey(String errorMsgKey);
	
	/**
	 * CSVValidationExceptionで変換が中断された際の
	 * defaultのメッセージの値を設定します。
	 * @param errorMsgValues
	 */
	void setErrorMsgValues(Object[] errorMsgValues);

	/**
	 * CSVValidationExceptionで変換が中断された際の
	 * defaultのメッセージキーを取得します。
	 * @return errorMsgKey
	 */
	String getErrorMsgKey();

	/**
	 * CSVValidationExceptionで変換が中断された際の
	 * defaultのメッセージの値を取得します。
	 * @return errorMsgValues
	 */
	Object[] getErrorMsgValues();
	
	/**
	 * コマンドを実行します
	 * 
	 * @param o コマンドの処理対象のオブジェクトをセットします。
	 * 			カラムチェックのときは
	 * 				toCSVのときはフィールドのオブジェクトデータ
	 * 				toObjのときはCSVカラムの文字列<String>
	 * 			行チェックのときは
	 * 				toCSVのときはエンティティのオブジェクトデータ<CSVEntityのアノテーションのついているクラス>
	 * 				toObjのときはCSVカラムの文字列の配列<String[]>
	 * @return コマンドの処理結果オブジェクト
	 * @throws CSVValidationException エラーが起きたときに設定します。
	 */
	Object execute(Object o) throws CSVValidationException;

	/**
	 * 各Commandで共有されるContextがセットされます。
	 * @param context
	 */
	void setContext(S2CSVCommandContext context);

}
