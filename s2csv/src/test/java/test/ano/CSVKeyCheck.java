package test.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.annotation.entity.CSVRecordValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@CSVRecordValidator(msgKey="", method="checkKeys")
public @interface CSVKeyCheck {

	String msgKey() default "errors.recordcheck2";
	
	String[] args() default {};
}
