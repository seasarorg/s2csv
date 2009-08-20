package test.csv;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

import test.entity.Dept;
import test.service.DeptService;
/** */
@CSVEntity(header=false,columnCountCheck=false)
public class HogeCsv {
	/** */
	public DeptService deptService;
//	public JdbcManagerImplementor jdbcManager;
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

//		System.out.println(
//				jdbcManager.getDialect()
//				.getSequenceNextValString("hoge", 0)
//		);
		
		Dept dept = deptService.findById(Long.valueOf(1));
		
		System.out.println(dept);
		
		return false;
	}
}
