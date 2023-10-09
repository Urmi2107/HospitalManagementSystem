package com.hospital.management.system;

public class BedNo {
	private int bedId;
	private String bedNo;
	private String isVacant;
	
	public BedNo() {
		super();
	}

	/**
	 * @param bedId
	 * @param bedNo
	 * @param isVacant
	 */
	public BedNo(int bedId, String bedNo, String isVacant) {
		super();
		this.bedId = bedId;
		this.bedNo = bedNo;
		this.isVacant = isVacant;
	}

	/**
	 * @return the bedId
	 */
	public int getBedId() {
		return bedId;
	}

	/**
	 * @param bedId the bedId to set
	 */
	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	/**
	 * @return the bedNo
	 */
	public String getBedNo() {
		return bedNo;
	}

	/**
	 * @param bedNo the bedNo to set
	 */
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	/**
	 * @return the isVacant
	 */
	public String getIsVacant() {
		return isVacant;
	}

	/**
	 * @param isVacant the isVacant to set
	 */
	public void setIsVacant(String isVacant) {
		this.isVacant = isVacant;
	}
	
	

}
