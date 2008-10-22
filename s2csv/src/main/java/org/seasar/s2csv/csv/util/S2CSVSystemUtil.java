package org.seasar.s2csv.csv.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

import org.seasar.s2csv.csv.annotation.column.CSVByteType;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVDateType;
import org.seasar.s2csv.csv.annotation.column.CSVDoubleType;
import org.seasar.s2csv.csv.annotation.column.CSVFloatType;
import org.seasar.s2csv.csv.annotation.column.CSVIntegerType;
import org.seasar.s2csv.csv.annotation.column.CSVLongType;
import org.seasar.s2csv.csv.annotation.column.CSVShortType;

/**
 * s2csv全般 ユーティリティ
 * 基本的にs2csvのシステムで使われるもの
 * @author newta
 */
public class S2CSVSystemUtil {
	
	/**
	 * カラムの名称を取得します
	 * @param f
	 * @return カラム名
	 */
	public static String getColumnName(Field f){

		CSVColumn c = f.getAnnotation(CSVColumn.class);
		
		String columnName = c.columnName();
		if(CSVColumn.NON.equals(columnName)){
			columnName = f.getName();
		}
		
		return columnName;
	}
	
	/**
	 * メッセージの変数の置き換えを行います
	 * このメソッドは引数の配列を破壊的に設定します
	 * @param msgArgs
	 * @param f 
	 * @param wrappingAnnoProps 
	 * @return 置き換えたメッセージ変数
	 */
	public static Object[] preReplaceMsgArgs(Object[] msgArgs,Field f, Map<String, Object> wrappingAnnoProps){
		
		if(msgArgs == null || msgArgs.length == 0){
			return msgArgs;
		}
		
		for(int i =0; i< msgArgs.length ; i++){
			
			Object arg = msgArgs[i];
			
			if (arg instanceof String) {
				String var = (String) arg;
				
				if(f != null && CSVColumn.REPLACE_NAME.equals(arg)){
					
					var = getColumnName(f);
					
				}else if(var.startsWith("${var:") && var.endsWith("}")
						&& !var.startsWith("$$")){
					
					if(wrappingAnnoProps != null){
						String keyName = var.substring("${var:".length(),var.length() -1);
						var = String.valueOf(wrappingAnnoProps.get(keyName));
					}
				}else if(var.startsWith("$$")){
					var = var.substring(1);
				}
				
				msgArgs[i] = var;
			}
			
		}
		
		return msgArgs;
	}
	
	/**
	 * 行を挿入するメッセージの変数の置き換えを行います
	 * Object配列は新しく作成されます
	 * @param msgArgs
	 * @param lineNo
	 * @return メッセージオブジェクト
	 */
	public static Object[] replaceMsgArgs(Object[] msgArgs,long lineNo){

		if(msgArgs == null){
			return msgArgs;
		}
		
		Object[] newArgs = new Object[msgArgs.length];

		for(int i =0; i< msgArgs.length ; i++){
			
			Object arg = msgArgs[i];
				
			if(CSVColumn.REPLACE_LINE_NO.equals(arg)){
				arg = String.valueOf(lineNo);
			}
			
			newArgs[i] = arg;
		}
		
		return newArgs;
	}
	
	/**
	 * フィールドの型からデフォルト設定のアノテーションを取り出す。
	 * @param field
	 * @return フィールド型から自動で追加されるアノテーションバリデート
	 */
	public static Class<? extends Annotation> getTypeValidateAnnotation(Field field){

		Class<?> fieldType = field.getType();
		
		Class<? extends Annotation> typeAnnotationClazz = null;
		
		if(fieldType.equals(byte.class) || fieldType.equals(Byte.class)){
			typeAnnotationClazz = CSVByteType.class;
		}else if(fieldType.equals(Date.class)){
			typeAnnotationClazz = CSVDateType.class;
		}else if(fieldType.equals(double.class) || fieldType.equals(Double.class)){
			typeAnnotationClazz = CSVDoubleType.class;
		}else if(fieldType.equals(float.class) || fieldType.equals(Float.class)){
			typeAnnotationClazz = CSVFloatType.class;
		}else if(fieldType.equals(int.class) || fieldType.equals(Integer.class)){
			typeAnnotationClazz = CSVIntegerType.class;
		}else if(fieldType.equals(long.class) || fieldType.equals(Long.class)){
			typeAnnotationClazz = CSVLongType.class;
		}else if(fieldType.equals(short.class) || fieldType.equals(Short.class)){
			typeAnnotationClazz = CSVShortType.class;
		}
		
		return typeAnnotationClazz;
	}
}
