package test.csv;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

@CSVEntity(header=true)
public class DeptCsv {

    /** deptNoプロパティ */
    @CSVColumn(columnIndex=2)
    public Integer deptNo;

    /** deptNameプロパティ */
    @CSVColumn(columnIndex=1)
    public String deptName;

    /** locプロパティ */
    @CSVColumn(columnIndex=0)
    public String loc;
}
