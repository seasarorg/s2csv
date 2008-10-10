package org.seasar.s2csv.csv.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.seasar.framework.util.StringUtil;

/**
 * 日付形式のコンバータです
 * @author newta
 */
public class CSVDateConvertor implements CSVColumnConvertor {

	private SimpleDateFormat format;
	
	protected String defaultPattern="yyyy/mm/dd" ;
	
	/**
	 * 
	 */
	public CSVDateConvertor(){
		this.format = new SimpleDateFormat(defaultPattern);
	}
	
	/**
	 * @param pattern
	 */
	public void setPattern(String pattern){
		this.format = new SimpleDateFormat(pattern);
	}

	public String getAsString(Object o) {

		if(o == null){
			return null;
		}
		
		Date d = (Date) o;
		return format.format(d);
	}

	public Object getAsObject(String column) throws ParseException {
		
		if(StringUtil.isEmpty(column)){
			return null;
		}
		
		if("null".equals(column)){
			return null;
		}
		
		return format.parse(column);
	}
}
