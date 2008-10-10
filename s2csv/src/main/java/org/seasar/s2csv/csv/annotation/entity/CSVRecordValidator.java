package org.seasar.s2csv.csv.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.s2csv.csv.annotation.ValidateTargetEntity;


/**
 * レコード全体のバリデーションを行う。
 * 相関チェック等
 * @author newta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CSVRecordValidator {

	/** メッセージキー */
    String msgKey();
    /** メッセージ引数 */
    String[] args() default {};

    /** バリデーション実行メソッド */
    String method();
    
    /** TODO CSVValidatorと同じにする？ */
    String[] methodArgsNames() default {};
    
    /** バリデーション実行クラス CSVCustamValidateの場合、値を持っているクラスそのもの */
    Class<?> methodClass() default ValidateTargetEntity.class;
    
    /** 
     * trueにするとCSVEntitiyのフィールドに値を設定してから動作する。
     * TODO 処理を追加する */
    boolean after() default false;
}
