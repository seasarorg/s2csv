package org.seasar.s2csv.csv.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Emailアドレス形式であるかチェックする
 * 
 * @author newta
 * 
 */
public class EmailValidator {

	static protected String ptnStr = "[¥¥d[a-z]]+@[¥¥d[a-z]]+.[a-z]+|[¥¥d[a-z]]+@[¥¥d[a-z]]+.[a-z]+.[a-z]+|[¥¥d[a-z]]+@[¥¥d[a-z]]+.[a-z]+.[a-z]+.[a-z]+";
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
