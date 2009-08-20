package org.seasar.s2csv.csv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.command.CSVCommandInvoker;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.command.S2CSVCommandContext;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * CSVファイル形式のデータをオブジェクト形式に変換するコントロールを行います
 * @author newta
 * @param <T> 
 */
public class CSVParseCtrlImpl<T> implements S2CSVParseCtrl<T>{

	private CSVCommandInvoker commandInvoker;
	
	private CSVParser parser;
	private CSVEntityDesc csvEntityDesc;

	private boolean next;
	private String[] currentLine;
	private long currentNo;
	
	private CSVValidateResult parseValidationResult; 
	
	private List<CSVValidateResult> validationResults;
	private boolean exceptionThrow;
	private Map<String, List<S2CSVCommand>> commandMap;

	private S2CSVCommandContext commandContext;
	
	@Override
	@Resource(name="csvCommandInvoker")
	public void setCSVCommandInvoker(CSVCommandInvoker commandInvoker){
		this.commandInvoker = commandInvoker;
	}


	@Override
	//通常Streamを持つため手動でセットします。
	@Binding(bindingType=BindingType.NONE)
	public void setCSVParser(CSVParser parser){
		this.parser = parser;
	}

	@Override
	public void setCSVEntityDesc(CSVEntityDesc csvEntityDesc){
		this.csvEntityDesc = csvEntityDesc;
	}

	@Override
	public boolean readNext() {
		
		boolean tmpHeaderCheck = false;
		if(!next && csvEntityDesc.isCheckHeader()){
			//まだ1回も読んでないとき
			tmpHeaderCheck = true;
		}
		
		next = parser.isNext();
		
		if(next){
			if(tmpHeaderCheck){
				//ヘッダ文字列が同じかチェックする
				int hedLength = csvEntityDesc.getHeaderNames().length;
				String[] heders = parser.getHeader();
				for(int i =0;i<hedLength;i++){
					
					String hed1 = csvEntityDesc.getHeaderNames()[i];
					String hed2 = heders[i];
					
					if(hed1.equals(hed2) == false){
						throw new CSVFormatException(1);
					}
				}
			}
			
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
		
		if(commandContext == null){
			this.commandContext = new S2CSVCommandContext();
		}
		commandInvoker.setContext(commandContext);
		
		T o = null;
		try{
			o = (T) commandInvoker.toObj(csvEntityDesc, currentLine, currentNo, commandMap);
		}catch(CSVValidationResultRuntimeException e){
			if(validationResults == null){
				validationResults = new ArrayList<CSVValidateResult>();
			}
			
			parseValidationResult = e.getValidateResult();
			validationResults.add(parseValidationResult);
			
			if(exceptionThrow){
				throw e;
			}
		}
		
		return o;
	}

	@Override
	public List<T> parseAll() throws CSVValidationResultException {
		return this.parseAll(0);
	}

	@Override
	public List<T> parseAll(int errorLimitCount) throws CSVValidationResultException {

		//parseAllのthrow処理
		boolean tmpExceptionThrow = this.exceptionThrow;
		
		//parseの処理でthrowするかしないか設定する。
		this.exceptionThrow = (errorLimitCount > 0);
		
		List<T> cache = new ArrayList<T>();
		try{
			while(this.readNext()){
				try{
					T o = this.parse();
					
					//バリデーションエラーが起きているときはnullになる
					if(o != null){
						cache.add(o);
					}
				}catch(CSVValidationResultRuntimeException e){
					
					if(validationResults.size() >= errorLimitCount){
						//エラー上限数までエラーが発生したら終了する
						
						//throwフラグを戻す
						this.exceptionThrow = tmpExceptionThrow;
						if(this.exceptionThrow){
							throw new CSVValidationResultException(
									cache,
									validationResults
									);
						}else{
							return cache;
						}
					}
				}
			}
		}finally{
			this.close();
		}
		
		return cache;
	}
	
	public void close(){
		this.parser.close();
	}

	@Override
	public CSVEntityDesc getCSVEntityDesc() {
		return csvEntityDesc;
	}

	@Override
	public List<S2CSVCommand> getCommands(String propertyName) {
		return commandMap.get(propertyName);
	}


	@Override
	public void setCommandMap(Map<String, List<S2CSVCommand>> commandMap) {
		this.commandMap = commandMap;
	}

	@Override
	public void setExceptionThrow(boolean b) {
		this.exceptionThrow = b;
	}

	@Override
	public CSVValidateResult getParseValidationResult() {
		return parseValidationResult;
	}

	@Override
	public List<CSVValidateResult> getValidationResultAll() {
		return validationResults;
	}

}
