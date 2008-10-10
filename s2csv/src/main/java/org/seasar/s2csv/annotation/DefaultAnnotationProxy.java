package org.seasar.s2csv.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * アノテーションインスタンス(Proxy)を作成します。
 * ただし設定はすべてデフォルトになります。
 * @author newta
 */
public class DefaultAnnotationProxy implements InvocationHandler{

	private Class<? extends Annotation> ref_clazz;

	/**
	 * アノテーションのインスタンスを作成します。
	 * @param ref_clazz ref_clazz
	 */
	public DefaultAnnotationProxy(Class<? extends Annotation> ref_clazz){
		this.ref_clazz = ref_clazz;
	}

	/**
	 * アノテーションのインスタンスを作成します。
	 * @param clazz clazz
	 * @return annotation
	 */
	public static Annotation getAnnotationInstance(Class<? extends Annotation> clazz){
		
		return (Annotation) Proxy.newProxyInstance(
				clazz.getClassLoader(),
				new Class[] { clazz },
				new DefaultAnnotationProxy(clazz)
				);
	}
	
	/* (非 Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(final Object proxy, final Method method, final Object[] args)
			throws Throwable {
		
		//hashCode, msgKey, toString, args, equals
		if(method.getName().equals("annotationType")){
			return ref_clazz;
		}else if(method.getName().equals("hashCode")){
			return Integer.valueOf(this.hashCode());
		}
		
		return method.getDefaultValue();
	}
	
	public String toString(){
		return DefaultAnnotationProxy.class.getName() + "@" + hashCode();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
