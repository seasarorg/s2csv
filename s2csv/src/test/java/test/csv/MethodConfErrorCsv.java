package test.csv;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
/** */
@CSVEntity(header=true)
public class MethodConfErrorCsv {
	/** */
	@CSVColumn(columnIndex=0)
	@CSVValidator(msgKey="",method="aaaa")
	public String a;
}
