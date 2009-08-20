package org.seasar.s2csv.csv;

import java.util.List;
import java.util.Map;

import org.seasar.s2csv.csv.command.CSVCommandInvoker;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.io.CSVWriter;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * オブジェクト形式のデータをCSVファイル形式のデータに書き出す変換のコントロールを行います
 * @author newta
 * @param <T> 
 */
public interface S2CSVWriteCtrl<T> {

	/**
	 * trueの場合:
	 * バリデーション、コンバートエラーが起きた際に
	 * CSVValidationResultRuntimeExceptionをthrowします。
	 * 
	 * falseの場合:
	 * エラーが起きてもExceptionをthrowしません。
	 * parseメソッドではnullが帰りますが、処理は終っておらず、
	 * getValidationResultで状態を取得する必要があります。
	 * @param b
	 */
	void setExceptionThrow(boolean b);
	
	/**
	 * コンバート、バリデーションのコマンドを実行するクラスをセットします。
	 * @param commandInvoker
	 */
	void setCSVCommandInvoker(CSVCommandInvoker commandInvoker);
	
	/**
	 * CSVWriterをコントロールするインスタンスをセットします。
	 * @param writer
	 */
	void setCSVWriter(CSVWriter writer);

	/**
	 * CSVエンティティの設定をセットします。
	 * @param csvEntityDesc
	 */
	void setCSVEntityDesc(CSVEntityDesc csvEntityDesc);
	
	/**
	 * オブジェクトに変換する際のバリデーション、コンバートのコマンドを設定します。
	 * @param commandMap 
	 */
	void setCommandMap(Map<String,List<S2CSVCommand>> commandMap);
	
	/**
	 * @return オブジェクトに変換する際のバリデーション、コンバートのコマンドを返します。
	 */
	Map<String,List<S2CSVCommand>> getCommandMap();
	
	/**
	 * オブジェクトをCSV行として書き出します
	 * @param o
	 * @throws CSVValidationResultRuntimeException 
	 */
	void write(T o) throws CSVValidationResultRuntimeException;
	
	/**
	 * オブジェクトリストをCSV行として書き出します。
	 * @param list
	 * @throws CSVValidationResultException 
	 */
	void writeAll(List<T> list) throws CSVValidationResultException;

	/**
	 * writeのバリデーション、コンバートで最後に行った処理で起こった結果を返します。
	 * @return null:正常  null以外:バリデーションエラー結果
	 */
	CSVValidateResult getWriteValidationResult();

	/**
	 * writeのバリデーション、コンバートで行った処理で起こった結果をすべて返します。
	 * @return null:正常  null以外:全てのバリデーションエラー結果
	 */
	List<CSVValidateResult> getValidationResultAll();
	
	/**
	 * CSVWriterにセットされたストリームを閉じます
	 */
	void close();
}
