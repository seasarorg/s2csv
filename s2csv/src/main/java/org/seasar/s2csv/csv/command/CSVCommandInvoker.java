package org.seasar.s2csv.csv.command;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.s2csv.csv.desc.CSVColumnDesc;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVConfigException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVSystemUtil;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

/**
 * バリデーションやコンバートのCommandを実行します。
 * @author newta
 */
public class CSVCommandInvoker {
	
	private S2CSVCommandContext context;
	
	/**
	 * entityデータからcsvの行データに変換します。
	 * @param csvEntityDesc
	 * @param entity
	 * @param lineNo
	 * @param commandMap
	 * @return　csvの行データ
	 */
	public String[] toCsv(CSVEntityDesc csvEntityDesc, Object entity,
			long lineNo, Map<String, List<S2CSVCommand>> commandMap) {

		if(entity == null){
			return null;
		}
		
		if(!entity.getClass().isAssignableFrom(csvEntityDesc.getEntityClass())){
			//違う設定渡されても、、、
			throw new CSVConfigException(entity.getClass());
		}

		if(context == null){
			context = new S2CSVCommandContext();
		}
		context.setEntity(entity);
		
		List<CSVColumnDesc> columnDescList = csvEntityDesc.getColumnConfigs();
		
		CSVValidateResult result = null;
		
		Object[] convAfter = new Object[csvEntityDesc.getColmunSize()];
		
		for(CSVColumnDesc colDesc : columnDescList){
			
			List<S2CSVCommand> commands = commandMap.get(colDesc.getColumnDesc().getPropertyName());

			Object value = colDesc.getColumnDesc().getValue(entity);
			
			//コマンドを実行します
			//エラーの場合resultにエラー情報が追加されます。
			value = execCommand(commands, value, result, null, lineNo,
					colDesc.getColumnDesc().getField());
			
			//エンティティに対して変換・チェックしたデータをセット
			convAfter[colDesc.getColumnIndex()] = value;
		}
		
		if(result != null){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		
		//カラムでのバリデーションエラーが無ければレコードのコマンドを実行します。
		List<S2CSVCommand> recodeCommands = commandMap.get(S2CSVCommand.RECODE);
		//コマンドを実行します
		//エラーの場合resultにエラー情報が追加されます。
		convAfter = (Object[]) execCommand(recodeCommands, convAfter, result, null, lineNo, null);
		if(result != null){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		
		//変換したオブジェクトをCSV行データにセットします。
		String[] csvLine = new String[csvEntityDesc.getColmunSize()];
		for(int i=0;i<csvLine.length;i++){
			Object o = convAfter[i];
			if(o != null){
				csvLine[i] = String.valueOf(o);
			}
		}
		
		//変換後のオブジェクトに対して実行するコマンド
		List<S2CSVCommand> afterCommands = commandMap.get(S2CSVCommand.RECODE_AFTER);
		csvLine = (String[]) execCommand(afterCommands, csvLine, result, null, lineNo, null);

		if(result != null){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		return csvLine;
	}
	/**
	 * csvの行データからオブジェクトへ変換します。
	 * @param <T>
	 * @param csvEntityDesc
	 * @param csvLine
	 * @param lineNo
	 * @param commandMap
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T toObj(CSVEntityDesc csvEntityDesc, String[] csvLine,
			long lineNo, Map<String, List<S2CSVCommand>> commandMap) {

		List<CSVColumnDesc> columnDescList = csvEntityDesc.getColumnConfigs();
		
		CSVValidateResult result = new CSVValidateResult();
		result.setData(csvLine);
		result.setLineNo(lineNo);;


		if(context == null){
			context = new S2CSVCommandContext();
		}
		T entity = (T) S2CSVUtil.newEntity(csvEntityDesc.getEntityClass());
		context.setEntity(entity);
		
		Object[] convAfter = new Object[csvEntityDesc.getColmunSize()];
		
		for(CSVColumnDesc colDesc : columnDescList){
			
			List<S2CSVCommand> commands = commandMap.get(colDesc.getColumnDesc().getPropertyName());

			Object value = csvLine[colDesc.getColumnIndex()];
			
			//コマンドを実行します
			//エラーの場合resultにエラー情報が追加されます。
			value = execCommand(commands, value, result, csvLine, lineNo,
					colDesc.getColumnDesc().getField());
			
			//エンティティに対して変換・チェックしたデータをセット
			convAfter[colDesc.getColumnIndex()] = value;
		}
		
		if(result.hasMsg()){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		
		//カラムでのバリデーションエラーが無ければレコードのコマンドを実行します。
		List<S2CSVCommand> recodeCommands = commandMap.get(S2CSVCommand.RECODE);
		//コマンドを実行します
		//エラーの場合resultにエラー情報が追加されます。
		convAfter = (Object[]) execCommand(recodeCommands, convAfter, result, csvLine, lineNo, null);
		if(result.hasMsg()){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		
		//変換したオブジェクトをエンティティにセットします。
		for(CSVColumnDesc colDesc : columnDescList){
			colDesc.getColumnDesc().setValue(entity, convAfter[colDesc.getColumnIndex()]);
		}
		
		//変換後のオブジェクトに対して実行するコマンド
		List<S2CSVCommand> afterCommands = commandMap.get(S2CSVCommand.RECODE_AFTER);
		entity = (T) execCommand(afterCommands, entity, result, csvLine, lineNo, null);

		if(result.hasMsg()){
			//エラーが発生している場合にはthrow
			throw new CSVValidationResultRuntimeException(result);
		}
		return entity;
	}
	
	/**
	 * コマンドを実行します
	 * @param commands
	 * @param value
	 * @param result
	 * @param csvLine
	 * @param lineNo
	 * @param colField
	 * @return
	 */
	private Object execCommand(List<S2CSVCommand> commands,
			Object value,
			CSVValidateResult result,
			String[] csvLine,
			long lineNo,
			Field colField){

		if(commands == null){
			return value;
		}
		
		for(S2CSVCommand command : commands){
			try {
				command.setContext(context);
				value = command.execute(value);
			} catch (CSVValidationException e) {
    			
				String msgKey = null;
				Object[] msgArgs = null;
				
        		if(StringUtil.isEmpty(e.getMsgKey())){
        			msgKey = command.getErrorMsgKey();
        			msgArgs = command.getErrorMsgValues();
        		}else{
        			msgKey = e.getMsgKey();
        			msgArgs = e.getArgs();
        		}
        		

        		CSVMsg msg = new CSVMsg();
    			msg.setMsgKey(msgKey);
        		
    			//行番号、カラム名などを置き換える。
    			Object[] preCompArgs =
    				S2CSVSystemUtil.preReplaceMsgArgs(
    					msgArgs,
    					colField,
    					null);
    			msg.setMsgArgs(S2CSVSystemUtil.replaceMsgArgs(preCompArgs,lineNo));
    			result.getMsgs().add(msg);
    			
    			//このカラムについては続きを処理しない
    			break;
			}
		}

		return value;
	}
	/**
	 * Commandに渡すcontextを設定する
	 * @param context
	 */
	public void setContext(S2CSVCommandContext context) {
		this.context = context;
	}

	/**
	 * Commandに渡すcontextを取得する
	 * @return context
	 */
	public S2CSVCommandContext getContext() {
		return context;
	}
}
