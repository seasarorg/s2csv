package test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Dept�G���?�B�?�B�N���X�ł��E?
 * 
 * @author S2JDBC-Gen
 */
@Entity
public class Dept {

    /** id�v���p�e�B */
    @Id
    @GeneratedValue
    @Column(precision = 19, nullable = false, unique = true)
    public Long id;

    /** deptNo�v���p�e�B */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer deptNo;

    /** deptName�v���p�e�B */
    @Column(length = 20, nullable = true, unique = false)
    public String deptName;

    /** loc�v���p�e�B */
    @Column(length = 20, nullable = true, unique = false)
    public String loc;

    /** versionNo�v���p�e�B */
    @Version
    @Column(precision = 10, nullable = true, unique = false)
    public Integer versionNo;
}