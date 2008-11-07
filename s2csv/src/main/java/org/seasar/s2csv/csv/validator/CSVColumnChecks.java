package org.seasar.s2csv.csv.validator;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * バリデートチェック　メソッドクラス
 * @author newta
 */
public class CSVColumnChecks {

	/**
	 * Required
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateRequired(String value){
		
		
		if(isBlank(value)){
			return false;
		}
		
		return true;
	}
	
	//TODO Validwhen 作るのは気が向いたらで。
	
//	Minlength 
	/**
	 * @param value 
	 * @param minlength 
	 * @return 結果
	 */
	public static boolean validateMinLength(String value, int minlength) {
		
		if (!isBlank(value)){
			try {
				if (value.length() < minlength){
					return false;
				}
				
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
//	Maxlength
	/**
	 * @param value 
	 * @param maxlength 
	 * @return 結果
	 */
	public static boolean validateMaxLength(String value, int maxlength) {
		
		if (!isBlank(value)){
			try {
				if (value.length() > maxlength){
					return false;
				}
				
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	// Minbytelength
	/**
	 * @param value 
	 * @param minbytelength 
	 * @param charset 
	 * @return 結果
	 */
	public static boolean validateMinByteLength(String value, int minbytelength,String charset) {
		
		if (!isBlank(value)){
			try {
				return getBytes(value, charset).length >= minbytelength;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	// Maxbytelength
	/**
	 * @param value 
	 * @param maxbytelength 
	 * @param charset 
	 * @return 結果
	 */
	public static boolean validateMaxByteLength(String value, int maxbytelength,String charset) {
		
		if (!isBlank(value)){
			try {
				return getBytes(value, charset).length <= maxbytelength;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	// Mask
	/**
	 * @param value 
	 * @param mask 
	 * @return 結果
	 */
	public static boolean validateMask(String value,String mask) {
		
		if (!isBlank(value)){
			try {
				Pattern patten = Pattern.compile(mask);
				
				if (!patten.matcher(value).matches()){
					return false;
				}
				
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		return true;
	}
	
	// IntRange
	/**
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean validateIntRange(String value,int min,int max){
		
		if(!isBlank(value)){
			try {
				int intValue = Integer.parseInt(value);
				
				if(!isRangeCheck(intValue, min, max)){
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	// LongRange
	/**
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean validateLongRange(String value,long min,long max){
		
		if(!isBlank(value)){
			try {
				long intValue = Long.parseLong(value);
				
				if(!isRangeCheck(intValue, min, max)){
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	// FloatRange
	/**
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean validateFloatRange(String value,float min,float max){
		
		if(!isBlank(value)){
			try {
				float intValue = Float.parseFloat(value);
				
				if(!isRangeCheck(intValue, min, max)){
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}


	// DoubleRange
	/**
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean validateDoubleRange(String value,double min,double max){
		
		if(!isBlank(value)){
			try {
				double intValue = Double.parseDouble(value);
				
				if(!isRangeCheck(intValue, min, max)){
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	

	// ByteType
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateByteType(String value){

		if(!isBlank(value)){
			try {
				
				Byte result = null;
				result = new Byte(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	// ShortType
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateShortType(String value){

		if(!isBlank(value)){
			try {
				
				Short result = null;
				result = new Short(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	// IntegerType
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateIntegerType(String value){

		if(!isBlank(value)){
			try {
				
				Integer result = null;
				result = new Integer(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	//	LongType 
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateLongType(String value){

		if(!isBlank(value)){
			try {
				
				Long result = null;
				result = new Long(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	

//	FloatType 
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateFloatType(String value){

		if(!isBlank(value)){
			try {
				
				Float result = null;
				result = new Float(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

//	DoubleType 
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateDoubleType(String value){

		if(!isBlank(value)){
			try {
				
				Double result = null;
				result = new Double(value);
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	// DateType 
	/**
	 * @param value 
	 * @param datePattern 
	 * @param datePatternStrict 
	 * @return 結果
	 */
	public static boolean validateDateType(String value,String datePattern,String datePatternStrict ){

		if(!isBlank(value)){
			try {
				
				Date result = null;
				
				if (datePattern != null && datePattern.length() > 0) {
					result = newDate(value,
							datePattern, false);
				} else if (datePatternStrict != null
						&& datePatternStrict.length() > 0) {
					result = newDate(value,
							datePatternStrict, true);
				} else{
					DateFormat formatter = DateFormat.getDateInstance(3, Locale.getDefault());
					formatter.setLenient(false);
					result = formatter.parse(value);
				}
				
				if(result == null){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	
//	TODO CreditCardTypeはめんどそうだからとりあえず実装しない
	
	
//	TODO Emailチェックは一旦コメント めんどいから
//	EmailType 
	/**
	 * @param value 
	 * @return 結果
	 */
	public static boolean validateEmailType(String value){

		if(!isBlank(value)){
			try {
				
				if(!EmailValidator.isEmail(value)){
					return false;
				}else{
					return true;
				}
				
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	
//	TODO UrlType CSV出力でいるか？ 

	/**
	 * 範囲チェック
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean isRangeCheck(int value, int min, int max)
    {
		return value >= min && value <= max;
    }
	/**
	 * 範囲チェック
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean isRangeCheck(long value, long min, long max)
    {
		return value >= min && value <= max;
    }
	/**
	 * 範囲チェック
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean isRangeCheck(float value, float min, float max)
    {
		return value >= min && value <= max;
    }
	/**
	 * 範囲チェック
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean isRangeCheck(short value, short min, short max)
    {
		return value >= min && value <= max;
    }
	/**
	 * 範囲チェック
	 * @param value 
	 * @param min 
	 * @param max 
	 * @return 結果
	 */
	public static boolean isRangeCheck(double value, double min, double max)
    {
		return value >= min && value <= max;
    }
	
	/**
	 * 空白かどうかチェックする
	 * @param str 
	 * @return 結果
	 */
    public static boolean isBlank(String str)
    {
    	return str == null || str.trim().length() == 0;
    }
	
	/**
	 * 文字列をバイト配列に変換する。
	 */
	private static byte[] getBytes(String str, String charset)
    {
        if(charset == null || charset.length() == 0)
            return str.getBytes();


        try
        {
            return str.getBytes(charset);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }
	

	/**
	 * 日付形式のオブジェクトを取得する
	 * @param value 日付に変換する文字列
	 * @param datePattern 日付形式
	 * @param strict 厳密チェック
	 * @return 日付オブジェクト
	 */
    private static Date newDate(String value, String datePattern,
			boolean strict) {
		Date date = null;

		if (value == null || datePattern == null || datePattern.length() == 0)

			return null;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
			date = formatter.parse(value);

			if (strict && datePattern.length() != value.length())

				date = null;
		}

		catch (ParseException e) {
		}

		return date;
	}
}
