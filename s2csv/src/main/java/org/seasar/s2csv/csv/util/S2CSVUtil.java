package org.seasar.s2csv.csv.util;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.IterationCallback;
import org.seasar.extension.jdbc.IterationContext;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.Select;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.S2CSVWriteCtrl;
import org.seasar.s2csv.csv.dxo.CSVDxo;
import org.seasar.s2csv.csv.dxo.CSVDxoImpl;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.validator.CSVValidateResult;


/**
 * s2csv全般 ユーティリティ
 * 基本的にユーザに使われるもの
 * @author newta
 */
public class S2CSVUtil {

	private S2CSVUtil(){
		//隠蔽
	}
	
	/**
	 * S2CSVCtrlFactoryを取得する
	 * @return S2CSVCtrlFactory
	 */
	public static S2CSVCtrlFactory getCSVCtrlFactory(){
		return SingletonS2Container.getComponent(S2CSVCtrlFactory.class);
	}

	/**
	 * readerのCSVの内容をテーブルに書き出します。
	 * バリデーションエラーがあった行は書き込まず、
	 * Listでレポートが返されます。
	 * @param csvEntityClass
	 * @param daoEntityClass
	 * @param reader
	 * @return バリデーション結果
	 */
	public static List<CSVValidateResult> csvValidateToS2Jdbc(
			final Class<?> csvEntityClass,
			final Class<?> daoEntityClass,
			final Reader reader
		){
		return csvValidateToS2Jdbc(csvEntityClass, daoEntityClass, null, reader);
	}
	
	/**
	 * readerのCSVの内容をテーブルに書き出します。
	 * バリデーションエラーがあった行は書き込まず、
	 * Listでレポートが返されます。
	 * @param csvEntityClass
	 * @param daoEntityClass
	 * @param dxo
	 * @param reader
	 * @return バリデーション結果
	 */
	public static List<CSVValidateResult> csvValidateToS2Jdbc(
			final Class<?> csvEntityClass,
			final Class<?> daoEntityClass,
			final CSVDxo dxo,
			final Reader reader
	){
		
		S2CSVCtrlFactory factory = getCSVCtrlFactory();

		final S2CSVParseCtrl<?> csvParser 
				= factory.getParseController(
						csvEntityClass,
						reader
						);
		
		JdbcManager jdbcManager =
			SingletonS2Container.getComponent(JdbcManager.class);
		
		CSVDxo local_dxo = dxo;
		if(local_dxo == null){
			local_dxo = new CSVDxoImpl();
		}
		
		//バリデーションメソッドは自分で呼び出す。
		csvParser.setValidateFlag(false);
		
		List<CSVValidateResult> result = new ArrayList<CSVValidateResult>();
		while(csvParser.readNext()){
			
			CSVValidateResult v = csvParser.validate();

			if(v != null){
				result.add(v);
				continue;
			}
			
			
			Object csvObj = csvParser.parse();
			
			Object daoObj = local_dxo.dxo(daoEntityClass, csvObj);
			
			jdbcManager.insert(daoObj).execute();
		}
		
		return result;
	}
	
	/**
	 * readerのCSVの内容をテーブルに書き出します。
	 * バリデーションはしません。
	 * 変換エラーがあった場合、CSVChangeExceptionが投げられます。
	 * @param csvEntityClass
	 * @param daoEntityClass
	 * @param reader
	 */
	public static void csvToS2Jdbc(
			final Class<?> csvEntityClass,
			final Class<?> daoEntityClass,
			final Reader reader
		){
		csvToS2Jdbc(csvEntityClass, daoEntityClass, null, reader);
	}
	
	/**
	 * readerのCSVの内容をテーブルに書き出します。
	 * バリデーションはしません。
	 * 変換エラーがあった場合、CSVChangeExceptionが投げられます。
	 * @param csvEntityClass
	 * @param daoEntityClass
	 * @param dxo
	 * @param reader
	 */
	public static void csvToS2Jdbc(
			final Class<?> csvEntityClass,
			final Class<?> daoEntityClass,
			final CSVDxo dxo,
			final Reader reader
	){
		
		S2CSVCtrlFactory factory = getCSVCtrlFactory();

		final S2CSVParseCtrl<?> csvParser 
				= factory.getParseController(
						csvEntityClass,
						reader
						);
		
		JdbcManager jdbcManager =
			SingletonS2Container.getComponent(JdbcManager.class);
		
		CSVDxo local_dxo = dxo;
		if(local_dxo == null){
			local_dxo = new CSVDxoImpl();
		}
		
		//バリデーションはしない。
		csvParser.setValidateFlag(false);
		
		while(csvParser.readNext()){
				
			Object csvObj = csvParser.parse();
			
			Object daoObj = local_dxo.dxo(daoEntityClass, csvObj);
			
			jdbcManager.insert(daoObj).execute();
		}
	}


	/**
	 * S2JDBCのSelectからwriterにCSV形式で書き出します。
	 * @param entityClass
	 * @param select
	 * @param writer
	 */
	@SuppressWarnings("unchecked")
	public static void s2jdbcToCsv(
			final Class<?> entityClass,
			final Select<?,?> select,
			final Writer writer ) {
		s2jdbcToCsv(entityClass,select, null, writer);
	}
	
	/**
	 * S2JDBCのSelectからwriterにCSV形式で書き出します。
	 * @param entityClass
	 * @param select
	 * @param dxo
	 * @param writer
	 */
	@SuppressWarnings("unchecked")
	public static void s2jdbcToCsv(
			final Class<?> entityClass,
			final Select<?,?> select,
			final CSVDxo dxo,
			final Writer writer ) {
		
		S2CSVCtrlFactory factory = getCSVCtrlFactory();
		
		final S2CSVWriteCtrl csvWriter 
				= factory.getWriteController(
						entityClass,
						writer
						);
		
		select.iterate(new IterationCallback(){
			
			private boolean init;
			private CSVDxo local_dxo;
			
			public Object iterate(Object obj, IterationContext context) {
				
				if(!init){
					if(dxo == null){
						local_dxo = new CSVDxoImpl();
					}else{
						local_dxo = dxo;
					}
					init = true;
				}
				
				Object csvObj = local_dxo.dxo(entityClass ,obj);
				
				csvWriter.write(csvObj);
				
				return null;
			}
		});
		
		csvWriter.close();
	}
}
