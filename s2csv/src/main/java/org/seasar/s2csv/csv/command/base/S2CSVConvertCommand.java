package org.seasar.s2csv.csv.command.base;

import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;


/**
 * @author newta
 */
public abstract class S2CSVConvertCommand extends S2CSVBaseCommand {

	@Override
	public Object execute(Object o) throws CSVValidationException {
		
		o = convert(o);
		
		return o;
	}

	protected abstract Object convert(Object o);
}
