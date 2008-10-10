package org.seasar.s2csv.annotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.MethodUtil;

/**
 * アノテーション関連のユーティリティ
 * @author newta
 */
public class CSVAnnotationUtil {

	private CSVAnnotationUtil() {
	}

	/**
	 * アノテーションに定義されているメソッドの値を取得しMapに変換します
	 * @param annotation アノテーション
	 * @return map
	 */
	public static Map<String,Object> getProps(Annotation annotation) {

		Map<String,Object> map = new HashMap<String,Object>();
		
		if(annotation == null){
			return map;
		}
		
		BeanDesc beanDesc = BeanDescFactory.getBeanDesc(annotation
				.annotationType());

		String names[] = beanDesc.getMethodNames();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			Object v = getProp(beanDesc, annotation, name);
			if (v != null)
				map.put(name, v);
		}

		return map;
	}

	/**
	 * アノテーションから値を取得します
	 * @param beanDesc beanDesc
	 * @param annotation annotation
	 * @param name methodName
	 * @return value
	 */
	public static Object getProp(BeanDesc beanDesc, Annotation annotation,
			String name) {

		java.lang.reflect.Method m = beanDesc.getMethodNoException(name);
		if (m == null)
			return null;

		Object v = MethodUtil.invoke(m, annotation, null);
		if (v != null && !"".equals(v))
			return v;

		return null;
	}
}
