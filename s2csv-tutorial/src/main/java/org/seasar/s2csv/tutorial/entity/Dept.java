package org.seasar.s2csv.tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;



@Entity
public class Dept {

	@Id
	@GeneratedValue
	public Long id;

	@Column(name="DEPT_NO")
	public Integer deptNo;

	@Column(name="DEPT_NAME")
	public String deptName;

	public String loc;

	@Version
	@Column(name="VERSION_NO")
	public Integer versionNo;

}