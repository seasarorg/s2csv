package org.seasar.s2csv.csv.convertor;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.ClassUtil;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.desc.CSVColumnDesc;
import org.seasar.s2csv.csv.desc.CSVConvertDesc;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVChangeException;
import org.seasar.s2csv.csv.util.S2CSVDescUtil;


/**
 * CSVをオブジェクトに変換したり、オブジェクトからCSVデータに変換したりする
 * @author newta
 */
public class CSVConvertCtrl {
	
	/** CSVエンティティの設定 */
	private CSVEntityDesc entityDesc;
	/** コンバータのインスタンスをキャッシュする */
	private Map<CSVColumn,CSVColumnConvertor> cache;
	
	/**
	 * @param entityDesc
	 */
	public CSVConvertCtrl(CSVEntityDesc entityDesc){
		this.entityDesc = entityDesc;
		this.cache = new HashMap<CSVColumn,CSVColumnConvertor>();
	}

	/**
	 * @param csvEntityClass
	 */
	public CSVConvertCtrl(Class<?> csvEntityClass){
		this.entityDesc =  S2CSVDescUtil.getCSVEntityDesc(csvEntityClass);
		this.cache = new HashMap<CSVColumn,CSVColumnConvertor>();
	}
	
	

	/**
	 * CSV１行データをオブジェクトに変換します
	 * @param <T> 
	 * @param entity 変換先オブジェクトクラス
	 * @param csvLine CSV行イメージ
	 * @return 変換したオブジェクト
	 */
	public <T> T covToObj(T entity,String[] csvLine){
		
		try{
			for(CSVColumnDesc colDesc : entityDesc.getColumnConfigs()){
				
				String columValue = csvLine[colDesc.getColumnIndex()];
				
				CSVConvertDesc convDesc = colDesc.getConvert();
				
				if(convDesc == null || convDesc.getConvToObjMethod() == null){
					//変換なしで設定
					colDesc.getColumnDesc().setValue(entity ,columValue);
					continue;
				}
				
				Object convedObj;
				if(convDesc.getConvClass() == null){
					//エンティティクラスのコンバートメソッド呼び出し
					convedObj = convDesc.getConvToObjMethod().invoke(entity, columValue);
				}else{
					//コンバータクラス実行
					CSVColumnConvertor convertor = this.cache.get(colDesc.getCsvColumn());
					
					if(convertor == null){
						convertor = (CSVColumnConvertor) ClassUtil.newInstance(convDesc.getConvClass());
						
						//コンバータのプロパティを設定する
						for(PropertyDesc convPropDesc : convDesc.getConvClassProps().keySet()){
							
							convPropDesc.setValue(convertor, convDesc.getConvClassProps().get(convPropDesc));
						}
						
						//コンバータのインスタンスをキャッシュする
						cache.put(colDesc.getCsvColumn(), convertor);
					}
					
					//変換
					convedObj = convDesc.getConvToObjMethod().invoke(convertor, columValue);
				}
				
				//変換後のオブジェクトをエンティティに設定
				colDesc.getColumnDesc().setValue(entity, convedObj);
			}
			
			return entity;
		}catch(Exception e){
			throw new CSVChangeException(e);
		}
	}
	
	/**
	 * オブジェクトを１行分のデータに変換します。
	 * 変換するにはアノテーションCSVColumnがフィールドに設定されている必要があります。
	 * @param entity 変換オブジェクト
	 * @return CSV行イメージ
	 */
	public String[] covToCSVLine(Object entity){

		String[] csvColumns = new String[entityDesc.getColmunSize()];
		
		try{
			for(CSVColumnDesc colDesc : entityDesc.getColumnConfigs()){
				Object columnObj = colDesc.getColumnDesc().getValue(entity);

				CSVConvertDesc convDesc = colDesc.getConvert();
				
				if(convDesc == null || convDesc.getConvToObjMethod() == null){
					//変換なしで設定
					if(columnObj != null){
						csvColumns[colDesc.getColumnIndex()]
						           = String.valueOf(columnObj);
					}
					continue;
				}

				Object convedObj;
				if(convDesc.getConvClass() == null){
					//エンティティクラスのコンバートメソッド呼び出し
					convedObj = convDesc.getConvToCSVMethod().invoke(entity, columnObj);
				}else{
					//コンバータクラス実行
					CSVColumnConvertor convertor = this.cache.get(colDesc.getCsvColumn());
					
					if(convertor == null){
						convertor = (CSVColumnConvertor) ClassUtil.newInstance(convDesc.getConvClass());
						
						//コンバータのプロパティを設定する
						for(PropertyDesc convPropDesc : convDesc.getConvClassProps().keySet()){
							
							convPropDesc.setValue(convertor, convDesc.getConvClassProps().get(convPropDesc));
						}
						
						//コンバータのインスタンスをキャッシュする
						cache.put(colDesc.getCsvColumn(), convertor);
					}
					
					//変換
					convedObj = convDesc.getConvToCSVMethod().invoke(convertor, columnObj);
				}
				
				//変換後のデータを設定
				if(columnObj != null){
					csvColumns[colDesc.getColumnIndex()]
					           = String.valueOf(convedObj);
				}
			}
			
			return csvColumns;
			
		}catch(Exception e){
			throw new CSVChangeException(e);
		}
	}
}
