package com.hospital.management.system;

public class Doctor {
	private String docId;
	private String docName;
	
	public Doctor() {
		
	}
	/**
	 * @param docId
	 * @param docName
	 */
	public Doctor(String docId, String docName) {
		super();
		this.docId = docId;
		this.docName = docName;
	}
	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	

}
