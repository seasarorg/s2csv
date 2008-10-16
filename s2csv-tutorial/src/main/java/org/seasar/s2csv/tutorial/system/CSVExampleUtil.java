package org.seasar.s2csv.tutorial.system;

import java.util.List;

import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

public class CSVExampleUtil {

    /**
     * バリデーションエラーのメッセージを表示します。
     * @param result
     */
    public static void showErrMessage(List<CSVValidateResult> result){
    	
    	if(result == null){
    		System.out.println("null");
    		return;
    	}

    	for(CSVValidateResult validateResult :result){
    		showErrMessage(validateResult);
    	}
    }

    /**
     * バリデーションエラーのメッセージを表示します。
     * @param validateResult
     */
    public static void showErrMessage(CSVValidateResult validateResult){

		//バリデーションエラー行
		 System.out.println(validateResult.getLineNo());
		 for(CSVMsg msg: validateResult.getMsgs()){
			 //バリデーションエラーメッセージ
			 System.out.println(msg);
		 }
    }
}
