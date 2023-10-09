/**
 * 
 */
package com.hospital.management.system;

/**
 * @author 2064967
 *
 */
public class Department {
	private String deptId;
	private String deptName;
	
	
	public Department() {
		
	}


	/**
	 * @param deptId
	 * @param deptName
	 */
	public Department(String deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	
}
