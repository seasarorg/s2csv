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
		method="validateMask",
		methodClass=CSVColumnChecks.class,
		methodArgsNames={"mask"}
		)
public @interface CSVMask {

	String msgKey() default "errors.invalid";
	
	String[] args() default {};
	
	String mask();
}
