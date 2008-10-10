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
	
	public Class<?> getConvClass() {
		return convClass;
	}
	public void setConvClass(Class<?> convClass) {
		this.convClass = convClass;
	}
	public Method getConvToObjMethod() {
		return convToObjMethod;
	}
	public void setConvToObjMethod(Method convToObjMethod) {
		this.convToObjMethod = convToObjMethod;
	}
	public Method getConvToCSVMethod() {
		return convToCSVMethod;
	}
	public void setConvToCSVMethod(Method convToCSVMethod) {
		this.convToCSVMethod = convToCSVMethod;
	}
	public Map<PropertyDesc, String> getConvClassProps() {
		return convClassProps;
	}
	public void setConvClassProps(Map<PropertyDesc, String> convClassProps) {
		this.convClassProps = convClassProps;
	}
	
	
}
