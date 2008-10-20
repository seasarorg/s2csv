package test.csv;

import java.util.Date;

import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVConvertor;
import org.seasar.s2csv.csv.annotation.column.CSVDateType;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.s2csv.csv.convertor.CSVDateConvertor;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;
/** */
@CSVEntity(header=true)
public class TestCsv {
	/** */
	public static final String hoMsg = "AAAA";
	/** */
	@CSVRequired
	@CSVColumn(columnIndex=0)
	public String hoge;
	
	/** */
	@CSVColumn(columnIndex=1)
	@CSVMaxLength(maxlength=10,args={"hoge2","10"})
	public String hoge2;
	/** */
	@CSVColumn(columnIndex=2,quote=false,columnName="ほげINT")
	public Integer hogeInt;
	
	/** */
	@CSVColumn(columnIndex=4)
	@CSVValidator(method="check", msgKey="errors.detail",args={"ほげほげエラー"})
	public String custamHoge;
	
	/** */
	@CSVDateType
//	@CSVColumn(columnIndex=3,columnName="ほげ日付",convertor=@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyy/MM/dd"))
	@CSVColumn(columnIndex=3,columnName="ほげ日付",convToObjMethod="hogeObj",convToCSVMethod="hogeCsvString")
	public Date hogeDate;
	/** */
	@CSVColumn(columnIndex=5, columnName="あいうえお")
	@CSVRequired//(args=hoMsg)
	public String req;
	/** */
	@CSVColumn(columnIndex=6, convertor=@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyyMMdd"))
	public Date hogeDate2;
	/**
	 * @param columnData 
	 * @return data */
	public Date hogeObj(String columnData){
		
		return new Date();
	}
	/**
	 * @param d 
	 * @return data */
	public String hogeCsvString(Date d){
		
		return "2222/02/02";
	}
	/**
	 * @param columndata 
	 * @return result */
	public boolean check(String columndata){
		
		if(columndata == null){
			//return true;
			//バリデーションエラーテスト
			throw new CSVValidationException();
		}
		
		return true;
	}
}
