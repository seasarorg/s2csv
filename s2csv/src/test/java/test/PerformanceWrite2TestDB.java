package test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.seasar.extension.jdbc.Select;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.util.S2CSVUtil;

import test.csv.ApartmentCsv;
import test.entity.Apartment;
import test.service.ApartmentService;

public class PerformanceWrite2TestDB extends S2CSVTestBase {

	private DateFormat f = DateFormat.getTimeInstance(DateFormat.LONG);
	
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
	@Test
	public void testList(){

		System.out.println("■testList");
		time_start();
		ApartmentService s = 
		SingletonS2Container.getComponent(ApartmentService.class);
		
		List<Apartment> list =
			s.select().limit(10000).getResultList();
		
		for(Apartment a :list){
			System.out.println(a);
		}
		time_end();
	}

	@Test
	public void testWrite10000_1() throws IOException{
		//結果 :7秒  h2 to csv  
		System.out.println("■testWrite10000");
		ApartmentService service = 
			SingletonS2Container.getComponent(ApartmentService.class);
		
		
		FileWriter writer = new FileWriter("C:\\csv_write2_test10000.csv");
		
		time_start();
		Select<Apartment,?> select = service.select().limit(10000);
		S2CSVUtil.s2jdbcToCsv(ApartmentCsv.class,select,null,writer);
		time_end();
	}
	
	protected void time_start(){
		System.out.println("START----" + f.format(new Date()));
	}
	
	protected void time_end(){
		
		System.out.println("END----" + f.format(new Date()));
	}
}
