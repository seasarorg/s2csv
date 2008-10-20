package org.seasar.s2csv.csv.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.validator.CSVColumnChecks;

/**
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@CSVValidator(msgKey="",
		method="validateMaxByteLength",
		methodClass=CSVColumnChecks.class,
		methodArgsNames={"maxbytelength","charset"}
		)
public @interface CSVMaxByteLength {

	/** メッセージキー */
	String msgKey() default "errors.maxbytelength";
	/** メッセージ引数 */
	String[] args() default {CSVColumn.REPLACE_NAME,"${var:maxbytelength}"};
	/** 最大バイト数 */
	int maxbytelength();
	/** 文字コード */
	String charset() default "";
}