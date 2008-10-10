package test.service;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.service.S2AbstractService;

/**
 * サービスの抽象クラスです
 * 
 * @author S2JDBC-Gen
 * @param <ENTITY>
 */
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {
	
	public AutoSelect<ENTITY> select(){
		return super.select();
	}
	
}