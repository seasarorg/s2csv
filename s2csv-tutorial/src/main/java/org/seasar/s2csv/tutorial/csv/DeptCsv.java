package org.seasar.s2csv.tutorial.csv;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;



@CSVEntity(header=false)
public class DeptCsv {

	@CSVColumn(columnIndex=0)
	public Long id;
	
	@CSVRequired
	@CSVColumn(columnIndex=1)
	public Integer deptNo;

	@CSVColumn(columnIndex=2)
	public String deptName;

	@CSVColumn(columnIndex=3)
	public String loc;

	@CSVColumn(columnIndex=4)
	public Integer versionNo;
}