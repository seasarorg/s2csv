package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.AAACsv;

/**
 * @author newta
 */
public class TestCSVMessage extends S2CSVTestBase {

	private String csvOKData = "20\r\n30\r\n";
	
	/** */
	@Test
	public void testRangeMsg(){
		

		StringReader sr = new StringReader(csvOKData);
		
		S2CSVParseCtrl<AAACsv> pc = S2CSVUtil.getCSVCtrlFactory().getParseController(AAACsv.class, sr);
		
		while(pc.readNext()){
			pc.parse();
			CSVValidateResult r = pc.getParseValidationResult();
			
			String msg = r.getMsgs().get(0).toString();
			
			assertEquals(msg,"a に範囲外の値が指定されています。0 から 10 の値を入力してください。");
		}
	}
}
