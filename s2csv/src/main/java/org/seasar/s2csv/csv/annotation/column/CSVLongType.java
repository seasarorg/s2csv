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
@CSVValidator(msgKey="",method="validateLongType", methodClass=CSVColumnChecks.class)
public @interface CSVLongType {

	/** メッセージキー */
	String msgKey() default "errors.long";
	/** メッセージ引数 */
	String[] args() default {CSVColumn.REPLACE_NAME};
}
