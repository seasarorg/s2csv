package org.seasar.s2csv.csv.controller;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
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
public class CSVParseCtrlImpl<T> implements S2CSVParseCtrl<T>{

	private CSVConvertCtrl maker;
	private CSVValidateCtrl validator;
	
	private CSVParser parser;
	private CSVEntityDesc csvEntityDesc;

	@Binding(bindingType=BindingType.NONE)
	private boolean validateFlag;

	private Object validateEntity;
	
	private boolean next;
	private String[] currentLine;
	private long currentNo;

	public void setCSVParser(CSVParser parser){
		this.parser = parser;
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

	public boolean readNext() {
		next = parser.isNext();
		if(next){
			currentLine = parser.nextLine();
			currentNo = parser.getCurrentLineNo();
		}else{
			currentLine = null;
			currentNo = -1;
			this.close();
		}
		
		return next;
	}

	@SuppressWarnings("unchecked")
	public T parse() throws CSVValidationResultRuntimeException {
		
		T o = null;
		
		//エンティティ取得
		T entity = (T) SingletonS2Container
							.getComponent(csvEntityDesc.getEntityClass());
		
		if(validateFlag){
			
			CSVValidateResult valRes = 
				this.validateLine(entity , currentLine, currentNo);
			
			if(valRes != null){
				throw new CSVValidationResultRuntimeException(valRes);
			}
		}
		
		o = maker.covToObj(entity, currentLine);
		
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> parseAll() throws CSVValidationResultException {

		List<CSVValidateResult> validateResult = new ArrayList<CSVValidateResult>();
		
		List cache = new ArrayList();
		try{
			while(this.readNext()){
				try{
					T o = this.parse();
					cache.add(o);
				}catch(CSVValidationResultRuntimeException e){
					validateResult.add(e.getValidateResult());
				}
			}
		}finally{
			this.close();
		}
		
		if(validateResult.size() > 0){
			throw new CSVValidationResultException(
					cache, validateResult
					);
		}

		return cache;
	}
	
	public void close(){
		this.parser.close();
	}
	
	/**
	 * 一行分のバリデーションを行う
	 * @param validateEntity
	 * @param line
	 * @param lineNo
	 * @return バリデーションエラーがないときはnull
	 */
	public CSVValidateResult validateLine(Object validateEntity, String[] line, long lineNo){
		return validator.validate(validateEntity , line, lineNo);
	}

	/**
	 * バリデーションのみを行います。
	 * @return バリデーション結果
	 */
	public List<CSVValidateResult> validateAll(){

		List<CSVValidateResult> result = new ArrayList<CSVValidateResult>();
		
		try{
			Object entity = SingletonS2Container.getComponent(csvEntityDesc.getEntityClass());
			
			while(this.readNext()){
				CSVValidateResult vaRes = 
					this.validateLine(entity , currentLine, currentNo);
				
				if(vaRes != null){
					result.add(vaRes);
				}
			}
		}finally{
			this.close();
		}
		
		return result;
	}

	public CSVValidateResult validate() {
		if(validateEntity == null){
			//バリデーションで使用するEntityはパフォーマンスのためインスタンス生成は１度だけ
			validateEntity = SingletonS2Container
							.getComponent(csvEntityDesc.getEntityClass());
		}
		return this.validateLine(validateEntity , currentLine, currentNo);
	}
}
