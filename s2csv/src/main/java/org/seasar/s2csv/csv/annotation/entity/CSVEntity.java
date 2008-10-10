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
	
	//TODO 処理を追加する
	/** CSVColumnの名前と同じかチェックする(validate時) */
	boolean headerCheck() default true;
	
	//TODO 処理を追加する
	/** カラムの個数を指定する(0はCSVColumnの最大値 1以上の場合、validate時カラム数チェック) */
	int columnCount() default 0;
}
