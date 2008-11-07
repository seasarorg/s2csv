package test.csv;

import java.util.Date;

import org.seasar.s2csv.csv.annotation.column.CSVByteType;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVDateType;
import org.seasar.s2csv.csv.annotation.column.CSVDoubleType;
import org.seasar.s2csv.csv.annotation.column.CSVEmailType;
import org.seasar.s2csv.csv.annotation.column.CSVFloatRange;
import org.seasar.s2csv.csv.annotation.column.CSVFloatType;
import org.seasar.s2csv.csv.annotation.column.CSVIntRange;
import org.seasar.s2csv.csv.annotation.column.CSVIntegerType;
import org.seasar.s2csv.csv.annotation.column.CSVLongRange;
import org.seasar.s2csv.csv.annotation.column.CSVLongType;
import org.seasar.s2csv.csv.annotation.column.CSVMask;
import org.seasar.s2csv.csv.annotation.column.CSVMaxByteLength;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVMinByteLength;
import org.seasar.s2csv.csv.annotation.column.CSVMinLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.column.CSVShortType;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
/** */
@CSVEntity(header=false)
public class BBBCsv {
	/** */
	@CSVColumn(columnIndex=0)
	@CSVRequired
	@CSVIntegerType
	@CSVIntRange(min=0,max=10)
	public Integer a;
	
	/** */
	@CSVColumn(columnIndex=1)
	@CSVByteType
	@CSVMaxByteLength(maxbytelength=10)
	@CSVMinByteLength(minbytelength=0)
	public byte b;

	/** */
	@CSVColumn(columnIndex=2)
	@CSVEmailType
	public String mail;

	/** */
	@CSVColumn(columnIndex=3)
	@CSVDateType
	public Date date;

	/** */
	@CSVColumn(columnIndex=4)
	@CSVFloatType
	@CSVFloatRange(min=0,max=10)
	public float f;
	
	/** */
	@CSVColumn(columnIndex=5)
	@CSVDoubleType
	public double d;
	
	/** */
	@CSVColumn(columnIndex=6)
	@CSVLongType
	@CSVLongRange(min=0,max=10)
	public long l;
	
	/** */
	@CSVColumn(columnIndex=7)
	@CSVShortType
	public short s;
	
	/** */
	@CSVColumn(columnIndex=8)
	@CSVMaxLength(maxlength=10)
	@CSVMinLength(minlength=0)
	public String str;
	
	/** */
	@CSVColumn(columnIndex=9)
	@CSVMask(mask="(.*)AAA(.*)")
	public String mask;
}
