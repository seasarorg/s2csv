package org.seasar.s2csv.csv.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.validator.CSVColumnChecks;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@CSVValidator(msgKey="",method="validateDateType",
		methodClass=CSVColumnChecks.class,
		methodArgsNames={"datePattern","datePatternStrict"}
		)
public @interface CSVDateType {
	
	String msgKey() default "errors.date";
	
	String[] args() default {CSVColumn.REPLACE_NAME};

    /** 日付形式チェックパターン  */
    String datePattern() default "";

    /** 厳密な日付形式チェックパターン */
    String datePatternStrict() default "";
}