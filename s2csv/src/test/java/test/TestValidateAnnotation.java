package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.BBBCsv;

/**
 * @author newta
 */
public class TestValidateAnnotation extends S2CSVTestBase {


	/** */
	@Test
	public void testValidateAnnotationOK() {

		StringReader sr = new StringReader("2,1,a@a.com,2000/11/12,1.1,2.2,3,4,aaa,bbAAAcc");

		S2CSVParseCtrl<BBBCsv> pc = S2CSVUtil.getCSVCtrlFactory().getParseController(
				BBBCsv.class, sr);

		pc.setValidateFlag(false);
		
		while (pc.readNext()) {
			System.out.println("---");
			CSVValidateResult resultList = pc.validate();
			System.out.println(pc.parse());

			if(resultList != null) {
				for (CSVMsg m : resultList.getMsgs()) {
					System.out.println(m.toString());
				}
				fail();
			}
		}
	}
	
	/** */
	@Test
	public void testValidateAnnotationBlank() {
		//ここはとりあえずExceptionがでなければOK

		StringReader sr = new StringReader(",,,,,,,,,");

		S2CSVParseCtrl<BBBCsv> pc = S2CSVUtil.getCSVCtrlFactory().getParseController(
				BBBCsv.class, sr);

		pc.setValidateFlag(false);
		
		while (pc.readNext()) {
			System.out.println("---");
			CSVValidateResult resultList = pc.validate();
			System.out.println(pc.parse());

			if(resultList != null) {
				for (CSVMsg m : resultList.getMsgs()) {
					System.out.println(m.toString());
				}
			}
		}
	}
}
