package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.DefaultCSVParser;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;

import test.csv.TestCsv;

public class TestCSVErrExec extends S2CSVTestBase {

	static String csvComma1Data =
		"ヘ\"ッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04\r\n" +
		"hoge,hhhm,112,,,,20\090408\r\n";
	
	static String csvComma2Data =
		"ヘッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04\r\n" +
		"hoge,hhhm,112,,,,20\"090408\r\n";
	
    
    @Test
    public void testAAAA(){

		StringReader sr = new StringReader(csvComma1Data);
		
		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
				System.out.println("---");
				System.out.println(sp.getCurrentLineNo());
				System.out.println(sp.getCurrentLine());
			}
			
			fail();
		}catch(CSVFormatException e){
			assertEquals(0 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }

    @Test
    public void testAAAB(){

		StringReader sr = new StringReader(csvComma2Data);

		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
				System.out.println("---");
				System.out.println(sp.getCurrentLineNo());
				System.out.println(sp.getCurrentLine());
			}
		}catch(CSVFormatException e){
			assertEquals(2 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }
    
}
