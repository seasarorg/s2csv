package org.seasar.s2csv.csv.desc.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.s2csv.annotation.CSVAnnotationUtil;
import org.seasar.s2csv.annotation.DefaultAnnotationProxy;
import org.seasar.s2csv.array.AutoExpandList;
import org.seasar.s2csv.csv.annotation.CSVValidator;
import org.seasar.s2csv.csv.annotation.ValidateTargetEntity;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVConvertor;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.s2csv.csv.annotation.entity.CSVRecordValidator;
import org.seasar.s2csv.csv.convertor.CSVColumnConvertor;
import org.seasar.s2csv.csv.desc.CSVColumnDesc;
import org.seasar.s2csv.csv.desc.CSVConvertDesc;
import org.seasar.s2csv.csv.desc.CSVDescFactory;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;
import org.seasar.s2csv.csv.desc.CSVValidateDesc;
import org.seasar.s2csv.csv.exception.runtime.CSVColumnConfigException;
import org.seasar.s2csv.csv.exception.runtime.CSVConfigException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationMethodReturnTypeException;
import org.seasar.s2csv.csv.util.S2CSVSystemUtil;

/**
 * CSVEntityクラス情報からCSV設定情報を作成するクラスです。
 * @author newta
 */
public class CSVDescFactoryImpl implements CSVDescFactory {

	/**
	 * CSVエンティティクラスから
	 * CSV設定情報を展開します。
	 * @param csvClass
	 * @return CSV設定情報
	 */
	public CSVEntityDesc createCSVEntityDesc(Class<?> csvClass){
		
		CSVEntity entityConf = csvClass.getAnnotation(CSVEntity.class);
		
		if(entityConf == null){
			throw new CSVConfigException(csvClass);
		}
		
		CSVEntityDesc conf =  new CSVEntityDesc();
		
		conf.setEntityClass(csvClass);
		

		List<CSVValidateDesc> recValList = createRecordValidate(csvClass);
		
		if(recValList != null){
			//レコードバリデータの追加
			conf.setRecordValidate(recValList);
		}
		
		
		List<String> header = new AutoExpandList<String>();
		List<String> fieldNames = new AutoExpandList<String>();
		List<CSVColumnDesc> columnsDesc = new AutoExpandList<CSVColumnDesc>();

		BeanDesc beanDesc = BeanDescFactory.getBeanDesc(csvClass);
		
		
		for(int i=0;i < beanDesc.getPropertyDescSize() ; i++){
			PropertyDesc desc = beanDesc.getPropertyDesc(i);
			Field f = desc.getField();
			
			if(f == null){
				continue;
			}
			
			CSVColumn c = f.getAnnotation(CSVColumn.class);
			
			if(c == null){
				continue;
			}
			
			String columnName = S2CSVSystemUtil.getColumnName(f);
			
			//カラム設定の作成
			CSVColumnDesc colDesc = createCSVColumnDesc(csvClass, desc, f);
			
			header.set(c.columnIndex(), columnName);
			fieldNames.set(c.columnIndex(), f.getName());
			columnsDesc.set(c.columnIndex(), colDesc);
		}
		
		if(header.size() == 0){
			throw new CSVColumnConfigException(csvClass);
		}

		conf.setHeader(entityConf.header());
		if(entityConf.header() && entityConf.headerCheck()){
			conf.setCheckHeader(true);
		}else{
			conf.setCheckHeader(false);
		}
		conf.setHeaderNames(header.toArray(new String[0]));
		conf.setFieldNames(fieldNames.toArray(new String[0]));
		conf.setColmunSize(conf.getHeaderNames().length);
		conf.setColumnConfigs(columnsDesc);
		conf.setCheckColumnSize(entityConf.columnCountCheck());
		conf.setDemiliter(entityConf.demiliter());
		
		return conf;
	}

	protected List<CSVValidateDesc> createRecordValidate(Class<?> csvClass) {

		List<CSVValidateDesc> validateList = new ArrayList<CSVValidateDesc>();
		
        for (Annotation anno : csvClass.getDeclaredAnnotations()) {

            Annotation wrappingAnno = null;
            CSVRecordValidator validateAnno = null;
    		if (anno instanceof CSVRecordValidator) {
    			validateAnno = (CSVRecordValidator) anno;
    		}else{
    			Class<?> annotationType = anno.annotationType();
    			validateAnno = (CSVRecordValidator) annotationType
            									.getAnnotation(CSVRecordValidator.class);
    			wrappingAnno = anno;
    		}
    		
            if (validateAnno == null) {
                continue;
            }
            
            
            Map<String, Object> wrappingAnnoProps = CSVAnnotationUtil.getProps(wrappingAnno);

        	//バリデーション設定 作成する
    		CSVValidateDesc desc = new CSVValidateDesc();
    		Class<?> validateClass = validateAnno.methodClass();
          
    		
    		////バリデーションメソッドの設定色々
            if (validateClass.equals(ValidateTargetEntity.class)) {
            	//エンティティのバリデーションメソッドを使う
            	desc.setEntityVadlidateMethod(true);
            	validateClass = csvClass;
            }else{
            	desc.setEntityVadlidateMethod(false);
            }
    		
    		String[] methodArgsNames = validateAnno.methodArgsNames();
    		Class<?>[] methodArgsTypePlus = new Class<?>[methodArgsNames.length + 1];
            Object[] methodArgs = new Object[methodArgsNames.length + 1];
            
    		//先頭はCSVのカラム文字列固定
            methodArgsTypePlus[0] = String[].class;
            //メソッド引数の作成
            BeanDesc beanDesc = null;
            if(wrappingAnno != null){
            	beanDesc = BeanDescFactory.getBeanDesc(wrappingAnno.annotationType());
    	        int i = 1;
    	        for(String name : methodArgsNames){
    	        	methodArgs[i] = wrappingAnnoProps.get(name);
    	        	methodArgsTypePlus[i] = beanDesc.getMethod(name).getReturnType();
    	        	i++;            	           
    	        }
            }
            //実行メソッドを取り出す。
            Method validationMethod = ClassUtil.getMethod(validateClass,validateAnno.method(),methodArgsTypePlus);

            Class<?> returnType = validationMethod.getReturnType();
            
            if(!returnType.equals(boolean.class)){
            	throw new CSVValidationMethodReturnTypeException(validationMethod);
            }
            
            desc.setValidateMethod(validationMethod);
            desc.setValidateMethodArgs(methodArgs);
            
            
            ////バリデーション メッセージの設定色々
        	String msgKey = (String) wrappingAnnoProps.remove("msgKey");
        	Object[] msgArgs = (Object[]) wrappingAnnoProps.remove("args");
        	
        	if(StringUtil.isBlank(msgKey)){
        		msgKey = validateAnno.msgKey();
        		msgArgs = validateAnno.args();
        	}
        	
        	//メッセージ変数の置き換え
        	//wrappingAnnoの変数値を代入したりする
        	msgArgs = S2CSVSystemUtil.preReplaceMsgArgs(msgArgs, null, wrappingAnnoProps);

        	desc.setMsgKey(msgKey);
        	desc.setMsgArgs(msgArgs);
            if(desc != null){
                validateList.add(desc);            	
            }
        }
		
		return validateList;
	}

	protected CSVColumnDesc createCSVColumnDesc(Class<?> csvClass, PropertyDesc columnDesc, Field f) {

		CSVColumn c = f.getAnnotation(CSVColumn.class);
		CSVColumnDesc desc = new CSVColumnDesc();

		desc.setColumnDesc(columnDesc);
		desc.setCsvColumn(c);

		CSVConvertDesc convDesc = createCSVConvertDesc(csvClass, f);
		desc.setConvert(convDesc);

		//バリデーション設定の作成
		List<CSVValidateDesc> validateList = 
			createCSVValidateDescList(csvClass, columnDesc.getField(), c, (convDesc == null));
		desc.setValidateList(validateList);
		
		
		return desc;
	}
	
	protected CSVConvertDesc createCSVConvertDesc(Class<?> csvClass,Field f){

		CSVConvertor conv = f.getAnnotation(CSVConvertor.class);
		
		if(conv == null){
			return null;
		}

		if(conv.convertor().equals(CSVColumnConvertor.class)){
			//コンバータクラスの定義は無いので、メソッド定義があるか調べる
			
			Method convToCSVMethod = null;
			if(StringUtil.isNotBlank(conv.convToCSVMethod())){
				convToCSVMethod = ClassUtil.getMethod(csvClass,conv.convToCSVMethod(),new Class<?>[]{f.getType()});				
			}

			Method convToObjMethod = null;
			if(StringUtil.isNotBlank(conv.convToObjMethod())){
				convToObjMethod = ClassUtil.getMethod(csvClass,conv.convToObjMethod(),new Class<?>[]{String.class});
			}
			
			if(convToCSVMethod == null && convToObjMethod == null){
				return null;
			}

			//コンバータクラスの定義の作成
			CSVConvertDesc convDesc = new CSVConvertDesc();
			convDesc.setConvToCSVMethod(convToCSVMethod);
			convDesc.setConvToObjMethod(convToObjMethod);
			
			return convDesc;
		}
		
		//コンバータクラスの定義の作成
		CSVConvertDesc convDesc = new CSVConvertDesc();

		Class<? extends CSVColumnConvertor> convClass = conv.convertor();
		String[] props = conv.convertSetProp();
		
		//コンバータクラスに渡す設定
		Map<PropertyDesc,String> convClassProps = null;
		
		if(props != null && props.length > 0){
			
			convClassProps = new HashMap<PropertyDesc,String>();
			BeanDesc destBeanDesc = BeanDescFactory.getBeanDesc(convClass);
			
			for(String p : props){
				
				if(p == null || p.equals("") || p.indexOf('=') < 1){
					continue;
				}
				
				String propName = p.substring(0,p.indexOf('=')).trim();
				String propValue = p.substring(p.indexOf('=') + 1).trim();
				
				PropertyDesc desc = destBeanDesc.getPropertyDesc(propName);
				
				convClassProps.put(desc,propValue);
			}
		}

		//設定
		convDesc.setConvClass(convClass);
		convDesc.setConvToCSVMethod(ClassUtil.getMethod(convClass,"getAsString",new Class<?>[]{Object.class}));
		convDesc.setConvToObjMethod(ClassUtil.getMethod(convClass,"getAsObject",new Class<?>[]{String.class}));
		convDesc.setConvClassProps(convClassProps);
		
		return convDesc;
	}

	protected List<CSVValidateDesc> createCSVValidateDescList(Class<?> csvClass, Field f,
			CSVColumn c, boolean defaultTypeValidateFlag) {
		List<CSVValidateDesc> validateList = new ArrayList<CSVValidateDesc>();
		
		//フィールドの型からデフォルトのチェックアノテーションを追加チェックするための設定
		Class<? extends Annotation> typeAnnotationClazz = null;
		
		if(defaultTypeValidateFlag){
			//コンバータを持っていないときのみ設定
			typeAnnotationClazz = S2CSVSystemUtil.getTypeValidateAnnotation(f);
		}
		
        for (Annotation anno : f.getDeclaredAnnotations()) {

            if(typeAnnotationClazz != null &&
            		typeAnnotationClazz.isAssignableFrom(anno.getClass())){
            	//すでにユーザに設定済みならば、無効にする
            	typeAnnotationClazz = null;
            }
            
            CSVValidateDesc desc = createCSVValidateDesc(csvClass,f,anno);
            if(desc != null){
                validateList.add(desc);            	
            }
        }
        
        if(typeAnnotationClazz != null){
        	Annotation anno = DefaultAnnotationProxy.getAnnotationInstance(typeAnnotationClazz);
            CSVValidateDesc desc = createCSVValidateDesc(csvClass,f,anno);

            if(desc != null){
                validateList.add(desc);            	
            }
        }
		
		
		return validateList;
	}
	
	
	protected CSVValidateDesc createCSVValidateDesc(Class<?> csvClass,Field columnField, Annotation anno){

        Annotation wrappingAnno = null;
        CSVValidator validateAnno = null;
		if (anno instanceof CSVValidator) {
			validateAnno = (CSVValidator) anno;
		}else{
			Class<?> annotationType = anno.annotationType();
			validateAnno = (CSVValidator) annotationType
        									.getAnnotation(CSVValidator.class);
			wrappingAnno = anno;
		}
		
        if (validateAnno == null) {
            return null;
        }
        
        
        Map<String, Object> wrappingAnnoProps = CSVAnnotationUtil.getProps(wrappingAnno);

		
    	//バリデーション設定 作成する
		CSVValidateDesc desc = new CSVValidateDesc();
		
		Class<?> validateClass = validateAnno.methodClass();
      
		
		////バリデーションメソッドの設定色々
		
        if (validateClass.equals(ValidateTargetEntity.class)) {
        	//エンティティのバリデーションメソッドを使う
        	desc.setEntityVadlidateMethod(true);
        	validateClass = csvClass;
        }else{
        	desc.setEntityVadlidateMethod(false);
        }

		
		String[] methodArgsNames = validateAnno.methodArgsNames();
		Class<?>[] methodArgsTypePlus = new Class<?>[methodArgsNames.length + 1];
        Object[] methodArgs = new Object[methodArgsNames.length + 1];
        
		//先頭はCSVのカラム文字列固定
        methodArgsTypePlus[0] = String.class;
        //メソッド引数の作成
        BeanDesc beanDesc = null;
        if(wrappingAnno != null){
        	beanDesc = BeanDescFactory.getBeanDesc(wrappingAnno.annotationType());
	        int i = 1;
	        for(String name : methodArgsNames){
	        	methodArgs[i] = wrappingAnnoProps.get(name);
	        	methodArgsTypePlus[i] = beanDesc.getMethod(name).getReturnType();
	        	i++;            	           
	        }
        }
        //実行メソッドを取り出す。
        Method validationMethod = ClassUtil.getMethod(validateClass,validateAnno.method(),methodArgsTypePlus);

        Class<?> returnType = validationMethod.getReturnType();
        
        if(!returnType.equals(boolean.class)){
        	throw new CSVValidationMethodReturnTypeException(validationMethod);
        }
        
        desc.setValidateMethod(validationMethod);
        desc.setValidateMethodArgs(methodArgs);
        
        
        ////バリデーション メッセージの設定色々
    	String msgKey = (String) wrappingAnnoProps.remove("msgKey");
    	Object[] msgArgs = (Object[]) wrappingAnnoProps.remove("args");
    	
    	if(StringUtil.isBlank(msgKey)){
    		msgKey = validateAnno.msgKey();
    		msgArgs = validateAnno.args();
    	}
    	
    	//メッセージ変数の置き換え
    	//wrappingAnnoの変数値を代入したりする
    	msgArgs = S2CSVSystemUtil.preReplaceMsgArgs(msgArgs,columnField, wrappingAnnoProps);

    	desc.setMsgKey(msgKey);
    	desc.setMsgArgs(msgArgs);
		
		return desc;
	}
}
