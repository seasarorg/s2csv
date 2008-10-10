package test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.s2csv.csv.util.S2CSVUtil;

import test.csv.DeptCsv;
import test.entity.Dept;
import test.service.DeptService;


public class TestS2JDBC extends S2CSVTestBase {

	public DeptService deptService;
	
	@Test
	public void testHoge() throws IOException{
		
		AutoSelect<Dept> select = deptService.select();
		
		Writer writer = new StringWriter();
		S2CSVUtil.s2jdbcToCsv(
				DeptCsv.class,
				select,
				null,
				writer 
				);
		
		System.out.println(writer.toString());
		
	}
}
