package test;

import java.io.StringReader;

import org.junit.Test;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.Hoge2Csv;
import test.csv.Hoge3Csv;

/**
 * @author newta
 */
public class TestRecordValidate extends S2CSVTestBase {
	
	static String csvData =
		"ヘッダ\r\n" +
		"1,hoe2,10,2008/01/04\r\n" +
		"2,hhhm,112,,,,20090408\r\n";
	
	static String csvOKData =
		"ヘッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04,abc,kkkk\r\n" +
		"\r\n" +
		"hoge,hhhm,112,,a,ccccc,20090408\r\n";
    
	/** */
    @Test
    public void testRecordVaildate(){

		StringReader sr = new StringReader(csvOKData);
		
		S2CSVCtrlFactory factory = S2CSVUtil.getCSVCtrlFactory();
		
		S2CSVParseCtrl<Hoge2Csv> sp = factory.getParseController(Hoge2Csv.class,sr);
		
		while (sp.readNext()) {
			sp.parse();
			CSVValidateResult vr = sp.getParseValidationResult();
			if(vr != null){
				for(CSVMsg msg : vr.getMsgs()){
//					System.out.println(msg);
					assertEquals(msg.toString(), "Record Validate Check Msg");
				}
			}else{
				fail();
			}
		}
    }
    /** */
    @Test
    public void testRecordVaildateAno(){

		StringReader sr = new StringReader(csvOKData);
		
		S2CSVParseCtrl<Hoge3Csv> sp = S2CSVUtil.getCSVCtrlFactory().getParseController(Hoge3Csv.class,sr);
		
		while (sp.readNext()) {
			sp.parse();
			CSVValidateResult vr = sp.getParseValidationResult();
			if(vr != null){
				boolean f = true;
				for(CSVMsg msg : vr.getMsgs()){
//					System.out.println(msg);
					if(f){
						assertEquals(msg.toString(), "Record Validate Check Msg");
					}else{
						assertEquals(msg.toString(), "Record Validate Check2 Msg");
					}
					
					f = !f;
				}
			}else{
				fail();
			}
		}
    }
}
