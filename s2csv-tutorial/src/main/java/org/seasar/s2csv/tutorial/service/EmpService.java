package org.seasar.s2csv.tutorial.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.util.S2CSVUtil;
import org.seasar.s2csv.csv.validator.CSVValidateResult;
import org.seasar.s2csv.tutorial.csv.EmpCsv;
import org.seasar.s2csv.tutorial.entity.Emp;
import org.seasar.s2csv.tutorial.system.CSVExampleUtil;

public class EmpService extends AbstractService<Emp> {

	/**
	 * 自動DIされます。
	 * CSVコントローラを作るファクトリです
	 */
	public S2CSVCtrlFactory csvCtrlFactory;

	/**
	 * EmpテーブルのデータをCSV形式で出力する
	 * @param fileName
	 * @throws IOException 
	 */
	public void outCsv(String fileName) throws IOException {
		Writer writer = new FileWriter(fileName);
		S2CSVUtil.s2jdbcToCsv(EmpCsv.class, select(), writer);
	}

	/**
	 * Empテーブルのデータを削除し、
	 * CSVファイルデータをDeptテーブルに登録する。
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void inCsv(String fileName) throws FileNotFoundException {

		Reader reader = new FileReader(fileName);
		//jdbcManager.callBySql("delete from emp").execute();

		S2CSVParseCtrl<EmpCsv> csv_parser = csvCtrlFactory.getParseController(
				EmpCsv.class, reader);

		List<CSVValidateResult> list = new ArrayList<CSVValidateResult>();
		
		while (csv_parser.readNext()) {
			CSVValidateResult validateResult = csv_parser.validate();

			if (validateResult != null) {
				list.add(validateResult);
				continue;
			}
		}
		
		CSVExampleUtil.showErrMessage(list);
	}
}