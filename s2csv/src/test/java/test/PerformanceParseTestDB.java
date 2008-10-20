package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.util.S2CSVUtil;

import test.csv.ApartmentCsv;
import test.entity.Apartment;
import test.service.ApartmentService;

/**
 * @author newta
 */
public class PerformanceParseTestDB extends S2CSVTestBase {

	private DateFormat f = DateFormat.getTimeInstance(DateFormat.LONG);
	
	/** */
	@Test
	public void testConnection(){

		time_start();
		ApartmentService s = 
		SingletonS2Container.getComponent(ApartmentService.class);
		
		Apartment a =
			s.select().limit(1).getSingleResult();
		
		System.out.println(a);
		time_end();
	}

	/** */
	@Test
	public void testParse10000() {
		//結果 : 24秒  csv to H2  not insert 5秒
		System.out.println("■testParse10000");
		
		FileReader reader;
		try {
			reader = new FileReader("C:\\csv_write_test10000.csv");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		time_start();
		S2CSVUtil.csvToS2Jdbc(ApartmentCsv.class,Apartment.class,null,reader);
		time_end();
	}
	
	protected void time_start(){
		System.out.println("START----" + f.format(new Date()));
	}
	
	protected void time_end(){
		
		System.out.println("END----" + f.format(new Date()));
	}
}
