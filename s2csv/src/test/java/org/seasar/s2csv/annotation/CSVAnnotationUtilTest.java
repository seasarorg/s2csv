package org.seasar.s2csv.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;

public class CSVAnnotationUtilTest extends S2TestCase {
	
	@Test
	public void testGetProps(){
		
		Annotation a = AAA.class.getAnnotation(AAAAno.class);
		
		Map<String,Object> m = CSVAnnotationUtil.getProps(a);
		
		assertEquals("aaa", m.get("a"));
		
		String[] b = (String[]) m.get("b");
		
		assertEquals("bb1",b[0]);
		assertEquals("bb2",b[1]);
	}
}
