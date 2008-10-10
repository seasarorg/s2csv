package org.seasar.s2csv.csv.dxo;

import org.seasar.framework.beans.util.Beans;

/**
 * 変換
 * @author newta
 */
public class CSVDxoImpl implements CSVDxo {

	public <T> T dxo(Class<T> c,Object o) {
		
		return Beans.createAndCopy(c, o).execute();
	}
}
