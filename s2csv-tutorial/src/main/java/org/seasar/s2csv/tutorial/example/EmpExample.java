package org.seasar.s2csv.tutorial.example;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.tutorial.service.EmpService;

public class EmpExample {

	//※ファイルが出力されます気をつけてください！
	public static String outPutFileName = "csv_out_emp";
	public static String outReadFileName = "csv_read_emp";
	public static String csvExt = ".csv";
	
	
	public EmpService empService;

	public static void main(String args[]) throws Exception{

		SingletonS2ContainerFactory.init();
		EmpExample example = SingletonS2Container.getComponent(EmpExample.class);
		
		//CSV出力処理1
		example.out1();

		//CSVバリデーションのケース1
		example.in1();

		//CSVバリデーションのケース2
		example.in2();


		//CSVバリデーションのチェック3 フォーマットエラーケース
		example.in3();
	}

	public void out1() throws Exception {
		empService.outCsv(outPutFileName + "1" + csvExt);
		System.out.println("[csv1]出力完了");
	}


	public void in1() throws Exception {
		empService.inCsv(outReadFileName + "1" + csvExt);
		System.out.println("[csv1]取込完了");
	}

	public void in2() throws Exception {
		empService.inCsv(outReadFileName + "2" + csvExt);
		System.out.println("[csv2]取込完了");
	}


	public void in3() throws Exception {
		try{
			empService.inCsv(outReadFileName + "3" + csvExt);
		}catch(CSVFormatException e){
			//e.printStackTrace();
			System.out.println(e.getErrorLineNo() + ":" + e.getMessage());
		}
		System.out.println("[csv3]取込完了");
	}
	
}