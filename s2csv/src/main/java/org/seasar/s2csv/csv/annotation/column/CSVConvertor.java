package org.seasar.s2csv.csv.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.convertor.CSVColumnConvertor;

/**
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVConvertor  {
	
	/** カラムを変換する際に使用するクラスがあれば登録する */
	Class<? extends CSVColumnConvertor> convertor();
	/** 
	 * コンバータに渡すデータ郡
	 * setterもしくはフィールドに設定する値を
	 * フィールド名 + "=" + 値　で登録する。
	 * 複数登録する際には配列で設定する
	 */
	String[] convertSetProp() default "";
}
