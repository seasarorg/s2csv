package test.service;

import org.seasar.extension.jdbc.SqlSelect;

import test.entity.Apartment2;
import test.entity.ApartmentNames;

/** */
public class Apartment2Service extends AbstractService<Apartment2> implements ApartmentNames {
	/**
	 * @param id 
	 * @return data */
    public Apartment2 findById(Long id) {
        return select().id(id).getSingleResult();
    }
    /**
     * @param id 
     * @param versionNo 
     * @return data */
    public Apartment2 findByIdVersion(Long id, Integer versionNo) {
        return select().id(id).version(versionNo).getSingleResult();
    }
    /**
     * @param sql 
     * @param o 
     * @return select */
    public SqlSelect<Apartment2> selectBySql(String sql,Object... o){
    	
    	return super.jdbcManager.selectBySql(Apartment2.class,sql,o);
    }
    
}