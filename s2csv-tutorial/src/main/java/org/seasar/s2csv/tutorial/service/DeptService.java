package org.seasar.s2csv.tutorial.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.seasar.framework.beans.util.Beans;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;
import org.seasar.s2csv.tutorial.csv.DeptCsv;
import org.seasar.s2csv.tutorial.entity.Dept;
import org.seasar.s2csv.tutorial.system.CSVExampleUtil;

public class DeptService extends AbstractService<Dept> {

	/**
	 * 自動DIされます。
	 * CSVコントローラを作るファクトリです
	 */
	public S2CSVCtrlFactory csvCtrlFactory;
	
    public Dept findById(Long id) {
        return select().id(id).getSingleResult();
    }
    
    /**
     * DeptテーブルのデータをCSV形式で出力する
     * @param fileName
     * @throws IOException
     */
    public void outCsv1(String fileName) throws IOException {
    	Writer writer = new FileWriter(fileName);
    	
    	S2CSVWriteCtrl<DeptCsv> csv_writer = 
    		csvCtrlFactory.getWriteController(DeptCsv.class, writer);
    	
    	List<Dept> all = super.findAll();
    	
    	for(Dept o : all){
    		
    		DeptCsv deptCsv = Beans.createAndCopy(DeptCsv.class, o).execute();

        	csv_writer.write(deptCsv);
    	}
    	
    	csv_writer.close();
    }
    
    /**
     * DeptテーブルのデータをCSV形式で出力する
     * @param fileName
     * @throws IOException
     */
    public void outCsv2(String fileName) throws IOException {

    	Writer writer = new FileWriter(fileName);
    	S2CSVUtil.s2jdbcToCsv(DeptCsv.class, select(), writer);
    }
    
    /**
     * Deptテーブルのデータを削除し、
     * CSVファイルデータをDeptテーブルに登録する。
     * @param fileName
     * @throws FileNotFoundException
     */
    public void inCsv1(String fileName) throws FileNotFoundException {
    	
    	Reader reader = new FileReader(fileName);
    	jdbcManager.callBySql("delete from dept").execute();
    	
    	 S2CSVParseCtrl<DeptCsv> csv_parser = 
    		 csvCtrlFactory.getParseController(DeptCsv.class, reader);
    	 
    	 //parse時のバリデーションチェックをオフにして
    	 //自分でvalidateメソッドを呼ぶ
    	 csv_parser.setValidateFlag(false);
    	 
    	 while(csv_parser.readNext()){
    		 CSVValidateResult validateResult = csv_parser.validate();
    		 
    		 if(validateResult != null){
    			 CSVExampleUtil.showErrMessage(validateResult);
    			continue;
    		 }
    		 
    		 DeptCsv deptCsv = csv_parser.parse();
    		 
    		 Dept dept = Beans.createAndCopy(Dept.class,deptCsv).execute();
    		 
    		 insert(dept);
    	 }
    }
    /**
     * Deptテーブルのデータを削除し、
     * CSVファイルデータをDeptテーブルに登録する。
     * @param fileName
     * @throws FileNotFoundException
     */
    public void inCsv2(String fileName) throws FileNotFoundException {
    	
    	Reader reader = new FileReader(fileName);
    	jdbcManager.callBySql("delete from dept").execute();
    	
    	 S2CSVParseCtrl<DeptCsv> csv_parser = 
    		 csvCtrlFactory.getParseController(DeptCsv.class, reader);
    	 
    	 while(csv_parser.readNext()){
    		 
    		 try{
	    		 DeptCsv deptCsv = csv_parser.parse();
	    		 Dept dept = Beans.createAndCopy(Dept.class,deptCsv).execute();
	    		 insert(dept);
	    		 
    		 }catch(CSVValidationResultRuntimeException e){
    			 CSVExampleUtil.showErrMessage(e.getValidateResult());
    		 }
    	 }
    }
    /**
     * Deptテーブルのデータを削除し、
     * CSVファイルデータをDeptテーブルに登録する。
     * @param fileName
     * @throws FileNotFoundException
     */
    public void inCsv3(String fileName) throws FileNotFoundException {
    	
    	Reader reader = new FileReader(fileName);
    	jdbcManager.callBySql("delete from dept").execute();
    	
    	
    	List<CSVValidateResult> result =
    		S2CSVUtil.csvValidateToS2Jdbc(DeptCsv.class,Dept.class,reader);
    	
    	
    	CSVExampleUtil.showErrMessage(result);
    }

    /**
     * Deptテーブルのデータを削除し、
     * CSVファイルデータをDeptテーブルに登録する。
     * @param fileName
     * @throws FileNotFoundException
     */
    public void inCsv4(String fileName) throws FileNotFoundException {
    	
    	Reader reader = new FileReader(fileName);
    	jdbcManager.callBySql("delete from dept").execute();
    	
    	S2CSVUtil.csvToS2Jdbc(DeptCsv.class,Dept.class,reader);
    }
    
    
}