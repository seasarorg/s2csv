package org.seasar.s2csv.csv.controller;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
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
public class CSVWriteCtrlImpl<T> implements S2CSVWriteCtrl<T> {

	private CSVEntityDesc csvEntityDesc;
	private CSVWriter writer;
	private CSVConvertCtrl maker;
	private CSVValidateCtrl validator;
	
	private boolean validateFlag;
	
	private Object validateEntity;
	
	public void setCSVWriter(CSVWriter writer){
		this.writer = writer;
	}
	
	public void setCSVEntityDesc(CSVEntityDesc csvEntityDesc){
		this.csvEntityDesc = csvEntityDesc;
	}
	
	public void setCSVMaker(CSVConvertCtrl maker){
		this.maker = maker;
	}
	
	public void setCSVValidator(CSVValidateCtrl validator){
		this.validator = validator;
	}


	public void setValidateFlag(boolean validateFlag) {
		this.validateFlag = validateFlag;
	}
	
	
	public void writeAll(List<T> list) throws CSVValidationResultException {

		List<CSVValidateResult> validateResult = new ArrayList<CSVValidateResult>();
		
		int i = 0;
		for(T o :list){
			
			if(validateFlag){
				CSVValidateResult valRes = this.validate(o ,i++);
				
				if(valRes != null){
					validateResult.add(valRes);
					continue;
				}
			}
			
			this.write(o);
		}

		if(validateResult.size() > 0){
			throw new CSVValidationResultException(
					null, validateResult
					);
		}
	}

	@SuppressWarnings("unchecked")
	public void write(T o) throws CSVValidationResultRuntimeException {
		
		if(validateFlag){
			CSVValidateResult valRes = this.validate(o , 0);
			if(valRes != null){
				throw new CSVValidationResultRuntimeException(valRes);
			}
		}

		String[] line = maker.covToCSVLine(o);
		writer.writeLine(line);
	}

	public void close(){
		
		this.writer.close();
	}


	/**
	 * バリデーションを行います。
	 * 行番号は0に設定されます
	 * @return バリデーション結果
	 */
	public CSVValidateResult validate(T o){
		return validate(o, 0);
	}
	
	/**
	 * バリデーションを行います。
	 * @param lineNo 行番号
	 * @return バリデーション結果
	 */
	public CSVValidateResult validate(T o, long lineNo){

		if(validateEntity == null){
			//バリデーションで使用するEntityはパフォーマンスのためインスタンス生成は１度だけ
			validateEntity = SingletonS2Container
							.getComponent(csvEntityDesc.getEntityClass());
		}
		
		Beans.copy(o, validateEntity)
				.includes(csvEntityDesc.getFieldNames())
				.execute();

		CSVValidateResult vaRes = validator.validate(o, lineNo);
			
		return vaRes;
	}


	public List<CSVValidateResult> validateAll(List<T> list) {

		List<CSVValidateResult> result = new ArrayList<CSVValidateResult>();
		
		int i = 0;
		for(T o :list){
			
			CSVValidateResult valres = this.validate(o ,i++);
			
			if(valres != null){
				result.add(valres);
			}
		}
		return result;
	}
}
