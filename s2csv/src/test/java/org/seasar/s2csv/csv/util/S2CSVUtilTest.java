package org.seasar.s2csv.csv.util;

import org.junit.Test;
import org.seasar.s2csv.csv.desc.CSVEntityDesc;

import test.S2CSVTestBase;
import test.csv.TestCsv;
/** */
public class S2CSVUtilTest extends S2CSVTestBase {
	/** */
	@Test
	public void testCreateCSVEntityDesc(){
		
		CSVEntityDesc desc = S2CSVDescUtil.getCSVEntityDesc(TestCsv.class);
		
		assertEquals(desc.getEntityClass(),TestCsv.class);
		assertEquals(desc.isHeader(), true);
		assertEquals(desc.getColmunSize(), 7);
		
		String[] headers = {"hoge", "hoge2", "ほげINT", "ほげ日付", "custamHoge", "あいうえお", "hogeDate2"};
		
		for(int i = 0;i<headers.length;i++){
			String c1 = desc.getHeaderNames()[i];
			String c2 = headers[i];
			assertEquals(c1,c2);
		} 
	}
}
