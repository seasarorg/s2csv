package test.service;

import org.seasar.extension.jdbc.SqlSelect;

import test.entity.Apartment;
import test.entity.ApartmentNames;
/** */
public class ApartmentService extends AbstractService<Apartment> implements ApartmentNames {
	/**
	 * @param id 
	 * @return data */
    public Apartment findById(Long id) {
        return select().id(id).getSingleResult();
    }
    /**
     * @param id 
     * @param versionNo 
     * @return dadta */
    public Apartment findByIdVersion(Long id, Integer versionNo) {
        return select().id(id).version(versionNo).getSingleResult();
    }
    /**
     * @param sql 
     * @param o 
     * @return select */
    public SqlSelect<Apartment> selectBySql(String sql,Object... o){
    	
    	return super.jdbcManager.selectBySql(Apartment.class,sql,o);
    }
    
}