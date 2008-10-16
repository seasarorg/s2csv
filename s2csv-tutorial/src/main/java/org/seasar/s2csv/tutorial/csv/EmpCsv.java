package org.seasar.s2csv.tutorial.csv;

import java.math.BigDecimal;
import java.util.Date;

import org.seasar.framework.util.StringUtil;
import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVConvertor;
import org.seasar.s2csv.csv.annotation.column.CSVDateType;
import org.seasar.s2csv.csv.annotation.column.CSVDoubleType;
import org.seasar.s2csv.csv.annotation.column.CSVIntegerType;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.s2csv.csv.annotation.entity.CSVRecordValidator;
import org.seasar.s2csv.csv.convertor.CSVDateConvertor;
import org.seasar.s2csv.tutorial.entity.Dept;
import org.seasar.s2csv.tutorial.service.DeptService;


@CSVEntity(header=true)
@CSVRecordValidator(msgKey="error.emp.recordErr",args={CSVColumn.REPLACE_LINE_NO},method="interrelationCheck")
public class EmpCsv {

	@CSVColumn(columnIndex=0,columnName="ID",quote=false)
	public Long id;

	@CSVColumn(columnIndex=1,columnName="従業員NO",quote=false)
	@CSVRequired
	@CSVIntegerType
	public Integer empNo;

	@CSVColumn(columnIndex=2,columnName="従業員名")
	@CSVRequired
	public String empName;

	@CSVColumn(columnIndex=3,columnName="マネージャID",quote=false)
	public Integer mgrId;

	@CSVColumn(columnIndex=4,columnName="入社日",convertor=@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyy/MM/dd"))
	@CSVDateType(datePattern="yyyy/mm/dd")
	public Date hiredate;

	@CSVDoubleType
	@CSVColumn(columnIndex=5,quote=false,convToCSVMethod="salCovToCsv",convToObjMethod="salCovToObj")
	public BigDecimal sal;

	@CSVColumn(columnIndex=6,quote=false)
	@CSVValidator(msgKey="error.emp.nodept",args={CSVColumn.REPLACE_LINE_NO},method="deptCheck")
	public Integer deptId;

	@CSVColumn(columnIndex=7,quote=false)
	public Integer versionNo;

	
	public DeptService deptService;
	
	//存在チェックバリデーション
	public boolean deptCheck(String deptIdStr){
		
		Long deptId = Long.valueOf(deptIdStr);
		
		Dept d = deptService.findById(deptId);
		
		if(d == null){
			return false;
		}
		
		return true;
	}
	
	//相関チェック
	public boolean interrelationCheck(String cols[]){
		
		//マネージャIDが設定されていて
		//入社日が設定されていない場合エラー
		if(StringUtil.isBlank(cols[3]) == false &&
				StringUtil.isBlank(cols[4]) == true
				){
			return false;
		}
		
		return true;
	}
	
	//オブジェクトへの変換
	public BigDecimal salCovToObj(String salStr){
		
		if(StringUtil.isBlank(salStr)){
			return null;
		}
		
		BigDecimal d = new BigDecimal(salStr);
		
		return d;
	}
	
	//CSVへの変換
	public String salCovToCsv(BigDecimal sal){
		
		if(sal == null){
			return null;
		}
		return String.valueOf(sal);
	}
}