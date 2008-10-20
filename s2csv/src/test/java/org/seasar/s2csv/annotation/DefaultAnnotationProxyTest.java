package org.seasar.s2csv.annotation;

import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;
/** */
public class DefaultAnnotationProxyTest extends S2TestCase {
	/** */
	@Test
	public void testDefaultAnnotationProxy(){
		
		AAAAno a = (AAAAno) DefaultAnnotationProxy.getAnnotationInstance(AAAAno.class);
		
		assertEquals("aaa", a.a());
		assertEquals("bb1", a.b()[0]);
		assertEquals("bb2", a.b()[1]);

		a.toString();
		a.hashCode();
		
		assertEquals(AAAAno.class, a.annotationType());
	}
}
