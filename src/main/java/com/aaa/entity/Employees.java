package com.aaa.entity;

   /**
    * @文件名称：employees.java
    * @创建时间：2018-08-28 12:27:06
    * @创  建  人：sn 
    * @文件描述：employees 实体类
    * @文件版本：V0.01 
    */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 通用mapper默认是类型是表名，驼峰命名规则
 *
 *  SELECT employee_number,last_name,first_name,extension,email,office_code,reports_to,job_title  FROM employees
 */
@Entity
@Table(name = "employees")
public class Employees{
	@Id
	@Column(name = "employeenumber")
	private Integer employeeNumber;
	@Column
	private String lastName;
	@Column
	private String firstName;
	@Column
	private String extension;
	@Column
	private String email;
	@Column
	private String officeCode;
	@Column
	private Integer reportsTo;
	@Column
	private String jobTitle;

	public void setEmployeeNumber(Integer employeeNumber){
		this.employeeNumber=employeeNumber;
	}

	public Integer getEmployeeNumber(){
		return employeeNumber;
	}

	public void setLastName(String lastName){
		this.lastName=lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setFirstName(String firstName){
		this.firstName=firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setExtension(String extension){
		this.extension=extension;
	}

	public String getExtension(){
		return extension;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return email;
	}

	public void setOfficeCode(String officeCode){
		this.officeCode=officeCode;
	}

	public String getOfficeCode(){
		return officeCode;
	}

	public void setReportsTo(Integer reportsTo){
		this.reportsTo=reportsTo;
	}

	public Integer getReportsTo(){
		return reportsTo;
	}

	public void setJobTitle(String jobTitle){
		this.jobTitle=jobTitle;
	}

	public String getJobTitle(){
		return jobTitle;
	}

	   @Override
	   public String toString() {
		   return "Employees{" +
				   "employeeNumber=" + employeeNumber +
				   ", lastName='" + lastName + '\'' +
				   ", firstName='" + firstName + '\'' +
				   ", extension='" + extension + '\'' +
				   ", email='" + email + '\'' +
				   ", officeCode='" + officeCode + '\'' +
				   ", reportsTo=" + reportsTo +
				   ", jobTitle='" + jobTitle + '\'' +
				   '}';
	   }
   }

