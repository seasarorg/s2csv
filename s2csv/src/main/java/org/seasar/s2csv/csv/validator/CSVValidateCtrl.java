package org.seasar.s2csv.csv.validator;

import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.s2csv.csv.convertor.CSVConvertCtrl;
import org.seasar.s2csv.csv.desc.CSVColumnDesc;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.desc.CSVValidateDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationInvokeException;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;
import org.seasar.s2csv.csv.util.S2CSVSystemUtil;

/**
 * CSVデータのバリデーションを行う
 * 管理は行単位
 */
public class CSVValidateCtrl {

	/** CSVエンティティの設定 */
	private CSVEntityDesc entityDesc;
	
	/** Objectに設定済みの値をバリデーションするときに使用します */
	private CSVConvertCtrl convertCtl;

	/**
	 * @param entityDesc
	 */
	public CSVValidateCtrl(CSVEntityDesc entityDesc){
		this.entityDesc = entityDesc;
	}
	/**
	 * @param csvEntityClass
	 */
	public CSVValidateCtrl(Class<?> csvEntityClass){
		this.entityDesc = S2CSVDescUtil.getCSVEntityDesc(csvEntityClass);
	}
    
    /**
     * CSV行データのバリデーションを行います。
     * @param entity 設定しようとしているオブジェクト(チェックメソッドが実装してあるオブジェクト)
     * @param csvLine CSV行データ
     * @param lineNo 
     * @return バリデーション結果　エラーがなければnull
     */
    public CSVValidateResult validate(Object entity, String[] csvLine, long lineNo){
    	
    	CSVValidateResult result = null;
    	

		for(CSVColumnDesc colDesc : entityDesc.getColumnConfigs()){
			
			String columnValue = csvLine[colDesc.getColumnIndex()];
			
			for(CSVValidateDesc valDesc : colDesc.getValidateList()){
				
				//カラムのバリデーション処理
				boolean b = false;

				CSVValidationException ve = null;
				try{
					b = column_validate(columnValue, valDesc, entity, lineNo);
				}catch(CSVValidationException v){
					b = false;
					ve = v;
				}	
				if(!b){
					//バリデーションエラー
	        		if(result == null){
	        			result = new CSVValidateResult();
		    			result.setData(csvLine);
		    			result.setLineNo(lineNo);
	        		}
	        		
	        		CSVMsg msg = new CSVMsg();
	        		if(ve != null){
	        			//Exceptionにメッセージキーが設定されている場合
	        			msg.setMsgKey(ve.getMsgKey());
	        			
	        			if(StringUtil.isNotBlank(msg.getMsgKey())){
	        				Object[] preCompArgs = S2CSVSystemUtil.preReplaceMsgArgs(ve.getArgs(),colDesc.getColumnDesc().getField(), null);
	        				msg.setMsgArgs(S2CSVSystemUtil.replaceMsgArgs(preCompArgs,lineNo));
	        			}
	        		}
	        		
	        		if(StringUtil.isBlank(msg.getMsgKey())){
		        		msg.setMsgKey(valDesc.getMsgKey());
		        		//行番号を設定する
		        		msg.setMsgArgs(S2CSVSystemUtil.replaceMsgArgs(valDesc.getMsgArgs(),lineNo));
	        		}
	        		result.getMsgs().add(msg);
					
					//TODO 継続して全てのバリデーションチェックを行うか制御できるようにする
	        		//カラムのチェックのみ終了 or 行のチェック終了
					//break; or return result;
				}
			}
		}
		
		
		if(result == null && entityDesc.getRecordValidate() != null){
			//カラムエラーが無いときのみentityのバリデーションを実行する
			
			for(CSVValidateDesc valDesc :entityDesc.getRecordValidate()){

				//レコードのバリデーション処理
				boolean b = false;

				CSVValidationException ve = null;
				try{
					b = record_validate(csvLine, valDesc, entity, lineNo);
				}catch(CSVValidationException v){
					b = false;
					ve = v;
				}	
				if(!b){
					//バリデーションエラー
	        		if(result == null){
	        			result = new CSVValidateResult();
		    			result.setData(csvLine);
		    			result.setLineNo(lineNo);
	        		}
	        		
	        		CSVMsg msg = new CSVMsg();
	        		if(ve != null){
	        			//Exceptionにメッセージキーが設定されている場合
	        			msg.setMsgKey(ve.getMsgKey());
	        			
	        			if(StringUtil.isNotBlank(msg.getMsgKey())){
	        				Object[] preCompArgs = S2CSVSystemUtil.preReplaceMsgArgs(ve.getArgs(), null, null);
	        				msg.setMsgArgs(S2CSVSystemUtil.replaceMsgArgs(preCompArgs,lineNo));
	        			}
	        		}
	        		
	        		if(StringUtil.isBlank(msg.getMsgKey())){
		        		msg.setMsgKey(valDesc.getMsgKey());
		        		//行番号を設定する
		        		msg.setMsgArgs(S2CSVSystemUtil.replaceMsgArgs(valDesc.getMsgArgs(),lineNo));
	        		}
	        		result.getMsgs().add(msg);
					
					//TODO 継続して全てのバリデーションチェックを行うか制御できるようにする
					//break; or return result;
				}
			}
		}
		
		return result;
    }
    

    /**
     * バリデーションでチェックを行う (レコード)
     * @param recordValues
     * @param valDesc
     * @param entity
     * @return ture:エラーなし false:エラー
     */
    protected boolean record_validate(String[] recordValues,CSVValidateDesc valDesc,Object entity,long lineNo){

		if(valDesc.getValidateMethod() == null){
			return true;
		}
		
		Object[] methodArgs = valDesc.getValidateMethodArgs();
		//先頭にCSVのパラメータ設定
		methodArgs[0] = recordValues;
		
		
		Boolean b;
		try{
			if(valDesc.isEntityVadlidateMethod()){
				//エンティティのチェックメソッド実行
				b = (Boolean) MethodUtil.invoke(valDesc.getValidateMethod(),entity,methodArgs);
			}else{
				//validationのスタティックメソッド実行
				b = (Boolean) MethodUtil.invoke(valDesc.getValidateMethod(),null,methodArgs);
			}
		}catch(InvocationTargetRuntimeException e){
			throw new CSVValidationInvokeException(valDesc.getValidateMethod(), e);
		}catch(IllegalAccessRuntimeException e){
			throw new CSVValidationInvokeException(valDesc.getValidateMethod(), e);
		}
		return b.booleanValue();
    }
    
    /**
     * バリデーションでチェックを行う (カラム)
     * @param columnValue
     * @param valDesc
     * @param entity
     * @return ture:エラーなし false:エラー
     */
    protected boolean column_validate(String columnValue,CSVValidateDesc valDesc,Object entity,long lineNo){

		if(valDesc.getValidateMethod() == null){
			return true;
		}
		
		Object[] methodArgs = valDesc.getValidateMethodArgs();
		//先頭にCSVのパラメータ設定
		methodArgs[0] = columnValue;
		
		
		Boolean b;
		try{
			if(valDesc.isEntityVadlidateMethod()){
				//エンティティのチェックメソッド実行
				b = (Boolean) MethodUtil.invoke(valDesc.getValidateMethod(),entity,methodArgs);
			}else{
				//validationのスタティックメソッド実行
				b = (Boolean) MethodUtil.invoke(valDesc.getValidateMethod(),null,methodArgs);
			}
		}catch(InvocationTargetRuntimeException e){
			throw new CSVValidationInvokeException(valDesc.getValidateMethod(), e);
		}catch(IllegalAccessRuntimeException e){
			throw new CSVValidationInvokeException(valDesc.getValidateMethod(), e);
		}
		return b.booleanValue();
    }
    
    /**
     * オブジェクトにセット済みのバリデーションを行います。
     * オブジェクト→CSVの文字列に変換してからバリデーションを行います
     * @param entity
     * @param lineNo 
     * @return バリデーション結果　エラーがなければnull
     */
	public CSVValidateResult validate(Object entity, long lineNo){

		if(this.convertCtl == null){
			//コンバータの作成
			this.convertCtl = new CSVConvertCtrl(this.entityDesc);
		}
		
		//CSV文字列に変換
		String[] csvLine = this.convertCtl.covToCSVLine(entity);
		
		//バリデーション処理
		return validate(entity, csvLine, lineNo);
	}

}
