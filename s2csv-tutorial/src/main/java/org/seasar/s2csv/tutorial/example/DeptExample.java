package org.seasar.s2csv.tutorial.example;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.s2csv.tutorial.service.DeptService;

public class DeptExample {

	public DeptService deptService;
	
	//※ファイルが出力されます気をつけてください！
	public static String outPutFileName = "csv_out_dept";
	public static String outReadFileName = "csv_read_dept";
	public static String csvExt = ".csv";
	
	public static void main(String args[]) throws Exception{

		SingletonS2ContainerFactory.init();
		DeptExample example = SingletonS2Container.getComponent(DeptExample.class);
		
		//CSV出力処理1
		example.out1();

		//CSV出力処理2
		example.out2();

		//CSV取込処理1
		example.in1();

		//CSV取込処理2
		example.in2();

		//CSV取込処理3
		example.in3();

		//CSV取込処理4
		example.in4();
	}

	public void out1() throws Exception {
		deptService.outCsv1(outPutFileName + "1" + csvExt);
		System.out.println("[csv1]出力完了");
	}

	public void out2() throws Exception {
		deptService.outCsv2(outPutFileName + "2" + csvExt);
		System.out.println("[csv2]出力完了");
	}

	public void in1() throws Exception {
		deptService.inCsv1(outReadFileName + "1" + csvExt);
		System.out.println("[csv1]取込完了");
	}

	public void in2() throws Exception {
		deptService.inCsv2(outReadFileName + "2" + csvExt);
		System.out.println("[csv2]取込完了");
	}

	public void in3() throws Exception {
		deptService.inCsv3(outReadFileName + "3" + csvExt);
		System.out.println("[csv3]取込完了");
	}

	public void in4() throws Exception {
		deptService.inCsv4(outReadFileName + "4" + csvExt);
		System.out.println("[csv4]取込完了");
	}
	
}