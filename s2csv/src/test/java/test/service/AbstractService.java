package test.service;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.service.S2AbstractService;

/**
 * �T�[�r�X�̒��ۃN���X�ł�
 * 
 * @author S2JDBC-Gen
 * @param <ENTITY>
 */
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {
	
	public AutoSelect<ENTITY> select(){
		return super.select();
	}
	
}