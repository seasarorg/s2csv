package org.seasar.s2csv.csv.util.validator;

import junit.framework.TestCase;

import org.seasar.s2csv.csv.validator.EmailValidator;

/**
 * EmailValidatorのtestクラスです。
 * @author newta
 */
public class EmailValidatorTest extends TestCase {

	/**
	 * test
	 */
	public static void testEmail() {
		assertFalse(EmailValidator.isEmail("@"));
		assertFalse(EmailValidator.isEmail("a@"));
		assertFalse(EmailValidator.isEmail("a@a"));
		assertFalse(EmailValidator.isEmail("a@a.a"));
		assertTrue(EmailValidator.isEmail("a@a.aa"));
		assertFalse(EmailValidator.isEmail("a@.a.aa"));
		assertFalse(EmailValidator.isEmail("a.@a.aa"));
		assertFalse(EmailValidator.isEmail("a.@a.aa"));
		assertFalse(EmailValidator.isEmail(".a@a.aa"));
		assertTrue(EmailValidator.isEmail("a.a@a.aa"));
		assertTrue(EmailValidator.isEmail("a.a@a.a.aa"));
		assertTrue(EmailValidator.isEmail("1a.a@a.a.aa"));
		assertTrue(EmailValidator.isEmail("1a.a@A.a3.aa"));

		assertTrue(EmailValidator.isEmail("aaaaaa@aaa.b3bb.co.jp"));
		assertTrue(EmailValidator.isEmail("aaa.aaa@bbb.co.jp"));
		assertTrue(EmailValidator.isEmail("aaa3aaa@aaa.bbb.co.jp"));
		
	}
}
