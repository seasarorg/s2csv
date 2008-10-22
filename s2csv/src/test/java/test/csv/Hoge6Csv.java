package test.csv;

import org.seasar.framework.util.StringUtil;
import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;
/** */
@CSVEntity(header=false,columnCountCheck=false)
public class Hoge6Csv {
	/** */
	@CSVRequired
	@CSVColumn(columnIndex=0)
	@CSVValidator(msgKey="errors.dept",method="deptValidate")
	public String a;
	/** */
	@CSVMaxLength(maxlength=10)
	@CSVColumn(columnIndex=1)
	public String b;
	
	/**
	 * 
	 * @param column_a_data
	 * @return result
	 */
	public boolean deptValidate(String column_a_data){
		if(StringUtil.isNotBlank(column_a_data)){
			throw new CSVValidationException("errors.custom2",new Object[]{"costom validation error2"});
		}
		
		return false;
	}
}
