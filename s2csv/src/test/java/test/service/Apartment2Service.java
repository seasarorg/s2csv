package test.service;

import org.seasar.extension.jdbc.SqlSelect;

import test.entity.Apartment2;
import test.entity.ApartmentNames;

public class Apartment2Service extends AbstractService<Apartment2> implements ApartmentNames {

    public Apartment2 findById(Long id) {
        return select().id(id).getSingleResult();
    }

    public Apartment2 findByIdVersion(Long id, Integer versionNo) {
        return select().id(id).version(versionNo).getSingleResult();
    }
    
    public SqlSelect<Apartment2> selectBySql(String sql,Object... o){
    	
    	return super.jdbcManager.selectBySql(Apartment2.class,sql,o);
    }
    
}