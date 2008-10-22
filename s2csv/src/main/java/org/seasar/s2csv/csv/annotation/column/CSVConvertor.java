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
	
	/** 
	 * カラムを変換する際に使用するクラスがあれば登録する
	 * CSVColumnConvertor.classが指定されているときはコンバート無し
	 */
	Class<? extends CSVColumnConvertor> convertor() default CSVColumnConvertor.class;
	/** 
	 * コンバータに渡すデータ郡
	 * setterもしくはフィールドに設定する値を
	 * フィールド名 + "=" + 値　で登録する。
	 * 複数登録する際には配列で設定する
	 */
	String[] convertSetProp() default "";
	
	/** 
	 * カラムをCSVイメージからオブジェクトに変換する際に使用するメソッドがあれば登録する
	 * メソッドはCSVオブジェクトのクラスのものが実行されます。
	 * 引数はStringのみです。
	 * メソッドの戻り値の型はvoid以外
	 * ただし、convertorがある場合この項目は無効 
	 */
	String convToObjMethod() default "";
	/** 
	 * カラムをオブジェクからトCSVイメージに変換する際に使用するメソッドがあれば登録する
	 * メソッドはオブジェクトのクラスに登録しておくこと。
	 * 引数はCSVオブジェクトのフィールドと同じもののみです。
	 * メソッドの戻り値の型はStringにする必要があります。
	 * ただし、convertorがある場合この項目は無効 
	 */
	String convToCSVMethod() default "";
}
