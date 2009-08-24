package org.seasar.s2csv.csv.command.base;

import org.seasar.s2csv.csv.exception.runtime.CSVValidationException;


/**
 * @author newta
 */
public abstract class S2CSVRecodeValidationCommand extends S2CSVBaseCommand {

	@Override
	public Object execute(Object o) throws CSVValidationException {
		
		if(o.getClass().isArray() == false){
			throw new ClassCastException();
		}
		
		if(String[].class.isAssignableFrom(o.getClass()) == false){
			Object[] oArray = (Object[])o;
			String[] sArray = new String[oArray.length];
			
			for (int i=0;i<oArray.length;i++) {
				 Object item = oArray[i];
				if(item != null){
					sArray[i] = String.valueOf(item);
				}
			}
			
			o = sArray;
		}
		
		boolean ok = validate((String[]) o);
		
		if(!ok){
			throw new CSVValidationException(
					getErrorMsgKey(),
					getErrorMsgValues());
		}
		
		return o;
	}

	protected abstract boolean validate(String[] o);
}
