package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.AAACsv;

public class TestCSVMessage extends S2CSVTestBase {

	private String csvOKData = "20\r\n30\r\n";
	
	@Test
	public void testRangeMsg(){
		

		StringReader sr = new StringReader(csvOKData);
		
		S2CSVParseCtrl<AAACsv> pc = S2CSVUtil.getCSVCtrlFactory().getParseController(AAACsv.class, sr);
		
		while(pc.readNext()){
			CSVValidateResult r = pc.validate();
			
			System.out.println(r);
			
			System.out.println(r.getMsgs().get(0));
		}
	}
}
