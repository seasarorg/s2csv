package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.DefaultCSVParser;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;

import test.csv.TestCsv;

/**
 * @author newta
 */
public class TestCSVErrExec extends S2CSVTestBase {

	static String csvComma1Data =
		"ヘ\"ッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04\r\n" +
		"hoge,hhhm,112,,,,20\090408\r\n";
	
	static String csvComma2Data =
		"ヘッダ,,,,,,\r\n" +
		"hogehoge,hoe2,10,2008/01/04,,,\r\n" +
		"hoge,hhhm,112,,,,20\"090408\r\n";
	
    
	/** */
    @Test
    public void testHeaderRecordFormatError(){

		StringReader sr = new StringReader(csvComma1Data);
		
		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
			}
			
			fail();
		}catch(CSVFormatException e){
			assertEquals(1 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }

	/** */
    @Test
    public void testHeaderCountError(){

		StringReader sr = new StringReader("へっだ,");

		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
			}
		}catch(CSVFormatException e){
			assertEquals(1 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }

	/** */
    @Test
    public void testColumnCountError(){

		StringReader sr = new StringReader("ヘッダ,,,,,,\r\n" +
				"a,b,c");

		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
			}
		}catch(CSVFormatException e){
			assertEquals(2 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }

	/** */
    @Test
    public void testNoEndOfQuoteError(){

		StringReader sr = new StringReader(csvComma2Data);

		try{
		
			CSVParser sp =
			new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
			
			while (sp.isNext()) {
				sp.nextLine();
			}
		}catch(CSVFormatException e){
			assertEquals(3 ,e.getErrorLineNo());
			assertEquals("ESCSV0009" ,e.getMessageCode());
		}
    }
    
}
