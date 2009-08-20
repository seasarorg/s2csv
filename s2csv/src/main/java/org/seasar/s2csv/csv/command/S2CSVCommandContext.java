package org.seasar.s2csv.csv.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author newta
 */
public class S2CSVCommandContext {

	private String ENTITY_KEY = S2CSVCommandContext.class.getName() + "_ENTITY_KEY";
	
	private Map<Object,Object> map = new HashMap<Object, Object>();
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Object key,Object value){
		map.put(key, value);
	}
	/**
	 * 
	 * @param <T>
	 * @param key
	 * @return value
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Object key){
		return (T) map.get(key);
	}
	/**
	 * 
	 * @param <T>
	 * @return entity
	 */
	public <T> T getEntity(){
		return get(ENTITY_KEY);
	}

	/**
	 * 
	 * @param entity
	 */
	public void setEntity(Object entity){
		put(ENTITY_KEY, entity);
	}
}
