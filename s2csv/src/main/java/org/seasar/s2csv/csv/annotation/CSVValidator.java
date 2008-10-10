package org.seasar.s2csv.csv.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CSVにバリデーション情報を付加します
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
public @interface CSVValidator {

	/** メッセージキー */
    String msgKey();
    /** メッセージ引数 */
    String[] args() default {};

    /** バリデーション実行メソッド */
    String method();
    
    /**
     * バリデーション実行クラスにプロパティを与える設定を書きます。
     * ここに書き込まれたプロパティ名と同名のプロパティを
     * 継承したアノテーションから取り出し、設定します
     */
    String[] methodArgsNames() default {};
    
    /** バリデーション実行クラス CSVCustamValidateの場合、値を持っているクラスそのもの */
    Class<?> methodClass() default ValidateTargetEntity.class;
}