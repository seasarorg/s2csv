package test;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.command.S2CSVCommand;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.TestCsv;

/**
 * @author newta
 */
public class TestExecCustom extends S2CSVTestBase {


	static String csvOKData = "ヘッダ,,,,,,\r\n"
			+ "hogehoge,hoe2,10,2008/01/04,abc,kkkk,\r\n" + "\r\n"
			+ "hoge,hhhm,112,2008/03/04,,ccccc,\r\n";

	/** */
	@Test
	public void testCSVtoObj() {

		StringReader sr = new StringReader(csvOKData);

		S2CSVParseCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(TestCsv.class, sr);

		List<S2CSVCommand> commands = c.getCommands("hogeDate");
		
		
		List<TestCsv> list = new ArrayList<TestCsv>();
		try {
			while(c.readNext()){
				TestCsv o = c.parse();
				list.add(o);
			}
			commands.clear();
		} catch (CSVValidationResultException e) {
			throw new RuntimeException(e);
		}
		
		List<CSVValidateResult> validationResults = c.getValidationResultAll();
		
		if(validationResults.isEmpty() == false){
			System.out.println("エラーあり");
			for (CSVValidateResult validateResult : validationResults) {
				List<CSVMsg> msgs = validateResult.getMsgs();
				for (CSVMsg msg : msgs) {
					System.out.println(msg.toString());
				}
			}
		}

		int i = 0;
		for (TestCsv o : list) {
			System.out.println("-結果データ:" + i++);
			System.out.println(o.hoge);
			System.out.println(o.hoge2);
			System.out.println(o.hogeInt);
			
			DateFormat sdf = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM);
			System.out.println(sdf.format(o.hogeDate));
			if(sdf.format(o.hogeDate).equals(sdf.format(new Date())) == false){
				//fail();
			}
		}
	}
}
