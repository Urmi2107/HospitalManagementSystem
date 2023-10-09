package com.hospital.management.system;

import java.sql.Date;

public class PatientDetails {
	private int patientId;
	private String patientName;
	private String gender;
	private Date dateOfBirth;
	private int age;
	private String patientContactNo;
	private String patientEmail;
	private String patientAddress;
	private String medicalDept;
	private String doctorName;
	private String description;
	private Date dateOfAdmission;
	private Date dateOfDischarge;
	private String bedNo;
	
	public PatientDetails() {
		super();
	}
	/**
	 * @param patientId
	 * @param patientName
	 * @param gender
	 * @param dateOfBirth
	 * @param age
	 * @param patientContactNo
	 * @param patientEmail
	 * @param patientAddress
	 * @param medicalDept
	 * @param doctorName
	 * @param description
	 * @param dateOfAdmission
	 * @param dateOfDischarge
	 * @param bedNo
	 */
	public PatientDetails(int patientId, String patientName, String gender, Date dateOfBirth, int age,
			String patientContactNo, String patientEmail, String patientAddress, String medicalDept, String doctorName,
			String description, Date dateOfAdmission, Date dateOfDischarge, String bedNo) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.patientContactNo = patientContactNo;
		this.patientEmail = patientEmail;
		this.patientAddress = patientAddress;
		this.medicalDept = medicalDept;
		this.doctorName = doctorName;
		this.description = description;
		this.dateOfAdmission = dateOfAdmission;
		this.dateOfDischarge = dateOfDischarge;
		this.bedNo = bedNo;
	}
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}
	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the patientContactNo
	 */
	public String getPatientContactNo() {
		return patientContactNo;
	}
	/**
	 * @param patientContactNo the patientContactNo to set
	 */
	public void setPatientContactNo(String patientContactNo) {
		this.patientContactNo = patientContactNo;
	}
	/**
	 * @return the patientEmail
	 */
	public String getPatientEmail() {
		return patientEmail;
	}
	/**
	 * @param patientEmail the patientEmail to set
	 */
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	/**
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}
	/**
	 * @param patientAddress the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	/**
	 * @return the medicalDept
	 */
	public String getMedicalDept() {
		return medicalDept;
	}
	/**
	 * @param medicalDept the medicalDept to set
	 */
	public void setMedicalDept(String medicalDept) {
		this.medicalDept = medicalDept;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dateOfAdmission
	 */
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	/**
	 * @param dateOfAdmission the dateOfAdmission to set
	 */
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	/**
	 * @return the dateOfDischarge
	 */
	public Date getDateOfDischarge() {
		return dateOfDischarge;
	}
	/**
	 * @param dateOfDischarge the dateOfDischarge to set
	 */
	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
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
	

}
