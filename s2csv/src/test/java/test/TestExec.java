package test;

import java.io.StringReader;
import java.io.StringWriter;
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

import test.csv.HogeCsv;
import test.csv.MethodConfErrorCsv;
import test.csv.TestCsv;

public class TestExec extends S2CSVTestBase {
	
	static String csvData =
		"ヘッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04\r\n" +
		"hoge,hhhm,112,,,,20090408\r\n";
	
	static String csvOKData =
		"ヘッダ\r\n" +
		"hogehoge,hoe2,10,2008/01/04,abc,kkkk\r\n" +
		"\r\n" +
		"hoge,hhhm,112,,a,ccccc,20090408\r\n";
    
    @Test
    public void testAAAA(){

		StringReader sr = new StringReader(csvOKData);
		
		
		CSVParser sp =
		new DefaultCSVParser(sr,S2CSVDescUtil.getCSVEntityDesc(TestCsv.class));
		
		System.out.println(sp.getCurrentLineNo());
		System.out.println(sp.getCurrentLine());
		
		while (sp.isNext()) {
			sp.nextLine();
			System.out.println("---");
			System.out.println(sp.getCurrentLineNo());
			System.out.println(sp.getCurrentLine());
		}
		
    }
    
    @Test
    public void testValidationMethodConfErrorCSV(){

    	try{
			StringReader sr = new StringReader(csvOKData);
			
			S2CSVUtil.getCSVCtrlFactory().getParseController(MethodConfErrorCsv.class, sr);
			
			fail();
    	}catch(SRuntimeException e){
    		assertEquals(e.getMessageCode(),"ESSR0057");
    	}
    }
    
    
    @Test
    public void testServerConnectValidation(){

		StringReader sr = new StringReader(csvOKData);
		
		S2CSVParseCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory().getParseController(HogeCsv.class, sr);
		
		List<CSVValidateResult> list = c.validateAll();
		System.out.println(list.size());
		
		c.close();
    }
//    
//    @Test
//    public void testWriteCsvCheck(){
//
//		StringWriter sw = new StringWriter();
//		S2CSVWriteCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(HogeCsv.class, sw);
//
//		HogeCsv o = new HogeCsv();
//		
//		o.a = "aa,bb,cc\"";
//		o.b = "a\r\nb\rc\n";
//
//		//オブジェクトを行データに変換
//		
//		c.write(o);
//		c.close();
//		
//		System.out.println(sw.toString());
//		//TODO SuperCSVでは \n は無条件に \r\nになるようだ。(\r\nも\r\r\nになってしまう) どうなのよ？
//		assertEquals(sw.toString(),"\"aa,bb,cc\"\"\",\"a\r\r\nb\rc\r\n\"\r\n");
//    }
	  @Test
	  public void testWriteCsvCheck(){
	
			StringWriter sw = new StringWriter();
			S2CSVWriteCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(HogeCsv.class, sw);
	
			HogeCsv o = new HogeCsv();
			
			o.a = "aa,bb,cc\"";
			o.b = "a\r\nb\rc\n";
	
			//オブジェクトを行データに変換
			
			c.write(o);
			c.close();
			
			String s = sw.toString();
			
			//System.out.println(s);
			
			int p = 0;
			while(p < s.length()){
				char a = s.charAt(p);
				
				if(a == '\r'){
					System.out.print("\\r");
				}else if(a == '\n'){
					System.out.print("\\n");
				}else{
					System.out.print(a);
				}
				
				p++;
			}
			System.out.println();
			
			assertEquals(s,"\"aa,bb,cc\"\"\",\"a\r\nb\rc\n\"\r\n");
	  }
    
    @Test
    public void testHoge(){

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<HogeCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(HogeCsv.class, sw);

		HogeCsv o = new HogeCsv();
		
		o.a = "ああああ";
		o.b = "いいい";
		
		//オブジェクトを行データに変換
		
		c.write(o);
		c.close();
		
		System.out.println(sw.toString());
		assertEquals(sw.toString(),"\"ああああ\",\"いいい\"\r\n");
    }
	
//	@Test
//	public void testContainerDefCheck(){
//		
//		S2Container c = SingletonS2ContainerFactory.getContainer();
//		
//		System.out.println("----");
//		containerView(c);
//		
//		ComponentDef o = c.getComponentDef(CSVDescPool.class);
//		System.out.println(o.getComponentClass());
//		assertEquals(o.getComponentClass(),CSVDescPoolImpl.class);
//	}
//	
//	private void componentDefView(S2Container c){
//
//		int s = c.getComponentDefSize();
//		
//		for(int i=0; i < s;i++){
//			ComponentDef d = c.getComponentDef(i);
//			System.out.println(d.getComponentClass());
//			
//		}
//	}
//	
//	private void containerView(S2Container c){
//
//		componentDefView(c);
//		int s = c.getChildSize();
//
//		for(int i=0; i < s;i++){
//			containerView(c.getChild(i));
//		}
//	}
	
//	@Test
//	public void testCSVWriteError(){
//		
//		StringWriter sw = new StringWriter();
//		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(TestCsv.class, sw);
//
//		TestCsv o = new TestCsv();
//		
//		o.hoge = "ああああ";
//		o.hoge2 = "いいい";
//		o.hogeInt = Integer.valueOf(10);
//		o.hogeDate = new Date();
//		
//		c.close();
//		//オブジェクトを行データに変換
//		try{
//			c.write(o);
//			fail();
//		}catch(CSVWriteException e){
//			System.out.println(e.getMessage());
//			assertEquals(e.getMessage(),"[ESCSV0006]CSVを書き出すことができませんでした。");
//		}
//	}


	@Test
	public void testCSVWrite(){

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(TestCsv.class, sw);

		TestCsv o = new TestCsv();
		
		o.hoge = "ああああ";
		o.hoge2 = "いいい";
		o.hogeInt = Integer.valueOf(10);
		o.hogeDate = new Date();
		
		//オブジェクトを行データに変換
		
		c.write(o);
		c.close();
		
		System.out.println(sw.toString());
		
		String check = 	"\"hoge\",\"hoge2\",ほげINT,\"ほげ日付\",\"custamHoge\",\"あいうえお\",\"hogeDate2\"\r\n" +
						"\"ああああ\",\"いいい\",10,\"2222/02/02\",,,\r\n";
		
		if(check.equals(sw.toString())){
			System.out.println("OK");
		}
		
		assertEquals(sw.toString(),check);
	}
    
//	@Test
//	public void testCSVWrite(){
//
//		StringWriter sw = new StringWriter();
//		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(TestCsv.class, sw);
//
//		TestCsv o = new TestCsv();
//		
//		o.hoge = "ああああ";
//		o.hoge2 = "いいい";
//		o.hogeInt = Integer.valueOf(10);
//		o.hogeDate = new Date();
//		
//		//オブジェクトを行データに変換
//		
//		c.write(o);
//		c.close();
//		
//		System.out.println(sw.toString());
//		
//		String check = 	"hoge,hoge2,ほげINT,ほげ日付,custamHoge,あいうえお,hogeDate2\r\n" +
//						"ああああ,いいい,10,2222/02/02,null,null,null\r\n";
//		
//		if(check.equals(sw.toString())){
//			System.out.println("OK");
//		}
//		
//		assertEquals(sw.toString(),check);
//	}
	
	@Test
	public void testCSVtoObj(){
		
		StringReader sr = new StringReader(csvOKData);
		
		S2CSVParseCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getParseController(TestCsv.class, sr);
		
		List<TestCsv> list;
		try {
			list = c.parseAll();
		} catch (CSVValidationResultException e) {
			throw new RuntimeException(e);
		}
		c.close();
		
		
		int i=0;
		for(TestCsv o:list){
			System.out.println("-結果データ:" + i++);
			System.out.println(o.hoge);
			System.out.println(o.hoge2);
			System.out.println(o.hogeInt);
			System.out.println(o.hogeDate);
		}
	}
	
	@Test
	public void testCSVValidation(){

		StringReader sr = new StringReader(csvData);
		
		S2CSVParseCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getParseController(TestCsv.class, sr);
		
		List<CSVValidateResult> resultList = c.validateAll();

		int i=0;
		for(CSVValidateResult r:resultList){
			System.out.println("-結果データ:" + i++);
			System.out.println("データ :[" + r.dataToString() + "]");
			for(CSVMsg m : r.getMsgs()){
				System.out.println(m);
			}
		}
	}
	
	@Test
	public void testObjtoCSV(){

		StringWriter sw = new StringWriter();
		S2CSVWriteCtrl<TestCsv> c = S2CSVUtil.getCSVCtrlFactory().getWriteController(TestCsv.class, sw);

		TestCsv o = new TestCsv();
		
		o.hoge = "ああああ";
		o.hoge2 = "いいい";
		o.hogeInt = Integer.valueOf(10);
		o.hogeDate = new Date();
		
		//オブジェクトを行データに変換
		c.write(o);
		c.close();
		
		System.out.println(sw.toString());
	}
//	
////
////	public static void testOBJValidation(){
////
////		//めんどいので自分でオブジェクトに変換したものを使う
////		String testCsvLine = "hogehoge,hoe2,10,2008/01/04,,,20090408";
////		CSVConvertController maker = new CSVConvertController();
////		TestBO o = maker.covToObj(TestBO.class, testCsvLine);
////
////		CSVValidateService validator = new CSVValidateService();
////		//オブジェクトに入ったデータをCSVバリデーションチェック（たぶん普通は使わないよね。）
////		System.out.println("オブジェクトバリデーションチェック■■■■■■■■■■■■■■■■■■■■■■■");
////		validator.validate(o);
////
////	}
//
//	public static void testCSVValidation(){
//
//		CSVController csvCtl = new CSVController(csvData);
//		
//		//行データをオブジェクトの設定でバリデーションチェック
//		System.out.println("CSV行バリデーションチェック■■■■■■■■■■■■■■■■■■■■■■■");
//		
//		
//		List<CSVValidateResult> list = csvCtl.validateCSV(TestBO.class);
//
//		for(CSVValidateResult o : list){
//			logValidationResult(o);
//		}
//	}
//	
//	public static void logValidationResult(CSVValidateResult o){
//		System.out.println("-結果データ");
//		
//		if(o == null){
//			System.out.println("バリデーションエラー無し");
//			return;
//		}
//		System.out.print(padR(String.valueOf(o.rowNo),10));
//		System.out.print("[" + padR(o.dataToString(),50) + "]");
//
//		for(CSVMsg msg : o.msgs){
//			System.out.println();
//			System.out.print(section(msg.toString(),35));
//		}
//		System.out.println();
//		
//	}
//	
//
//	private static String section(String s, int len){
//
//		String result = s;
//		if(s == null){
//			result = "";
//		}
//		
//		for(int i=0;i < len;i++){
//			result = " " + result;
//		}
//		
//		return result;
//	}
//	
//	private static String padR(String s, int len){
//		
//		String result = s;
//		if(s == null){
//			result = "";
//		}
//		
//		while(result.length() < len){
//			result += " ";
//		}
//		
//		return result;
//	}
//	
//	public static void main(String[] args){
//		
//		testCSVtoObj();
//		
//		testOBJtoCSV();
//		
//		testCSVValidation();
//		
//		hogehogeGyoumu();
//		
//		System.out.println("終わり");
//	}
//	
//	
//	private static void hogehogeGyoumu(){
//		System.out.println("hogehogeGyoumu■■■■■■■■■■■■■■■■■■■■■■■");
//
//		CSVController csvCtl = new CSVController(csvOKData);
//
//		List<CSVValidateResult> list = csvCtl.validateCSV(TestBO.class);
//		
//		if(list.size() >0){
//			//バリデーションエラー処理
//			for(CSVValidateResult o : list){
//				logValidationResult(o);
//			}
//		}else{
//			//オブジェクト変換処理
//			List<TestBO> objList = csvCtl.parseCSV(TestBO.class);
//			int i = 0;
//			for(TestBO o : objList){
//				System.out.println("-結果データ:" + i++);
//				System.out.println(o.hoge);
//				System.out.println(o.hoge2);
//				System.out.println(o.hogeInt);
//				System.out.println(o.hogeDate);
//			}
//			
//		}
//	}
}
