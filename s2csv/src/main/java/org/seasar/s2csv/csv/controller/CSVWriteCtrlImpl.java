package org.seasar.s2csv.csv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.command.CSVCommandInvoker;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.command.S2CSVCommandContext;
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
public class CSVWriteCtrlImpl<T> implements S2CSVWriteCtrl<T> {

	private CSVEntityDesc csvEntityDesc;
	private CSVWriter writer;
	private long currentNo;
	
	private CSVCommandInvoker commandInvoker;
	private Map<String, List<S2CSVCommand>> commandMap;

	private CSVValidateResult writeValidationResult; 
	private List<CSVValidateResult> validationResults;
	private boolean exceptionThrow;

	private S2CSVCommandContext commandContext;

	@Override
	public void setCSVWriter(CSVWriter writer){
		this.writer = writer;
	}
	@Override
	public void setCSVEntityDesc(CSVEntityDesc csvEntityDesc){
		this.csvEntityDesc = csvEntityDesc;
	}
	@Override
	public void setCSVCommandInvoker(CSVCommandInvoker commandInvoker){
		this.commandInvoker = commandInvoker;
	}
	@Override
	public Map<String, List<S2CSVCommand>> getCommandMap() {
		return commandMap;
	}

	@Override
	public CSVValidateResult getWriteValidationResult() {
		return writeValidationResult;
	}

	@Override
	public List<CSVValidateResult> getValidationResultAll() {
		return validationResults;
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
	public void writeAll(List<T> list) throws CSVValidationResultException {

		List<CSVValidateResult> validateResult = new ArrayList<CSVValidateResult>();
		
		currentNo = 0;
		
		try{
			for(T o :list){
				currentNo++;
				try{
					this.write(o);
				}catch (CSVValidationResultRuntimeException e) {
					//書き込みのときは継続だけにしようか。
					//エラーが出ても最後まで実行
				}
			}
	
			if(exceptionThrow && validateResult.size() > 0){
				throw new CSVValidationResultException(
						null, validateResult
						);
			}
		}finally{
			//TODO どうする？
			//this.close();
		}
	}

	@Override
	public void write(T o) throws CSVValidationResultRuntimeException {

		if(commandContext == null){
			this.commandContext = new S2CSVCommandContext();
		}
		commandInvoker.setContext(commandContext);
		
		String[] line = null;
		try{
			line = commandInvoker.toCsv(csvEntityDesc, o, currentNo, commandMap);
		}catch(CSVValidationResultRuntimeException e){
			if(validationResults == null){
				validationResults = new ArrayList<CSVValidateResult>();
			}
			
			writeValidationResult = e.getValidateResult();
			validationResults.add(writeValidationResult);
			
			if(exceptionThrow){
				throw e;
			}
		}

		writer.writeLine(line);
	}

	@Override
	public void close(){
		
		this.writer.close();
	}

}
