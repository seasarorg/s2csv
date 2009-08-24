package org.seasar.s2csv.csv.command.base;

import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;


/**
 * @author newta
 */
public abstract class S2CSVColmunValidationCommand extends S2CSVBaseCommand {

	@Override
	public Object execute(Object o) throws CSVValidationException {
		
		boolean ok = validate((String) o);
		
		if(!ok){
			throw new CSVValidationException(
					getErrorMsgKey(),
					getErrorMsgValues());
		}
		
		return o;
	}

	protected abstract boolean validate(String o);
}
