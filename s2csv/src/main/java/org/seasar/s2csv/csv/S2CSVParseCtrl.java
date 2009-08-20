package org.seasar.s2csv.csv;

import java.util.List;
import java.util.Map;

import org.seasar.s2csv.csv.command.CSVCommandInvoker;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * CSVファイル形式のデータをオブジェクト形式に変換する処理のコントロールを行います
 * @author newta
 * @param <T> 
 */
public interface S2CSVParseCtrl<T> {

	/**
	 * default: false
	 * 
	 * trueの場合:
	 * バリデーション、コンバートエラーが起きた際に
	 * CSVValidationResultRuntimeExceptionをthrowします。
	 * 
	 * falseの場合:
	 * エラーが起きてもExceptionをthrowしません。
	 * parseメソッドではnullが帰りますが、処理は終っておらず、
	 * getValidationResultで状態を取得する必要があります。
	 * @param flag
	 */
	void setExceptionThrow(boolean flag);
	
	/**
	 * Parseをコントロールするインスタンスをセットします。
	 * @param parser
	 */
	void setCSVParser(CSVParser parser);
	
	/**
	 * コンバート、バリデーションのコマンドを実行するクラスをセットします。
	 * @param commandInvoker
	 */
	void setCSVCommandInvoker(CSVCommandInvoker commandInvoker);
	
	/**
	 * CSVエンティティの設定をセットします。
	 * @param csvEntityDesc
	 */
	void setCSVEntityDesc(CSVEntityDesc csvEntityDesc);

	/**
	 * CSVエンティティの設定を取得します。
	 * @return csvEntityDesc
	 */
	CSVEntityDesc getCSVEntityDesc();
	
	/**
	 * オブジェクトに変換する際のバリデーション、コンバートのコマンドを設定します。
	 * @param commandMap キー:property名
	 */
	void setCommandMap(Map<String,List<S2CSVCommand>> commandMap);
	
	/**
	 * @param propertyName ターゲットのプロパティ名
	 * @return 指定のプロパティに設定されたコマンドを返します。
	 */
	List<S2CSVCommand> getCommands(String propertyName);
	
	/**
	 * 次の行を読み込みます。
	 * @return true:次の行あり false:次の行なし close()が自動で呼び出されます
	 */
	boolean readNext();
	
	/**
	 * カレントの行に対してパース処理します。
	 * バリデーション、コンバートでエラーが発生するとnullを返します。
	 * @return 結果オブジェクト バリデーション、コンバートでエラーが発生すると:null
	 * @throws CSVValidationResultRuntimeException バリデーションエラーがあったときに投げられます。
	 * @throws IndexOutOfBoundsException 最終行を呼び出した後に呼び出されると発生します
	 */
	T parse() throws CSVValidationResultRuntimeException,IndexOutOfBoundsException;
	
	/**
	 * 全てのデータをパースします
	 * 自動的にclose()が呼び出され、
	 * これ以降処理できなくなります。
	 * (エラーで処理を中断した場合もcloseが呼ばれます。)
	 * @return 結果オブジェクトリスト
 	 * @throws CSVValidationResultException 全てのデータのパース後 validation エラーがあった場合呼び出されます
	 */
	List<T> parseAll() throws CSVValidationResultException;

	/**
	 * 全てのデータをパースします
	 * 自動的にclose()が呼び出され、
	 * これ以降処理できなくなります。
	 * (エラーで処理を中断した場合もcloseが呼ばれます。)
	 * @param errorLimitCount エラー発生レコード数の最大値を設定します。
	 * 							0以下に指定するとlimitなしとなります。
	 * 							1以上を設定したときその件数分バリデーションエラー等が起こった場合に
	 * 							処理を終了します。
	 * 							エラー件数に達したときの動作はsetExceptionThrowで設定された値により変わります。
	 * 							trueの場合 :CSVValidationResultExceptionがthrowされます。
	 * 							falseの場合:エラーに達するまでに変換できたデータを返します。
	 * 										（エラー内容を取得するにはgetValidationResultAllメソッドを使用してください）
	 * @return 結果オブジェクトリスト
 	 * @throws CSVValidationResultException 全てのデータのパース後 validation エラーがあった場合呼び出されます
	 */
	List<T> parseAll(int errorLimitCount) throws CSVValidationResultException;

	/**
	 * parseのバリデーション、直近のコンバート処理で起こった結果を返します。
	 * @return null:正常  null以外:バリデーションエラー結果
	 */
	CSVValidateResult getParseValidationResult();

	/**
	 * parseのバリデーション、コンバートで行った処理で起こった結果をすべて返します。
	 * @return null:正常  null以外:全てのバリデーションエラー結果
	 */
	List<CSVValidateResult> getValidationResultAll();
	
	/**
	 * CSVParserにセットされているストリームを閉じます。
	 */
	void close();
}
