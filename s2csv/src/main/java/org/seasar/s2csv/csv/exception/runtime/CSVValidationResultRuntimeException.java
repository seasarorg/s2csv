package org.seasar.s2csv.csv.exception.runtime;

import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * 1件parse及び1件writeでバリデーションエラーが起きた時に発行されるException
 * (ValidationFlagがtureのときにしか発生しない)
 * @author newta
 */
public class CSVValidationResultRuntimeException extends RuntimeException
		 {

	private static final long serialVersionUID = 1L;

	private CSVValidateResult validateResult;
	/**
	 * @param validateResult
	 */
	public CSVValidationResultRuntimeException(
			CSVValidateResult validateResult)
	{
		this.validateResult = validateResult;
	}
	/**
	 * @return バリデーション結果
	 */
	public CSVValidateResult getValidateResult(){
		return this.validateResult;
	}
	
}
