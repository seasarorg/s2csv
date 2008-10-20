package test.service;

import test.entity.Dept;
import test.entity.DeptNames;
/** */
public class DeptService extends AbstractService<Dept> implements DeptNames {
	/**
	 * @param id 
	 * @return data */
    public Dept findById(Long id) {
        return select().id(id).getSingleResult();
    }
    /**
     * @param id 
     * @param versionNo 
     * @return data */
    public Dept findByIdVersion(Long id, Integer versionNo) {
        return select().id(id).version(versionNo).getSingleResult();
    }
}