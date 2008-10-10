package org.seasar.s2csv.csv;

import java.util.List;

import org.seasar.s2csv.csv.convertor.CSVConvertCtrl;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.io.CSVWriter;
import org.seasar.s2csv.csv.validator.CSVValidateCtrl;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * オブジェクト形式のデータをCSVファイル形式のデータに書き出す変換のコントロールを行います
 * @author newta
 * @param <T> 
 */
public interface S2CSVWriteCtrl<T> {


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
	 * 書き込み時にバリデートを行います
	 * 初期値 false
	 * @param validateFlag
	 */
	void setValidateFlag(boolean validateFlag);
	
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
	 * writerを閉じます
	 */
	void close();

	/**
	 * オブジェクトをバリデーションします
	 * メッセージに設定される行は 0 になります。
	 * @param o
	 * @return バリデーション結果
	 */
	CSVValidateResult validate(T o);

	/**
	 * オブジェクトをバリデーションします
	 * メッセージに設定される行は 0 になります。
	 * @param o
	 * @param lineNo 
	 * @return バリデーション結果
	 */
	CSVValidateResult validate(T o, long lineNo);

	/**
	 * オブジェクトを全てバリデーションします
	 * @param list 
	 * @param o
	 * @return バリデーション結果リスト
	 */
	List<CSVValidateResult> validateAll(List<T> list);
}
