package test.csv;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
/** */
@CSVEntity(header=false,columnCountCheck=false)
public class Hoge7Csv {
	/** */
	@CSVRequired
	@CSVColumn(columnIndex=20)
	public String a;
	/** */
	@CSVMaxLength(maxlength=10)
	@CSVColumn(columnIndex=10)
	public String b;


}
