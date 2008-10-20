package org.seasar.s2csv.csv.desc;

import java.lang.reflect.Method;
import java.util.Map;

import org.seasar.framework.beans.PropertyDesc;

/**
 * コンバータ設定
 * @author newta
 */
public class CSVConvertDesc {
	
	/** コンバータクラス nullのときはエンティティのコンバータメソッドを呼ぶ */
	private Class<?> convClass;

	/** CSVからObjectに変換するときのメソッド */
	private Method convToObjMethod;
	/** ObjectからCSVに変換するときのメソッド */
	private Method convToCSVMethod;
	
	/** コンバータクラスをインスタンス化するときに設定するプロパティ */
	private Map<PropertyDesc,String> convClassProps;
	
	/**
	 * @return convClass
	 */
	public Class<?> getConvClass() {
		return convClass;
	}
	/**
	 * @param convClass
	 */
	public void setConvClass(Class<?> convClass) {
		this.convClass = convClass;
	}
	/**
	 * @return convToObjMethod
	 */
	public Method getConvToObjMethod() {
		return convToObjMethod;
	}
	/**
	 * @param convToObjMethod
	 */
	public void setConvToObjMethod(Method convToObjMethod) {
		this.convToObjMethod = convToObjMethod;
	}
	/**
	 * @return convToCSVMethod
	 */
	public Method getConvToCSVMethod() {
		return convToCSVMethod;
	}
	/**
	 * @param convToCSVMethod
	 */
	public void setConvToCSVMethod(Method convToCSVMethod) {
		this.convToCSVMethod = convToCSVMethod;
	}
	/**
	 * @return convClassProps
	 */
	public Map<PropertyDesc, String> getConvClassProps() {
		return convClassProps;
	}
	/**
	 * @param convClassProps 
	 */
	public void setConvClassProps(Map<PropertyDesc, String> convClassProps) {
		this.convClassProps = convClassProps;
	}
	
	
}
