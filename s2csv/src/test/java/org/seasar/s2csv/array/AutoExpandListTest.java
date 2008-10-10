package org.seasar.s2csv.array;

import java.util.List;

import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;

public class AutoExpandListTest extends S2TestCase {

	@Test
	public void testSet(){
		
		List<String> l = new AutoExpandList<String>();

		l.add("a");
		l.set(10,"b");
		l.set(0,"c");
		
		assertEquals(11,l.size());
		
		for(int i=0;i<11;i++){
			String s = l.get(i);
			if(i == 0){
				assertEquals("c", s);
			}else if(i == 10){
				assertEquals("b", s);
			}else{
				assertEquals(s, null);
			}
		}
	}

	@Test
	public void testAdd(){

		List<String> l = new AutoExpandList<String>();
		
		l.add("a");
		
		l.add(10,"b");
		
		l.add(0,"c");

		assertEquals(12,l.size());

		for(int i=0;i<12;i++){
			String s = l.get(i);
			if(i == 0){
				assertEquals("c", s);
			}else if(i == 1){
				assertEquals("a", s);
			}else if(i == 11){
				assertEquals("b", s);
			}else{
				assertEquals(s, null);
			}
		}
	}
}
