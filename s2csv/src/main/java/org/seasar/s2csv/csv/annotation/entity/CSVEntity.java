package org.seasar.s2csv.csv.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * CSVエンティティの設定定義を付加します
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CSVEntity {

	/** ヘッダ true:あり false:なし */
	boolean header() default true;
	
	/** CSVColumnの名前と同じかチェックする(validate時) */
	boolean headerCheck() default false;
	
	/** true時、カラムの個数をチェックします */
	boolean columnCountCheck() default true;
	
	/** カラム区切り文字 */
	char demiliter() default ',';
}
