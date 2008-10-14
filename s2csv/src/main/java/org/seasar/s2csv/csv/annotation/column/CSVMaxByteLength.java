package org.seasar.s2csv.csv.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.validator.CSVColumnChecks;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@CSVValidator(msgKey="",
		method="validateMaxByteLength",
		methodClass=CSVColumnChecks.class,
		methodArgsNames={"maxbytelength","charset"}
		)
public @interface CSVMaxByteLength {
	
	String msgKey() default "errors.maxbytelength";
	
	String[] args() default {CSVColumn.REPLACE_NAME,"${var:maxbytelength}"};
	
	int maxbytelength();

	String charset() default "";
}