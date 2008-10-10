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
		method="validateMinByteLength",
		methodClass=CSVColumnChecks.class,
		methodArgsNames={"minbytelength","charset"}
		)
public @interface CSVMinByteLength {
	
	String msgKey() default "errors.minbytelength";
	
	String[] args() default {};
	
	int minbytelength();

	String charset() default "";
}