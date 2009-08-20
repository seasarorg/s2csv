package org.seasar.s2csv.csv.exception.runtime;

import java.util.List;

import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * parseAll及びwriteAllが実行されたときに
 * バリデーションエラーが発生していた場合に発行されるException
 * (ValidationFlagがtureのときにしか発生しない)
 * @author newta
 */
public class CSVValidationResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<?> dataList;

	private List<CSVValidateResult> validateResultList;
	/**
	 * @param dataList
	 * @param validateResultList
	 */
	public CSVValidationResultException(
			List<?> dataList,
			List<CSVValidateResult> validateResultList) {
		this.dataList = dataList;
		this.validateResultList = validateResultList;
	}
	/**
	 * @return バリデーション結果
	 */
	public List<CSVValidateResult> getValidateResultList(){
		return this.validateResultList;
	}
	/**
	 * @param <T>
	 * @return パース結果(バリデーションエラーの無かったもののみ)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getDataList(){
		return (List<T>) this.dataList;
	}
}
