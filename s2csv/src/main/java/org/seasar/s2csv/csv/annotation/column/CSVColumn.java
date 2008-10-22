package org.seasar.s2csv.csv.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * S2Csvのカラムで使用されることを示すアノテーションです
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVColumn  {
	
	//TODO これでいいのか考え中
	/** カラム名未定義 */
	public static final String NON = "org.seasar.s2csv.csv.annotation.column.CSVColumn.NON";
	
	/** バリデーションエラー等でカラム名に置き換えるときにメッセージ引数に指定します */
	public static final String REPLACE_NAME = "org.seasar.s2csv.csv.annotation.column.CSVColumn.REPLACE_NAME";
	
	/** バリデーションエラー等で行番号に置き換えるときにメッセージ引数に指定します */
	public static final String REPLACE_LINE_NO = "org.seasar.s2csv.csv.annotation.column.CSVColumn.REPLACE_LINE_NO";
	
	/** quoutの有り無し */
	boolean quote() default true;
	
	/** カラム名 */
	String columnName() default NON;
	
	/** カラム番号 0から開始 */
	int columnIndex();
}
