package org.seasar.s2csv.csv;

import java.util.List;

import org.seasar.s2csv.csv.convertor.CSVConvertCtrl;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.validator.CSVValidateCtrl;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * CSVファイル形式のデータをオブジェクト形式に変換するコントロールを行います
 * @author newta
 * @param <T> 
 */
public interface S2CSVParseCtrl<T> {

	/**
	 * Parseをコントロールするインスタンスをセットします。
	 * @param parser
	 */
	void setCSVParser(CSVParser parser);
	/**
	 * CSVエンティティの設定をセットします。
	 * @param csvEntityDesc
	 */
	void setCSVEntityDesc(CSVEntityDesc csvEntityDesc);
	/**
	 * コンバートとCSV行作成をコントロールするクラスをセットします。
	 * @param maker
	 */
	void setCSVMaker(CSVConvertCtrl maker);
	
	/**
	 * バリデーションをコントロールするクラスをセットします。
	 * @param validator
	 */
	void setCSVValidator(CSVValidateCtrl validator);
	
	/**
	 * パース時にバリデートを行います
	 * 初期値 true
	 * @param validateFlag
	 */
	void setValidateFlag(boolean validateFlag);
	
	/**
	 * 次の行を読み込みます。
	 * @return true:次の行あり false:次の行なし close()が自動で呼び出されます
	 */
	boolean readNext();
	
	/**
	 * カレントの行に対してパース処理します。
	 * @return 結果オブジェクト
	 * @throws CSVValidationResultRuntimeException バリデーションエラーがあったときに投げられます。
	 */
	T parse() throws CSVValidationResultRuntimeException;
	
	/**
	 * 全てのデータをパースします
	 * 自動的にclose()が呼び出され、
	 * これ以降処理できなくなります。
	 * @return 結果オブジェクトリスト
 	 * @throws CSVValidationResultException 全てのデータのパース後 validation エラーがあった場合呼び出されます
	 */
	List<T> parseAll() throws CSVValidationResultException;

	/**
	 * カレントの行に対してバリデーションを行います。
	 * @return バリデーション結果
	 */
	CSVValidateResult validate();
	
	/**
	 * 全てのデータをバリデーションします
	 * 自動的にclose()が呼び出され、
	 * これ以降処理できなくなります。
	 * @return バリデーション結果リスト
	 */
	List<CSVValidateResult> validateAll();
	
	/**
	 * ストリームを閉じます。
	 */
	void close();
}
