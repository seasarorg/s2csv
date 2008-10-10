package test.csv;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

import test.entity.Dept;
import test.service.DeptService;

@CSVEntity(header=false)
public class HogeCsv {

	public DeptService deptService;
	
	@CSVRequired
	@CSVColumn(columnIndex=0)
	@CSVValidator(msgKey="errors.dept",method="deptValidate")
	public String a;
	
	@CSVMaxLength(maxlength=10)
	@CSVColumn(columnIndex=1)
	public String b;
	
	
	public boolean deptValidate(String column_a_data){
		
		Dept dept = deptService.findById(Long.valueOf(1));
		
		System.out.println(dept);
		
		return false;
	}
}
