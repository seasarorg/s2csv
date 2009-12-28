package test;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.seasar.framework.exception.SRuntimeException;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultException;
import org.seasar.s2csv.csv.io.CSVParser;
import org.seasar.s2csv.csv.io.DefaultCSVParser;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

import test.csv.Hoge4Csv;
import test.csv.Hoge5Csv;
import test.csv.Hoge6Csv;
import test.csv.Hoge7Csv;
import test.csv.HogeCsv;
import test.csv.MethodConfErrorCsv;
import test.csv.TestCsv;

/**
 * @author newta
 */
public class TestExec extends S2CSVTestBase {

	static String csvData = "ヘッダ,,,,,,\r\n"
			+ "hogehoge,hoe2,10,2008/01/04,,,\r\n"
			+ "hoge,hhhm,112,,,,20090408\r\n";

	static String csvOKData = "ヘッダ,,,,,,\r\n"
			+ "hogehoge,hoe2,10,2008/01/04,abc,kkkk,\r\n" + "\r\n"
			+ "hoge,hhhm,112,,a,ccccc,20090408\r\n";

	/** */
	@Test
	public void testParseOnly() {

		StringReader sr = new StringReader(csvOKData);

		CSVParser sp = new DefaultCSVParser(sr, S2CSVDescUtil
				.getCSVEntityDesc(TestCsv.class));

		System.out.println(sp.getCurrentLineNo());
		System.out.println(sp.getCurrentLine());

		while (sp.isNext()) {
			sp.nextLine();
			System.out.println("---");
			System.out.println(sp.getCurrentLineNo());
			System.out.println(sp.getCurrentLine());
		}
	}
	/** */
	@Test
	public void testEmptyHederColumn(){

		StringReader sr = new StringReader("ヘッダ,\"\",,,,,\r\n");

		CSVParser sp = new DefaultCSVParser(sr, S2CSVDescUtil
				.getCSVEntityDesc(TestCsv.class));

		while (sp.isNext()) {
			sp.nextLine();
		}
	}

	/** */
	@Test
	public void testEmptyColumn(){

		StringReader sr = new StringReader("ヘッダ,,,,,,\r\n" +
				"hogehoge,,\"\",2008/01/04,,\"\",\r\n");
		String[][] check = {{"ヘッダ","","","","","","",},{"hogehoge","","","2008/01/04","","",""}};

		CSVParser sp = new DefaultCSVParser(sr, S2CSVDescUtil
				.getCSVEntityDesc(TestCsv.class));

		int checkIndex = 0;
		while (sp.isNext()) {
			String[] test = sp.nextLine();
			
			for(int i=0;i<test.length;i++){
				String a = test[i];
				String b = check[checkIndex][i];
				
				assertEquals(a,b);
			}
			
			checkIndex++;
		}
	}

	/** */
	@Test
	public void testValidationMethodConfErrorCSV() {

		try {
			StringReader sr = new StringReader(csvOKData);

			S2CSVUtil.getCSVCtrlFactory().getParseController(
					MethodConfErrorCsv.class, sr);

			fail();
		} catch (SRuntimeException e) {
			assertEquals(e.getMessageCode(), "ESSR0057");
		}
	}

	/** */
	@Test
	public void testServerConnectValidation() {

		StringReader sr = new StringReader(csvOKData);

		S2CSVParseCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(HogeCsv.class, sr);

		c.parseAll();
		List<CSVValidateResult> resultList = c.getValidationResultAll();
		System.out.println(resultList.size());

		c.close();
	}

	/** */
	@Test
	public void testWriteCsvCheck() {

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getWriteController(HogeCsv.class, sw);

		HogeCsv o = new HogeCsv();

		o.a = "aa,bb,cc\"";
		o.b = "a\r\nb\rc\n";

		// オブジェクトを行データに変換

		c.write(o);
		c.close();

		String s = sw.toString();

		// System.out.println(s);

//		int p = 0;
//		while (p < s.length()) {
//			char a = s.charAt(p);
//
//			if (a == '\r') {
//				System.out.print("\\r");
//			} else if (a == '\n') {
//				System.out.print("\\n");
//			} else {
//				System.out.print(a);
//			}
//
//			p++;
//		}
//		System.out.println();

		assertEquals(s, "\"aa,bb,cc\"\"\",\"a\r\nb\rc\n\"\r\n");
	}

	/** */
	@Test
	public void testHoge() {

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getWriteController(HogeCsv.class, sw);

		HogeCsv o = new HogeCsv();

		o.a = "ああああ";
		o.b = "いいい";

		// オブジェクトを行データに変換

		c.write(o);
		c.close();

		System.out.println(sw.toString());
		assertEquals(sw.toString(), "\"ああああ\",\"いいい\"\r\n");
	}

	// @Test
	// public void testContainerDefCheck(){
	//		
	// S2Container c = SingletonS2ContainerFactory.getContainer();
	//		
	// System.out.println("----");
	// containerView(c);
	//		
	// ComponentDef o = c.getComponentDef(CSVDescPool.class);
	// System.out.println(o.getComponentClass());
	// assertEquals(o.getComponentClass(),CSVDescPoolImpl.class);
	// }
	//	
	// private void componentDefView(S2Container c){
	//
	// int s = c.getComponentDefSize();
	//		
	// for(int i=0; i < s;i++){
	// ComponentDef d = c.getComponentDef(i);
	// System.out.println(d.getComponentClass());
	//			
	// }
	// }
	//	
	// private void containerView(S2Container c){
	//
	// componentDefView(c);
	// int s = c.getChildSize();
	//
	// for(int i=0; i < s;i++){
	// containerView(c.getChild(i));
	// }
	// }

	// @Test
	// public void testCSVWriteError(){
	//		
	// StringWriter sw = new StringWriter();
	// S2CSVWriteCtrl<TestCsv> c =
	// S2CSVUtil.getCSVCtrlFactory().getWriteController(TestCsv.class, sw);
	//
	// TestCsv o = new TestCsv();
	//		
	// o.hoge = "ああああ";
	// o.hoge2 = "いいい";
	// o.hogeInt = Integer.valueOf(10);
	// o.hogeDate = new Date();
	//		
	// c.close();
	// //オブジェクトを行データに変換
	// try{
	// c.write(o);
	// fail();
	// }catch(CSVWriteException e){
	// System.out.println(e.getMessage());
	// assertEquals(e.getMessage(),"[ESCSV0006]CSVを書き出すことができませんでした。");
	// }
	// }

	/** */
	@Test
	public void testCSVWrite() {

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getWriteController(TestCsv.class, sw);

		TestCsv o = new TestCsv();

		o.hoge = "ああああ";
		o.hoge2 = "いいい";
		o.hogeInt = Integer.valueOf(10);
		o.hogeDate = new Date();

		// オブジェクトを行データに変換

		c.write(o);
		c.close();

		System.out.println(sw.toString());

		String check = "\"hoge\",\"hoge2\",ほげINT,\"ほげ日付\",\"custamHoge\",\"あいうえお\",\"hogeDate2\"\r\n"
				+ "\"ああああ\",\"いいい\",10,\"2222/02/02\",,,\r\n";

		if (check.equals(sw.toString())) {
			System.out.println("OK");
		}

		assertEquals(sw.toString(), check);
	}

	/** */
	@Test
	public void testCSVtoObj() {

		StringReader sr = new StringReader(csvOKData);

		S2CSVParseCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(TestCsv.class, sr);

		List<TestCsv> list;
		try {
			list = c.parseAll();
		} catch (CSVValidationResultException e) {
			throw new RuntimeException(e);
		}
		c.close();

		int i = 0;
		for (TestCsv o : list) {
			System.out.println("-結果データ:" + i++);
			System.out.println(o.hoge);
			System.out.println(o.hoge2);
			System.out.println(o.hogeInt);
			
			DateFormat sdf = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM);
			System.out.println(o.hogeDate);
			if(sdf.format(o.hogeDate).equals(sdf.format(new Date())) == false){
				fail();
			}
		}
	}

	/** */
	@Test
	public void testCSVValidation() {

		StringReader sr = new StringReader(csvData);

		S2CSVParseCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(TestCsv.class, sr);

		c.parseAll(1);
		List<CSVValidateResult> resultList = c.getValidationResultAll();

		//int i = 0;
		for (CSVValidateResult r : resultList) {
//			System.out.println("-結果データ:" + i++);
//			System.out.println("データ :[" + r.dataToString() + "]");
			for (CSVMsg m : r.getMsgs()) {
				assertEquals("あいうえお は必須項目です。値を入力してください。", m.toString());
			}
		}
	}

	/** */
	@Test
	public void testObjtoCSV() {

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory()
				.getWriteController(TestCsv.class, sw);

		TestCsv o = new TestCsv();

		o.hoge = "ああああ";
		o.hoge2 = "いいい";
		o.hogeInt = Integer.valueOf(10);
		o.hogeDate = new Date();

		// オブジェクトを行データに変換
		c.write(o);
		c.close();

//		System.out.println(sw.toString());
		assertEquals("\"hoge\",\"hoge2\",ほげINT,\"ほげ日付\",\"custamHoge\",\"あいうえお\",\"hogeDate2\"\r\n" + 
				"\"ああああ\",\"いいい\",10,\"2222/02/02\",,,\r\n",sw.toString());
	}
	
	/** */
	@Test
	public void testCSVValidationExceptionMessage(){

		StringReader sr = new StringReader(csvData);
		S2CSVParseCtrl<Hoge4Csv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(Hoge4Csv.class, sr);

		c.parseAll();
		List<CSVValidateResult> resultList = c.getValidationResultAll();

//		int i = 0;
		for (CSVValidateResult r : resultList) {
//			System.out.println("-結果データ:" + i++);
//			System.out.println("データ :[" + r.dataToString() + "]");
			for (CSVMsg m : r.getMsgs()) {
//				System.out.println(m);
				assertEquals(m.toString(),"Dept Validate Check Msg");
			}
		}
	}
	

	/** */
	@Test
	public void testCSVValidationExceptionSetMessage1(){

		StringReader sr = new StringReader(csvData);
		S2CSVParseCtrl<Hoge5Csv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(Hoge5Csv.class, sr);

		c.parseAll();
		List<CSVValidateResult> resultList = c.getValidationResultAll();

//		int i = 0;
		for (CSVValidateResult r : resultList) {
//			System.out.println("-結果データ:" + i++);
//			System.out.println("データ :[" + r.dataToString() + "]");
			for (CSVMsg m : r.getMsgs()) {
				System.out.println(m);
				assertEquals(m.toString(),"costom validation error");
			}
		}
	}
	
	/** */
	@Test
	public void testCSVValidationExceptionSetMessage2(){

		StringReader sr = new StringReader(csvData);
		S2CSVParseCtrl<Hoge6Csv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(Hoge6Csv.class, sr);

		c.parseAll();
		List<CSVValidateResult> resultList = c.getValidationResultAll();

//		int i = 0;
		for (CSVValidateResult r : resultList) {
//			System.out.println("-結果データ:" + i++);
//			System.out.println("データ :[" + r.dataToString() + "]");
			for (CSVMsg m : r.getMsgs()) {
				System.out.println(m);
				assertEquals(m.toString(),"バリデーションエラー:costom validation error2");
			}
		}
	}

	/** */
	@Test
	public void testCSVNoDefineColumn(){
		//TODO 書き出しテストも作る
		String csvDataB = "1,2,3,4,5,6,7,8,9,0,1b,2,3,4,5,6,7,8,9,0,1a,2,3,4,5,6,7,8,9,0\r\n";
		StringReader sr = new StringReader(csvDataB);

		S2CSVParseCtrl<Hoge7Csv> c = S2CSVUtil.getCSVCtrlFactory()
				.getParseController(Hoge7Csv.class, sr);

		List<Hoge7Csv> parseAll = c.parseAll();
		
		for (Hoge7Csv hoge7Csv : parseAll) {
			
			System.out.println(hoge7Csv);
		}
	}
}
