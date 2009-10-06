package org.seasar.s2csv.csv.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Emailアドレス形式であるかチェックする
 * 
 * @author newta
 */
public class EmailValidator {

	static protected String ptnStr = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)";
	static private Pattern ptn = Pattern.compile(ptnStr);

	/**
	 * Email形式であるかチェックする
	 * @param email
	 * @return true:email形式
	 */
	public static boolean isEmail(String email) {
		
		Matcher mc = ptn.matcher(email);
		if (mc.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
