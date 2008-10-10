package test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Deptエン繝?ィ繝?ィクラスです・?
 * 
 * @author S2JDBC-Gen
 */
@Entity
public class Dept {

    /** idプロパティ */
    @Id
    @GeneratedValue
    @Column(precision = 19, nullable = false, unique = true)
    public Long id;

    /** deptNoプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer deptNo;

    /** deptNameプロパティ */
    @Column(length = 20, nullable = true, unique = false)
    public String deptName;

    /** locプロパティ */
    @Column(length = 20, nullable = true, unique = false)
    public String loc;

    /** versionNoプロパティ */
    @Version
    @Column(precision = 10, nullable = true, unique = false)
    public Integer versionNo;
}