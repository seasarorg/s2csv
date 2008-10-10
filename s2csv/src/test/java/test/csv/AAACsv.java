package test.csv;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVIntRange;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

@CSVEntity
public class AAACsv {

	@CSVColumn(columnIndex=0)
	@CSVIntRange(min=0,max=10)
	public Integer a;
}
